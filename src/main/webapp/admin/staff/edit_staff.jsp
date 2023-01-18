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
								<form action="/BookStoreOnline/admin/manage/staff/edit"
									method="post" id="validateForm">
									<input type="hidden" name="id" value="${staff.id}" />
									<div class="form-group">
										<label class="required">First name:</label> <input
											name="firstName" class="form-control"
											placeholder="Nhập tên chính"
											value="${staff.fullName.firstName }" />
									</div>

									<div class="form-group">
										<label class="required">Middle name:</label> <input
											name="middleName" class="form-control"
											placeholder="Nhập tên đệm"
											value="${staff.fullName.middleName }" />
									</div>

									<div class="form-group">
										<label class="required">Last name:</label> <input
											name="lastName" class="form-control" placeholder="Nhập họ"
											value="${staff.fullName.lastName }" />
									</div>

									<div class="form-group">
										<label class="required">Mobile:</label> <input name="mobile"
											class="form-control" placeholder="Nhập SĐT"
											value="${staff.mobile }" />
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
											value="${staff.email }" readonly="readonly" />
									</div>

									<div class="form-group">
										<c:choose>
											<c:when test="${staff.sex == 'Nam' }">
												<label class="required">Gender:</label>&ensp;Nam <input
													type="radio" name="sex" value="Nam" checked="checked" />
										&emsp;Nữ <input type="radio" name="sex" value="Nữ" />
											</c:when>

											<c:when test="${staff.sex == 'Nữ' }">
												<label class="required">Gender:</label>&ensp;Nam <input
													type="radio" name="sex" value="Nam" />
										&emsp;Nữ <input type="radio" name="sex" value="Nữ"
													checked="checked" />
											</c:when>
										</c:choose>

									</div>

									<div class="form-group">
										<fmt:formatDate value="${staff.dateOfBirth }"
											pattern="yyyy-MM-dd" var="strDate" />
										<label class="required">Date of birth:</label> <input
											type="date" name="dob" class="form-control"
											placeholder="Nhập ngày sinh" value="${strDate }" />
									</div>

									<div class="form-group">
										<label class="required">Apartment number:</label> <input
											name="number" class="form-control" placeholder="Nhập số nhà"
											value="${staff.address.number }" />
									</div>

									<div class="form-group">
										<label class="required">Street:</label> <input name="street"
											class="form-control" placeholder="Nhập tên đường"
											value="${staff.address.street }" />
									</div>

									<div class="form-group">
										<label class="required">District:</label> <input
											name="district" class="form-control"
											placeholder="Nhập tên quận huyện"
											value="${staff.address.district }" />
									</div>

									<div class="form-group">
										<label class="required">City:</label> <input name="city"
											class="form-control" placeholder="Nhập tên thành phố"
											value="${staff.address.city }" />
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
											placeholder="Nhập tên tài khoản"
											value="${staff.account.username }" readonly="readonly" />
									</div>

									<div class="form-group">
										<label class="required">Password:</label> <input
											name="password" class="form-control"
											placeholder="Nhập mật khẩu" type="password"
											value="${staff.account.password }" />
									</div>

									<div class="form-group">
										<fmt:formatDate value="${staff.dateStart }"
											pattern="yyyy-MM-dd" var="strDate_" />
										<label class="required">Date start:</label> <input type="date"
											name="dateS" class="form-control"
											placeholder="Nhập ngày bắt đầu" value="${strDate_ }" />
									</div>

									<div class="form-group">
										<label class="required">Position: </label> <select class="form-control"
											name="position">
											<c:choose>
												<c:when test="${staff.position == 'MANAGER' }">
													<option value="MANAGER" selected="selected">MANAGER</option>
													<option value="ADMIN">ADMIN</option>
													<option value="SALES">SALES</option>
												</c:when>
												
												<c:when test="${staff.position == 'ADMIN' }">
													<option value="MANAGER">MANAGER</option>
													<option value="ADMIN" selected="selected">ADMIN</option>
													<option value="SALES">SALES</option>
												</c:when>
												
												<c:when test="${staff.position == 'SALES' }">
													<option value="MANAGER" selected="selected">MANAGER</option>
													<option value="ADMIN">ADMIN</option>
													<option value="SALES" selected="selected">SALES</option>
												</c:when>
											</c:choose>

										</select>
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
				dob : "required",
				number : {
					required : true,
					digits : true,
				},
				street : "required",
				district : "required",
				city : "required",
				password : {
					required : true,
					minlength : 6,
				},
				dateS : "required",
				
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
				dob : "Vui lòng nhập ngày sinh",
				number : {
					required : "Vui lòng nhập số nhà",
					digits : "Số nhà phải là số"
				},
				street : "Vui lòng nhập tên đường",
				district : "Vui lòng nhập tên quận, huyện",
				city : "Vui lòng nhập tên thành phố",
				password : {
					required : "Vui lòng đăng kí mật khẩu",
					minlength : "Mật khẩu cần tối thiểu 6 kí tự"
				},
				dateS : "Vui lòng nhập ngày bắt đầu",
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