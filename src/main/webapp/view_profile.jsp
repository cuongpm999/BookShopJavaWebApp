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

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/form.css">
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
		<h4 class="tieude-tp">THÔNG TIN CÁ NHÂN</h4>
		<div class="row card">
			<div class="col-md-12" style="padding: 10px;">
				<div class="card-body">
					<div class="panel-body">
						<table class="table table-hover">
							<tbody>
								<tr>
									<td>Tên tài khoản</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${customer.account.username }</td>
								</tr>
								<tr>
									<td>Họ và tên</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${customer.fullName.lastName }
										${customer.fullName.middleName } ${customer.fullName.firstName }</td>
								</tr>
								<tr>
									<td>Giới tính</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">
										${customer.sex}</td>
								</tr>
								<tr>
									<td>Ngày sinh</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;"><fmt:formatDate
											value="${customer.dateOfBirth }" pattern="dd/MM/yyyy" /></td>
								</tr>
								<tr>
									<td>Điện thoại</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${customer.mobile }</td>
								</tr>
								<tr>
									<td>Email</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${customer.email }</td>
								</tr>
								<tr>
									<td>Địa chỉ</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;">${customer.address.number },
										${customer.address.street }, ${customer.address.district },
										${customer.address.city }</td>
								</tr>
								<tr>
									<td>Điểm thưởng</td>
									<td style="font-size: 17px; font-weight: 600; color: #495057;"><fmt:formatNumber
											type="number" maxFractionDigits="0"
											value="${customer.point }" /></td>
								</tr>
								<tr>
									<td><a href="/BookStoreOnline/edit-profile" class="btn btn-primary" style="margin-top: 10px;"><i
											class="fas fa-edit"></i> Edit</a></td>
									<td></td>
								</tr>
							</tbody>
						</table>

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

</html>