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

<title>Book Store Online</title>

<!-- CSS & JS -->
<!-- CSS -->
<%@ include file="/includes/css_js.jsp"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/form.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js"></script>
</head>
<body>
	<!-- HEADER -->
	<%@ include file="/includes/header.jsp"%>
	<!-- ------ -->

	<!-- MAIN -->
	<div id="main" class="container lienhe">
		<h4 class="tieude-tp">ĐĂNG KÍ</h4>
		<div class="row card">
			<div class="col-md-12" style="padding: 10px;">
				<c:choose>
					<c:when test="${status=='failed'}">
						<div class="alert alert-danger">
							<strong>Failed!</strong> Bạn đã đăng kí thất bại!

						</div>
					</c:when>
				</c:choose>
				<div class="card-body">
					<div class="panel-body">
						<form action="/BookStoreOnline/register" method="post" id="registerForm">
							<div class="form-group">
								<label class="required">First name:</label> <input
									name="firstName" class="form-control"
									placeholder="Nhập tên chính"
									value="${customer.fullName.firstName }" />
							</div>

							<div class="form-group">
								<label class="required">Middle name:</label> <input
									name="middleName" class="form-control"
									placeholder="Nhập tên đệm"
									value="${customer.fullName.middleName }" />
							</div>

							<div class="form-group">
								<label class="required">Last name:</label> <input
									name="lastName" class="form-control" placeholder="Nhập họ"
									value="${customer.fullName.lastName }" />
							</div>

							<div class="form-group">
								<label class="required">Mobile:</label> <input name="mobile"
									class="form-control" placeholder="Nhập SĐT"
									value="${customer.mobile }" />
							</div>

							<div class="form-group">
								<c:choose>
									<c:when test="${status=='faileEmailBiTrung'}">
										<div class="alert alert-danger">
											<strong>Failed!</strong> Email đã được sử dụng!
										</div>
									</c:when>
								</c:choose>
								<label class="required">Email:</label> <input name="email"
									class="form-control" placeholder="Nhập email"
									value="${customer.email }" />
							</div>

							<div class="form-group">
								<label class="required">Gender:</label>&ensp;Nam <input
									type="radio" name="sex" value="Nam" checked="checked" />
								&emsp;Nữ <input type="radio" name="sex" value="Nữ" />
							</div>

							<div class="form-group">
								<label class="required">Date of birth:</label> <input
									type="date" name="dob" class="form-control"
									placeholder="Nhập ngày sinh" />
							</div>

							<div class="form-group">
								<label class="required">Apartment number:</label> <input
									name="number" class="form-control" placeholder="Nhập số nhà"
									value="${customer.address.number }" />
							</div>

							<div class="form-group">
								<label class="required">Street:</label> <input name="street"
									class="form-control" placeholder="Nhập tên đường"
									value="${customer.address.street }" />
							</div>

							<div class="form-group">
								<label class="required">District:</label> <input name="district"
									class="form-control" placeholder="Nhập tên quận huyện"
									value="${customer.address.district }" />
							</div>

							<div class="form-group">
								<label class="required">City:</label> <input name="city"
									class="form-control" placeholder="Nhập tên thành phố"
									value="${customer.address.city }" />
							</div>

							<div class="form-group">
								<c:choose>
									<c:when test="${status=='faileTenBiTrung'}">
										<div class="alert alert-danger">
											<strong>Failed!</strong> Tên tài khoản đã được sử dụng!
										</div>
									</c:when>
								</c:choose>
								<label class="required">Username:</label> <input name="username"
									class="form-control" placeholder="Nhập tên tài khoản"
									value="${customer.account.username }" />
							</div>

							<div class="form-group">
								<label class="required">Password:</label> <input name="password"
									class="form-control" placeholder="Nhập mật khẩu"
									type="password" value="${customer.account.password }" />
							</div>

							<div class="form-group">
								<label>Note: </label>
								<textarea name="note" class="form-control"
									placeholder="Nhập ghi chú" rows="5">${customer.note }</textarea>
							</div>

							<button type="submit" class="btn btn-success">
								<i class="fas fa-download"></i> Save
							</button>

						</form>
					</div>
				</div>
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

<script type="text/javascript">
	$().ready(function() {
		$('#registerForm').validate({
			rules : {
				firstName : "required",
				middleName : "required",
				lastName : "required",
				mobile : {
					required : true,
					digits : true,
					minlength : 10,
					maxlength : 10,
				},
				email : {
					required : true,
					email : true
				},
				dob : "required",
				number : {
					required : true,
					digits : true,
				},
				street : "required",
				district : "required",
				city : "required",
				username : "required",
				password : {
					required : true,
					minlength : 6,
				}
			},
			messages : {
				firstName : "Please enter your first name",
				middleName : "Please enter your middle name",
				lastName : "Please enter your last name",
				mobile : {
					required : "Please enter phone numbers",
					minlength : "Số điện thoại phải bao gồm 10 số",
					maxlength : "Số điện thoại chỉ bao gồm 10 số",
					digits : "Số điện thoại chỉ bao gồm số"
				},
				email : {
					required : "Vui lòng nhập Email",
					email : "Vui lòng nhập đúng Email"
				},
				dob : "Vui lòng nhập ngày sinh",
				number : {
					required : "Vui lòng nhập số nhà",
					digits : "Số nhà phải là số"
				},
				street : "Vui lòng nhập tên đường",
				district : "Vui lòng nhập tên quận, huyện",
				city : "Vui lòng nhập tên thành phố",
				username : "Vui lòng đăng kí tên tài khoản",
				password : {
					required : "Vui lòng đăng kí mật khẩu",
					minlength : "Mật khẩu cần tối thiểu 6 kí tự"
				}
			},
		})
	})
</script>

<style>
.error {
	font-weight: 400 !important;
	color: red;
	font-size: 15px !important;
}
</style>

</html>