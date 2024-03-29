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
						<form style="display: flex;"
							action="/BookStoreOnline/admin/statistic/income" method="post"
							id="stat-form">
							<div style="display: flex; margin-right: 20px;">
								<label
									style="margin-top: 6px; margin-right: 10px; font-size: 17px;">From:</label>
								<div>
									<fmt:formatDate value="${fromD }" pattern="yyyy-MM-dd"
										var="strDateFrom" />
									<input type="date" class="form-control" name="from"
										value="${strDateFrom}">
								</div>
							</div>
							<div style="display: flex; margin-right: 20px;">
								<label
									style="margin-top: 6px; margin-right: 10px; font-size: 17px;">To:
								</label>
								<div>
									<fmt:formatDate value="${toD }" pattern="yyyy-MM-dd"
										var="strDateTo" />
									<input type="date" class="form-control" name="to"
										value="${strDateTo}">
								</div>
							</div>
							<div>
								<button type="submit" class="btn btn-primary">
									<i class="fas fa-search"></i> Tìm
								</button>
							</div>
						</form>

						<h1 class="my-3"></h1>

						<table class="table">
							<thead class="thead-light">
								<tr>
									<th>#</th>
									<th>Khoảng thời gian</th>
									<th>Số lượng khách hàng mua</th>
									<th>Tổng thu nhập</th>
								</tr>
							</thead>
							<tbody id="myTable">
								<c:if test="${not empty incomeStat }">
									<tr>
										<td>1</td>
										<td>${incomeStat.period }</td>
										<td>${incomeStat.customer }</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="0"
												value="${incomeStat.revenue }" /> ₫</td>
									</tr>
								</c:if>
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

<script type="text/javascript">
	$().ready(function() {
		$('#stat-form').validate({
			rules : {
				from : "required",
				to : "required",

			},
			messages : {
				from : "Vui lòng nhập ngày bắt đầu",
				to : "Vui lòng nhập ngày kết thúc",
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
