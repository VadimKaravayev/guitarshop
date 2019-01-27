package com.epam.preprod.karavayev.nonshop.task7part2proxy;

import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.Product;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MyMapHandler implements InvocationHandler {
    private Map<String, Object> map = new HashMap<>();
    {
        map.put("id", -1);
        map.put("name", null);
        map.put("price", null);
        map.put("guitarType", null);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String name = method.getName();
        String key = Character.toLowerCase(name.charAt(3)) + name.substring(4);
        if (name.equals("equals")) {
            Object o = args[0];

            if (proxy == o) return true;
            if (o == null || proxy.getClass() != o.getClass()) return false;

            Product thisObj = ((Product) proxy);
            Product thatObj = (Product) o;

            if (thisObj.getId() != thatObj.getId()) return false;

            String fieldName = thisObj.getName();
            BigDecimal fieldPrice = thisObj.getPrice();
            GuitarType fieldGuitarType = thisObj.getGuitarType();
            if (fieldName != null ? !fieldName.equals(thatObj.getName()) : thatObj.getName() != null) return false;
            if (fieldPrice != null ? !fieldPrice.equals(thatObj.getPrice()) : thatObj.getPrice() != null) return false;
            return fieldGuitarType != null ? fieldGuitarType.equals(thatObj.getGuitarType()) : thatObj.getGuitarType() == null;
        }

        if (name.equals("hashCode")) {
            Product thisObj = (Product) proxy;
            int result = thisObj.getId();
            result = 31 * result + (thisObj.getName() != null ? thisObj.getName().hashCode() : 0);
            result = 31 * result + (thisObj.getPrice() != null ? thisObj.getPrice().hashCode() : 0);
            return result;
        }

        if (name.equals("toString")) {
            Product thisObj = (Product) proxy;
            return String.format("Product {id: %d, name: %s, price: %s, type: %s}",
                    thisObj.getId(),
                    thisObj.getName(),
                    thisObj.getPrice(),
                    thisObj.getGuitarType() == null ? "unknown type" : thisObj.getGuitarType().getName());
        }

        return name.startsWith("get") ? map.get(key) : name.startsWith("set") ? map.put(key, args[0]) : null;
    }
}
