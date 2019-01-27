<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="vk" %>
<fmt:setLocale value="${pageContext.request.locale.language}"/>
<fmt:setBundle basename="lang"/>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Shpping cart</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myStyles/shopping-cart-style.css">
	<link rel="stylesheet" href="css/myStyles/lang-choice-style.css">
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<div class="shopping-cart">
      <!-- Title -->
      <div class="title">
        <a href="${pageContext.request.contextPath}/product">&#10094;Back</a>
        <p>Shopping Bag</p>
        <button type="button" class="remove-all-btn" id="clear-cart">Clear cart &#10006;</button>
      </div>

        <div id="cart">
            <c:forEach var="item" items="${cart.cartMap}">
                <div class="item">

                    <div class="image">
                      <img src="${pageContext.request.contextPath}/images/${item.key.id}.jpg" alt="" />
                    </div>

                    <div class="description">
                      <span>${item.key.name}</span>
                    </div>

                    <div class="quantity" productid="${item.key.id}">
                      <button class="minus-btn" type="button">
                        &#9472;
                        <img src="${pageContext.request.contextPath}/images/minus.svg" alt="" />
                      </button>
                      <input type="text" name="name" value="${item.value}">
                      <button class="plus-btn" type="button">&#10010;
                        <img src="${pageContext.request.contextPath}/images/plus.svg" alt="" />
                      </button>
                    </div>

                    <div class="total-price">$${item.key.price}</div>
                    <div class="remove">
                      <button type="button" class="remove-btn" productid="${item.key.id}">
                        &#10006;
                      </button>
                    </div>
                  </div>
              </c:forEach>
        </div>

        <p id="allTotalPrice">Total sum: ${totalSum}</p>
        <c:set var="doCheckout"/>
        <c:if test="${not empty userEntity && not empty cart.cartMap}">
            <c:set var="doCheckout" value="order"/>
        </c:if>
        <c:if test="${empty userEntity}">
            <c:set var="doCheckout" value="home"/>
            <c:set var="promptMsg" value="You must be logged in to place an order"/>
        </c:if>
        <span id="promtpMsg">${promptMsg}</span>
        <div id="order-btn"><a href="${doCheckout}">Check out</a><div>
    </div>

    <script src="${pageContext.request.contextPath}/js/myScript/cart.js"></script>
    <script src="js/myScript/lang-choice.js"></script>
</body>
</html>

<!-- ${pageContext.request.contextPath}/order -->