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
									<strong>Faile!</strong> Bạn đã sửa thất bại!

								</div>
							</c:when>
						</c:choose>
						<div class="card-body">
							<div class="panel-body">
								<form action="/BookStoreOnline/admin/manage/publisher/edit" method="post" id="validateForm">
									<input type="hidden" name="id" value="${publisher.id}" />
									<div class="form-group">
										<label class="required" for="txtInput">Name: </label> <input
											name="name" class="form-control" placeholder="Nhập tên NXB" value="${publisher.name }"/>
									</div>
									<div class="form-group">
										<label class="required">Address: </label> <input
											name="address" class="form-control"
											placeholder="Nhập địa chỉ NXB" value="${publisher.address }"/>
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
				name : "required",
				address : "required",
			},
			messages : {
				name : "Please enter name publisher",
				address : "Please enter address publisher",
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