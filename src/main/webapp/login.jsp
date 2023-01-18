<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS & JS -->
<%@ include file="/includes/css_js.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js"></script>

<title>Book Store Online</title>
</head>
<body>
	<!-- HEADER -->
	<%@ include file="/includes/header.jsp"%>
	<!-- ------ -->

	<!-- MAIN -->
	<div id="main">
		<div class="header-w3l">
			<h1 class="mt-5">Đăng nhập</h1>
		</div>
		<div class="main-w3layouts-agileinfo">
			<!--form-stars-here-->
			<div class="wthree-form">
				<h2>Vui lòng điền để đăng nhập</h2>
				<a class="login-google"
					href="https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile&redirect_uri=http://localhost:8080/BookStoreOnline/login-google&response_type=code
    &client_id=609518659592-1hj1qicf627bj8fmg3s08fha3mjo4qjb.apps.googleusercontent.com&approval_prompt=force">
					<div class="btn-gg">
						<div class="btn-gg-img">
							<img src="${pageContext.request.contextPath}/static/img/icons8-google-48.png" alt="logo">
						</div>
						<div class="btn-gg-text">
							Tiếp tục với <b>Gmail</b>
						</div>
					</div>
				</a> <a class="login-facebook"
					href="#">
					<div class="btn-f">
						<div class="btn-f-img">
							<img src="${pageContext.request.contextPath}/static/img/icons8-facebook-48.png" alt="logo">
						</div>
						<div class="btn-f-text">
							Tiếp tục với <b>Facebook</b>
						</div>
					</div>
				</a>
				<div class="separator">Hoặc</div>
				<form action="/BookStoreOnline/login" method="post">
					<c:choose>
						<c:when test="${status=='failed'}">
							<div class="alert alert-danger"
								style="font-size: 13px; text-align: center;">
								<strong>Failed!</strong> Đăng nhập thất bại!
							</div>
						</c:when>
					</c:choose>
					<div class="form-sub-w3">
						<input type="text" name="username" placeholder="Tên tài khoản"
							required />
						<div class="icon-w3">
							<i class="fa fa-user" aria-hidden="true"></i>
						</div>
					</div>
					<div class="form-sub-w3">
						<input type="password" name="password" placeholder="Mật khẩu"
							required />
						<div class="icon-w3">
							<i class="fa fa-unlock-alt" aria-hidden="true"></i>
						</div>
					</div>
					<div class="submit-agileits">
						<input type="submit" name="submit" value="Đăng nhập">
					</div>
				</form>
				<h2 style="margin-top: 30px;">
					Bạn chưa có tài khoản <br> <a href="/BookStoreOnline/register">Đăng kí
						ngay</a>
				</h2>
			</div>

		</div>
	</div>
	<!-- ------ -->

	<!-- FOOTER -->
	<%@ include file="/includes/footer.jsp"%>
	<!-- ------ -->
	
	<!--Start of Tawk.to Script-->
	<script type="text/javascript">
		var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
		(function() {
			var s1 = document.createElement("script"), s0 = document
					.getElementsByTagName("script")[0];
			s1.async = true;
			s1.src = 'https://embed.tawk.to/619b7c236bb0760a4943ba71/1fl3lpqvs';
			s1.charset = 'UTF-8';
			s1.setAttribute('crossorigin', '*');
			s0.parentNode.insertBefore(s1, s0);
		})();
	</script>
	<!--End of Tawk.to Script-->
	
</body>

</html>