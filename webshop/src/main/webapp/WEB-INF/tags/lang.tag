<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${pageContext.request.locale.language}"/>
<fmt:setBundle basename="lang"/>
<div class="dropdown">
<button onclick="openDropdownLang()" class="dropbtn"><fmt:message key="language"/></button>
  <div id="myDropdownLang" class="dropdown-content">
    <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en"><fmt:message key="english"/></a>
    <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru"><fmt:message key="russian"/></a>
  </div>
</div>