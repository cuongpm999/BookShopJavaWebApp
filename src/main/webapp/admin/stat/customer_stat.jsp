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
						<input class="form-control" id="myInput" type="text"
							placeholder="Search..">

						<h1 class="my-3"></h1>

						<table class="table">
							<thead class="thead-light">
								<tr>
									<th>#</th>
									<th>Full name</th>
									<th>Sex</th>
									<th>Mobile</th>
									<th>Email</th>
									<th>Address</th>
									<th>Total money</th>
								</tr>
							</thead>
							<tbody id="myTable">
								<c:forEach items="${customerStats }" var="customerStat"
									varStatus="loop">
									<tr>
										<td>${loop.index + 1 }</td>
										<td>${customerStat.fullName.lastName }
											${customerStat.fullName.middleName }
											${customerStat.fullName.firstName }</td>
										<td>${customerStat.sex }</td>
										<td>${customerStat.mobile }</td>
										<td>${customerStat.email }</td>
										<td>${customerStat.address.number },
											${customerStat.address.street },
											${customerStat.address.district },
											${customerStat.address.city }</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="0"
												value="${customerStat.revenue }" /> ₫</td>
									</tr>
								</c:forEach>
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
