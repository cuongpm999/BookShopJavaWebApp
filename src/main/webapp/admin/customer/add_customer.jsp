<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="description"
	content="ban laptop uy tin, chat luong, dich vu tot">
<meta name="keywords"
	content="laptop, asus, dell, hp, lenovo, acer, apple, msi, lg">
<meta name="author" content="CuongPham">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS & JAVA_SCRIPT -->
<%@ include file="/includes/css_js.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/manage.css">
	
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js"></script>
<!-- ----------------- -->

<title>Book Store Online</title>
</head>

<body>

	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v8.0"
		nonce="ChmFo6ST"></script>

	<!-- MAIN -->
	<div id="main">
		<%@ include file="/admin/header_admin.jsp"%>

		<div class="main-content">
			<div class="row">
				<div class="col-md-12">
					<div class="form-add">
						<c:choose>
							<c:when test="${status=='failed'}">
								<div class="alert alert-danger">
									<strong>Failed!</strong> Bạn đã thêm thất bại!

								</div>
							</c:when>
						</c:choose>
						<div class="card-body">
							<div class="panel-body">
								<form action="/BookStoreOnline/admin/manage/customer/add"
									method="post" id="validateForm">
									<div class="form-group">
										<label class="required">First name:</label> <input
											name="firstName" class="form-control"
											placeholder="Nhập tên chính" value="${customer.fullName.firstName }"/>
									</div>

									<div class="form-group">
										<label class="required">Middle name:</label> <input
											name="middleName" class="form-control"
											placeholder="Nhập tên đệm" value="${customer.fullName.middleName }"/>
									</div>

									<div class="form-group">
										<label class="required">Last name:</label> <input
											name="lastName" class="form-control" placeholder="Nhập họ" value="${customer.fullName.lastName }"/>
									</div>

									<div class="form-group">
										<label class="required">Mobile:</label> <input name="mobile"
											class="form-control" placeholder="Nhập SĐT" value="${customer.mobile }"/>
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
											class="form-control" placeholder="Nhập email" value="${customer.email }"/>
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
											name="number" class="form-control" placeholder="Nhập số nhà" value="${customer.address.number }"/>
									</div>

									<div class="form-group">
										<label class="required">Street:</label> <input name="street"
											class="form-control" placeholder="Nhập tên đường" value="${customer.address.street }"/>
									</div>

									<div class="form-group">
										<label class="required">District:</label> <input
											name="district" class="form-control"
											placeholder="Nhập tên quận huyện" value="${customer.address.district }"/>
									</div>

									<div class="form-group">
										<label class="required">City:</label> <input name="city"
											class="form-control" placeholder="Nhập tên thành phố" value="${customer.address.city }"/>
									</div>

									<div class="form-group">
										<c:choose>
											<c:when test="${status=='faileTenBiTrung'}">
												<div class="alert alert-danger">
													<strong>Failed!</strong> Tên tài khoản đã được sử dụng!
												</div>
											</c:when>
										</c:choose>
										<label class="required">Username:</label> <input
											name="username" class="form-control"
											placeholder="Nhập tên tài khoản" value="${customer.account.username }"/>
									</div>

									<div class="form-group">
										<label class="required">Password:</label> <input
											name="password" class="form-control"
											placeholder="Nhập mật khẩu" type="password" value="${customer.account.password }"/>
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
		</div>
		<%@ include file="/admin/footer_admin.jsp"%>
	</div>
	</div>

</body>

<script type="text/javascript">
	$().ready(function() {
		$('#validateForm').validate({
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