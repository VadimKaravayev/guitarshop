<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="vk" %>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Success order page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
        body {
            color: #585858;
        }

        .box {
            display: flex;
            flex-direction: column;
            text-align: center;
        }

        .checked {
            font-size: 100px;
            color: darkseagreen;
        }

	</style>
</head>

<body>
    <div class="box">
        <i class="fa fa-check-circle checked"></i>
        <h1>Thank you</h1>
        <p>Your order has been successfully placed!</p>
        <a href="product">shop</a>
    </div>

</body>


</html>