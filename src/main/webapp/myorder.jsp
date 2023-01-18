<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="description" content="app mua sách">
<meta name="keywords" content="app, mua, sách">
<meta name="author" content="CuongPham">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS & JS -->
<%@ include file="/includes/css_js.jsp"%>
<!-- --- -->

<title>Book Store Online</title>
</head>

<style>
#main .table {
	border-collapse: collapse;
	border: 1px solid #ebebeb;
	text-align: center;
	vertical-align: middle;
}

.table thead tr {
	background: #f8f8f8;
}

.table thead th {
	border-collapse: collapse;
	border: 1px solid #ebebeb;
	text-align: center;
	vertical-align: middle;
	border-bottom: 2px solid #ddd;
	font-size: 15px;
}

.table tbody td {
	border-collapse: collapse;
	border: 1px solid #ebebeb;
	text-align: center;
	vertical-align: middle;
}

.table tbody td a:hover {
	text-decoration: none;
}

.table tfoot .btn {
	padding: 6px 18px;
}

.total-cart {
	text-transform: uppercase;
	text-align: right;
	font-weight: 500;
	font-size: 18px;
	margin-top: 25px;
}

.total-money {
	color: red;
}

.total-cart .btn {
	margin-top: 15px;
	padding: 8px 20px;
}

.panel-primary {
	border-color: #337ab7;
}

.panel {
	margin-bottom: 20px;
	background-color: #fff;
	border: 1px solid #e9ecf2;
	border-radius: 4px;
	-webkit-box-shadow: 0 1px 1px rgb(0 0 0/ 0.2);
	box-shadow: 0 1px 1px rgb(0 0 0/ 0.2);
}

.panel-primary>.panel-heading {
	background-color: #1c215ee6;
	color: #fff;
	border: none;
}

.panel-heading {
	font-size: 18px;
	font-weight: 300;
	letter-spacing: 0.025em;
	height: 60px;
	line-height: 38px;
	padding: 10px 15px;
	border-top-left-radius: 3px;
	border-top-right-radius: 3px;
}

.panel-body {
	padding: 15px;
}

body {
	background: #ededed;
}
</style>

<body>
	<!-- HEADER -->
	<%@ include file="/includes/header.jsp"%>
	<!-- ------ -->


	<!-- MAIN  -->
	<div id="main">
		<div class="container">
			<h1 class="mt-5"></h1>
			<c:forEach var="order" items="${myorders }">
				<div class="panel panel-primary" style="margin-top: 30px;">
					<div class="panel-heading">
						Ngày mua:
						<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy"
							value="${order.key.dateCreate}" />
						- Tổng tiền:
						<fmt:formatNumber type="number" maxFractionDigits="0"
							value="${order.key.payment.totalMoney}" />
						₫ - ${order.key.shipment.name} <fmt:formatNumber type="number" maxFractionDigits="0"
							value="${order.key.shipment.price}" /> đ
					</div>
					<div class="panel-body">
						<div style="margin-bottom: 10px;">
							<span class="text-success">${order.key.status}</span>
						</div>
						<table class="table">
							<thead>
								<tr>
									<th>Ảnh sản phẩm</th>
									<th>Tên sản phẩm</th>
									<th style="width: 150px;">Giá</th>
									<th style="width: 100px;">Số lượng</th>
									<th style="width: 150px;">Thành tiền</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="lineBookItem" items="${order.value }">
									<tr>
										<td><img style="height: 120px;"
											src="https://res.cloudinary.com/cuongpham/image/upload/${lineBookItem.key.img }" /></td>
										<td><a href="javascript:void(0);"
											style="font-size: 15px; color: black;">${lineBookItem.key.book.title }</a></td>
										<td><fmt:formatNumber type="number" maxFractionDigits="0"
												value="${lineBookItem.key.price*(100-lineBookItem.key.discount)/100 }" />
											₫</td>
										<td style="text-align: center">${lineBookItem.value }</td>
										<td
											style="font-weight: 500; color: red; font-size: 15px; text-align: center">
											<fmt:formatNumber type="number" maxFractionDigits="0"
												value="${(lineBookItem.key.price*(100-lineBookItem.key.discount)/100) * lineBookItem.value }" />
											₫
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- --- -->

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