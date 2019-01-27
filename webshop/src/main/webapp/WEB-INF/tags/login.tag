<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${pageContext.request.locale.language}"/>
<fmt:setBundle basename="lang"/>

<c:if test="${empty sessionScope.userEntity}">
    <form method="post" action="login">
    <div class="styled-input agile-styled-input-top">
        <input type="email" placeholder="Email" name="email" value="${email}">
    </div>
    <div class="styled-input agile-styled-input-top">
        <input type="password" placeholder="Password" name="password"/>
    </div>
    <div>
        <span class="error">${loginError}</span>
    </div>
    <fmt:message key="login" var="log"/>
    <input type="submit" value="${log}"/>
    </form>
</c:if>
<c:if test="${not empty sessionScope.userEntity}">
    <form action="logout" method="post">
        <img src="image" style="width:50px; height:50px; border-radius: 50%;"/>
        <p>${sessionScope.userEntity.firstName} ${sessionScope.userEntity.lastName}</p>
        <fmt:message key="logout" var="logout"/>
        <input type="submit" value="${logout}"/>
    </form>
</c:if>

<c:remove var="email" scope="session" />
<c:remove var="loginError" scope="session" />
