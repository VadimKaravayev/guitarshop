<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="vk" %>
<fmt:setLocale value="${pageContext.request.locale.language}"/>
<fmt:setBundle basename="lang"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Order</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/myStyles/checkout-form-style.css">
	<link rel="stylesheet" href="css/myStyles/myStyles.css">
	<link rel="stylesheet" href="css/myStyles/lang-choice-style.css">
</head>
<body>
    <div class="wrapper">
    <a href="${pageContext.request.contextPath}/cart">Back</a>
	<h2>Checkout Form</h2>
	<div class="row">
        <div class="col-25">
            <div class="container">
              <h4>Cart <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b>${cart.cartMap.size()}</b></span></h4>
              <c:set var="total" value="0"/>
              <c:forEach var="cartEntry" items="${cart.cartMap}">
                <c:set var="price" value="${cartEntry.value * cartEntry.key.price}"/>
                <c:set var="total" value="${total + price}"/>
                <p>${cartEntry.key.name} <span class="price">$${price}</span></p>
              </c:forEach>
              <hr>
              <p>Total <span class="price" style="color:black"><b>$<fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${total}" /></b></span></p>
            </div>
          </div>
      <div class="col-75">
        <div class="container">
          <form action="order" method="post">
            <div class="row">
              <div class="col-50">
                <h3>Billing Address</h3>
                <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                <span id="name_error" class="error">${userCheckoutErrors["addressErr"]}</span>
                <input type="text" id="adr" name="address" placeholder="542 W. 15th Street">
              </div>
              <div class="col-50">
                <h3>Payment</h3>
                <label for="ccnum">Credit card number</label>
                <span id="name_error" class="error">${userCheckoutErrors["creditCardErr"]}</span>
                <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444">
              </div>
            </div>
            <span id="name_error" class="error">${userCheckoutErrors["cart"]}</span>
            <input type="submit" value="Continue to checkout" class="btn">
          </form>
        </div>
      </div>
    </div>
    </div>
    <script src="js/myScript/lang-choice.js"></script>
</body>
</html>