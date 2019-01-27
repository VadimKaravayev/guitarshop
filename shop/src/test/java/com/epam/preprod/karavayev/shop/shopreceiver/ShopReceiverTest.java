package com.epam.preprod.karavayev.shop.shopreceiver;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.productstock.*;
import com.epam.preprod.karavayev.shop.utils.ConsoleHelper;
import com.epam.preprod.karavayev.shop.utils.InputProcessor;
import com.epam.preprod.karavayev.shop.utils.UserInterface;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ShopReceiverTest {
    private ShopReceiver shopReceiver;
    private Stock stock;
    private ShoppingCart shoppingCart;
    private ShoppingCartHistory shoppingCartHistory;
    private OrderDatabase orderDatabase;
    private UserInterface console;
    private InputProcessor processor;

    @Before
    public void setUp() {
        List<StringInstrument> list = new ArrayList<>();
        stock = new Stock(list);
        shoppingCart = new ShoppingCart();
        shoppingCartHistory = new ShoppingCartHistory();
        orderDatabase = new OrderDatabase();
        processor = new InputProcessor();
        console = new ConsoleHelper();
        shopReceiver = new ShopReceiver(stock, shoppingCart, shoppingCartHistory, orderDatabase, console, processor);
    }

    public void mockOrder(Date date) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        Order order = new Order(map);
        orderDatabase.addToOrderDatabase(date, order);
    }

    @Test
    public void shouldReturnTotalPriceOfProductsInCart() {

        stock.addProductToStock(new Guitar(1, "Ibanez", new BigDecimal(100)));
        stock.addProductToStock(new Guitar(2, "Fender", new BigDecimal(100)));
        stock.addProductToStock(new Guitar(3, "Yamaha", new BigDecimal(100)));
        for (int i = 1; i <= 3; i++ ) {
            shoppingCart.addToCart(i, i);
        }
        assertThat(shoppingCart.getTotalPrice(stock), is("600"));
    }

    @Test
    public void shouldDisplayFiveLastProductAddedToCart() {
        int[] ids = {3, 2, 5, 10, 9, 7, 1, 4, 6, 8};
        int[] amounts = {1, 2, 4, 3, 5, 4, 2, 1, 1, 5};
        for (int i = 0; i < ids.length; i++ ) {
            shoppingCart.addToCart(ids[i], amounts[i]);
            shoppingCartHistory.addToCartHistory(ids[i], amounts[i]);
        }
        int[] args = Arrays.copyOfRange(ids, 5, 9);
        Iterator<Map.Entry<Integer, ShoppingCartHistory.Item>> iterator =
                shoppingCartHistory.getCartHistoryMap().entrySet().iterator();
        for (Integer arg: args) {
            assertThat(iterator.next().getValue().getProductId(), is(arg));
        }
    }

    @Test
    public void shouldShowOrdersBetweenTwoSpecifiedDates() throws ParseException {
        Date d1 = processor.convertStringToDate("12-02-2018 12-00");
        Date d2 = processor.convertStringToDate("13-03-2018 12-00");
        Date d3 = processor.convertStringToDate("14-04-2018 12-00");
        Date d4 = processor.convertStringToDate("15-05-2018 12-00");
        mockOrder(d1);
        mockOrder(d2);
        mockOrder(d3);
        mockOrder(d4);
        Map<Date, Order> ordersByPeriod = shopReceiver.getOrdersByPeriod(d2, d4);
        assertEquals(2, ordersByPeriod.size());
    }

    @Test
    public void shouldReturnOrderByClosestDate() throws ParseException {
        Date d1 = processor.convertStringToDate("12-02-2018 12-00");
        Date d2 = processor.convertStringToDate("13-03-2018 12-00");
        Date d3 = processor.convertStringToDate("14-04-2018 12-00");
        Date d4 = processor.convertStringToDate("15-02-2018 12-00");

        shoppingCart.addToCart(1, 1);
        shoppingCart.addToCart(2, 1);
        Order order1 = new Order(shoppingCart.getCartMap());
        shoppingCart.clearCart();

        shoppingCart.addToCart(4, 3);
        Order order2 = new Order(shoppingCart.getCartMap());
        shoppingCart.clearCart();

        shoppingCart.addToCart(5, 2);
        Order order3 = new Order(shoppingCart.getCartMap());
        shoppingCart.clearCart();

        orderDatabase.addToOrderDatabase(d1, order1);
        orderDatabase.addToOrderDatabase(d2, order2);
        orderDatabase.addToOrderDatabase(d3, order3);
        Order orderByClosestDate = shopReceiver.getOrderByClosestDate(d4).get();
        assertEquals(order1, orderByClosestDate);
    }
}