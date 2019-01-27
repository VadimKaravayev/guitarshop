<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="vk" %>
<fmt:setLocale value="${pageContext.request.locale.language}"/>
<fmt:setBundle basename="lang"/>
<!DOCTYPE html>
<html lang="zxx">

<head>
	<title>Cool Music Signup</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="css/font-awesome.css" rel="stylesheet">

	<link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />

	<link rel="stylesheet" type="text/css" href="css/jquery-ui1.css">
    <link rel="stylesheet" href="css/myStyles/myStyles.css">
    <link rel="stylesheet" href="css/myStyles/lang-choice-style.css">

	<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800" rel="stylesheet">
</head>

<body>
    <div>
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<a href="home">Back Home</a>
				</div>
				<div class="modal-body modal-body-sub_agile">
					<div class="main-mailposi">
						<span class="fa fa-envelope-o" aria-hidden="true"></span>
					</div>
					<div class="modal_body_left modal_body_left1">
						<h3 class="agileinfo_sign">Sign Up</h3>
						<p>
							Let's set up your Cool Music Account.
						</p>
						<!--  -->
						<form action="register" method="post" id="signup-form" enctype="multipart/form-data">
							<div class="styled-input agile-styled-input-top">
                                <span id="name_error" class="error">${userRegErrors["name"]}</span>
                                <input type="text" placeholder="Name" name="name" id="firstname" value="${userRegDto.firstName}">
							</div>
                            <div class="styled-input agile-styled-input-top">
                                <span id="lastname_error" class="error">${userRegErrors["lastname"]}</span>
								<input type="text" placeholder="Last name" name="lastname" id="lastname" value="${userRegDto.lastName}">
							</div>
							<div class="styled-input">
                                <span id="email_error" class="error">${userRegErrors["email"]}</span>
								<input type="text" placeholder="E-mail" name="email" id="useremail" value="${userRegDto.email}"/>
							</div>
							<div class="styled-input">
                                <span id="pass_error" class="error">${userRegErrors["password"]}</span>
								<input type="password" placeholder="Password" name="password" id="password1">
							</div>
							<div class="styled-input">
								<span id="passconfirm_error" class="error">${userRegErrors["passwordconfirm"]}</span>
								<input type="password" placeholder="Confirm Password" name="passwordconfirm" id="password2"/>
							</div>
                            <div class="styled-input">
                                <input type="checkbox" name="subscription" id="password2" value="yes" <c:if test="${userRegDto.subscription != null}">checked</c:if>>Subscription</input>
                            </div>
                            <div class="styled-input">
                                <span id="file_error" class="error">${userRegErrors["file"]}</span>
                                <span id="file_error" class="error">${userRegErrors["imageMaxSize"]}</span>
                                <input type="file" name="file" id="file" accept="image/*">Choose file...</input>
                            </div>
							<div>
							    <p>Verify you are a human.</p>
							    <input type="text" name="captchavalue" id="captcha-input" autocomplete="off"/>
							    <span id="captcha_error" class="error">${userRegErrors["captchavalue"]}</span>

                                <div><vk:captcha/></div>

							</div>
							<input type="submit" value="Sign Up" id="signUp-submit">
						</form>

						<p>
							<a href="#">By clicking register, I agree to your terms</a>
						</p>
					</div>
				</div>
			</div>
			<script src="js/myScript/lang-choice.js"></script>
</body>
</html>