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
						<table class="table table-hover">
							<tbody>
								<tr>
									<td>Tên tài khoản</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${staff.account.username }</td>
								</tr>
								<tr>
									<td>Họ và tên</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${staff.fullName.lastName }
										${staff.fullName.middleName } ${staff.fullName.firstName }</td>
								</tr>
								<tr>
									<td>Giới tính</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">
										${staff.sex}</td>
								</tr>
								<tr>
									<td>Ngày sinh</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;"><fmt:formatDate
											value="${staff.dateOfBirth }" pattern="dd/MM/yyyy" /></td>
								</tr>
								<tr>
									<td>Điện thoại</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${staff.mobile }</td>
								</tr>
								<tr>
									<td>Email</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${staff.email }</td>
								</tr>
								<tr>
									<td>Địa chỉ</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${staff.address.number },
										${staff.address.street }, ${staff.address.district },
										${staff.address.city }</td>
								</tr>
								<tr>
									<td>Ngày bắt đầu làm</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;"><fmt:formatDate
											value="${staff.dateStart }" pattern="dd/MM/yyyy" /></td>
								</tr>
								<tr>
									<td>Vị trí</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${staff.position }</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/admin/footer_admin.jsp"%>
	</div>
	</div>

</body>

</html>
