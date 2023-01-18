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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/cart.css">
<!-- --- -->

<title>Book Store Online</title>
</head>

<body>
	<!-- HEADER -->
	<%@ include file="/includes/header.jsp"%>
	<!-- ------ -->

	<!-- MAIN  -->
	<div id="main" class="container">
		<h4 class="tieude-tp">GIỎ HÀNG CỦA BẠN</h4>
		<c:choose>
			<c:when test="${status=='success'}">
				<div class="alert alert-success">
					<strong>Success!</strong> Bạn đã đặt hàng thành công!
				</div>
			</c:when>
			<c:when test="${status=='failed'}">
				<div class="alert alert-danger">
					<strong>Faile!</strong> Bạn đã đặt hàng thất bại!
				</div>
			</c:when>
			<c:when test="${status=='emptyCart'}">
				<div class="alert alert-danger">
					<strong>Faile!</strong> Bạn hãy chọn hàng cần mua!
				</div>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${not empty cart }">

				<div class="row header-title text-center d-flex">
					<div class="col-4">Sản phẩm</div>
					<div class="col-2">Đơn giá</div>
					<div class="col-2">Số lượng</div>
					<div class="col-2">Thành tiền</div>
					<div class="col-2"></div>
				</div>

				<c:forEach var="lineBookItem" items="${mapLineItem }">
					<div class="row item text-center">
						<div class="col-4 text-center">
							<div class="row">
								<div class="col-4">
									<a
										href="/BookStoreOnline/book/detail?barCode=${lineBookItem.key.barCode }"><img
										src="https://res.cloudinary.com/cuongpham/image/upload/${lineBookItem.key.img }"
										class="img-fluid" alt="Responsive image" /></a>
								</div>
								<div class="col-8"
									style="align-self: center; word-break: break-all;">
									<a
										href="/BookStoreOnline/book/detail?barCode=${lineBookItem.key.barCode }">${lineBookItem.key.book.title }</a>
								</div>
							</div>
						</div>
						<div class="col-2"
							style="align-self: center; word-break: break-all;">
							<fmt:formatNumber type="number" maxFractionDigits="0"
								value="${lineBookItem.key.price*(100-lineBookItem.key.discount)/100 }" />
							₫
						</div>
						<div class="col-2"
							style="align-self: center; word-break: break-all;">
							<input class="form-control" value="${lineBookItem.value }"
								type="number" min=1
								style="width: 70px; margin: auto; text-align: center;"
								id="quantity${lineBookItem.key.barCode}"
								onchange="Shop.editCart('${lineBookItem.key.barCode}')">
						</div>
						<div class="col-2"
							style="align-self: center; word-break: break-all;"
							id="price${lineBookItem.key.barCode}">
							<fmt:formatNumber type="number" maxFractionDigits="0"
								value="${(lineBookItem.key.price*(100-lineBookItem.key.discount)/100) * lineBookItem.value }" />
							₫
						</div>
						<div class="col-2" style="align-self: center;">
							<a href="javascript:void(0);"
								onclick="Shop.deleteCart('${lineBookItem.key.barCode}')"
								style="color: black;"><i class="far fa-trash-alt"></i></a>
						</div>
					</div>
				</c:forEach>

				<div class="card-body row"
					style="justify-content: flex-end; background: white; margin-top: 10px">
					<div class="tongtienthanhtoan">
						Tổng tiền đơn hàng : <strong><span id="total_value"><fmt:formatNumber
									type="number" maxFractionDigits="0"
									value="${cart.totalAmount }" /> </span> ₫</strong>
					</div>
				</div>
				<div class="row" style="justify-content: flex-end; margin-top: 20px">
					<c:choose>
						<c:when test="${empty cart }">
							<a href="javascript:void(0);"
								class="btn btn-success button-buy-submit-cart">Tiến hành đặt
								hàng</a>
						</c:when>

						<c:when test="${not empty cart}">
							<a href="/BookStoreOnline/checkout"
								class="btn btn-success button-buy-submit-cart">Tiến hành đặt
								hàng</a>
						</c:when>
					</c:choose>
				</div>

			</c:when>
			<c:when test="${empty cart }">
				<div class="no-cart">
					<img alt="nocart"
						src="${pageContext.request.contextPath}/static/img/no_cart.png">
				</div>
			</c:when>
		</c:choose>

	</div>
	<!-- ----- -->
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
