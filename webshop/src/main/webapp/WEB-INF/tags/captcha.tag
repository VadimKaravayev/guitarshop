<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${storeMethodName eq 'hiddenField'}">
    <input type="hidden" name="captchaId" value="${captchaId}"/>
</c:if>

<c:url value="captcha" var="doCaptcha" scope="session">
    <c:param name="captchaId" value="${captchaId}"/>
</c:url>

<img src="${doCaptcha}"/>


