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
	<title>403</title>
	<link rel="stylesheet" href="css/myStyles/service-page-style.css">
</head>
<body>

	<div class="cover">
	    <h1>Access denied <small>Error 403</small></h1>
	    <p class="lead">The requested resource requires an authentication.</p>
	</div>
</body>
</html>
