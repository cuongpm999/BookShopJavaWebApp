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
	href="${pageContext.request.contextPath}/static/css/buy-now.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/cart.css">
<!-- --- -->

<title>Book Store Online</title>
</head>

<body>
	<!-- HEADER -->
	<%@ include file="/includes/header.jsp"%>
	<!-- ------ -->

	<!-- MAIN -->
	<div id="main">
		<div class="container" style="padding: 0;">
			<div class="type-address">
				<div class="row">
					<div class="col-6">
						<h1>Địa Chỉ Nhận Hàng</h1>
					</div>
					<div class="col-6">
						<button type="button" class="btn btn-dark" style="float: right"
							data-toggle="modal" data-target="#exampleModal">Sửa địa
							chỉ</button>
					</div>
				</div>
				<div class="form-check radio-address">

					<span class="name-customer">${customerLogin.fullName.firstName }
						${customerLogin.fullName.lastName }</span> <span class="address-customer">${customerLogin.address.number }
						${customerLogin.address.street } ${customerLogin.address.district }
						${customerLogin.address.city }</span>
				</div>
			</div>
		</div>

		<div class="container">
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
								<a href="javascript:void(0);"><img
									src="https://res.cloudinary.com/cuongpham/image/upload/${lineBookItem.key.img }"
									class="img-fluid" alt="Responsive image" /></a>
							</div>
							<div class="col-8"
								style="align-self: center; word-break: break-all;">
								<a href="javascript:void(0);">${lineBookItem.key.book.title }</a>
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
						${lineBookItem.value }</div>
					<div class="col-2"
						style="align-self: center; word-break: break-all;"
						id="price${lineBookItem.key.barCode}">
						<fmt:formatNumber type="number" maxFractionDigits="0"
							value="${(lineBookItem.key.price*(100-lineBookItem.key.discount)/100) * lineBookItem.value }" />
						₫
					</div>

				</div>
			</c:forEach>
		</div>

		<div class="container payment-method-type-delivery">
			<c:choose>
				<c:when test="${status=='ccnameNull'}">
					<div class="alert alert-danger">
						<strong>Failed!</strong> Loại thẻ không được để trống!
					</div>
				</c:when>
				<c:when test="${status=='ccnumberNull'}">
					<div class="alert alert-danger">
						<strong>Failed!</strong> Mã số thẻ không được để trống!
					</div>
				</c:when>
				<c:when test="${status=='ccexpirationNull'}">
					<div class="alert alert-danger">
						<strong>Failed!</strong> Hạn thẻ không được để trống!
					</div>
				</c:when>
			</c:choose>
			<div class="row payment-method">
				<h4>Phương thức thanh toán</h4>
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active method-payment"
						onclick="Shop.setPaymentWith('Cash')"><a href="#tab-01"
						aria-controls="tab-01" role="tab" data-toggle="tab">Thanh toán
							khi nhận hàng</a></li>
					<li class="method-payment" role="presentation"
						onclick="Shop.setPaymentWith('DigitalWallet')"><a
						href="#tab-02" aria-controls="tab-02" role="tab" data-toggle="tab">Digital
							Wallet</a></li>
					<li class="method-payment" role="presentation"
						onclick="Shop.setPaymentWith('Credit')"><a href="#tab-03"
						aria-controls="tab-03" role="tab" data-toggle="tab">Credit</a></li>
				</ul>
			</div>
			<form action="/BookStoreOnline/checkout" method="post">
				<input type="hidden" name="paymentWith" value="Cash"
					id="paymentWith" />
				<div class="row">
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="tab-01">
							Thanh toán khi nhận hàng.</div>
						<div role="tabpanel" class="tab-pane" id="tab-02">
							<div class="row">
								<select class="form-control" style="margin-left: 10px"
									name="paymentType">
									<option value="Thanh toán bằng Paypal">Thanh toán bằng
										Paypal</option>
								</select>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="tab-03">
							<div class="row">
								<div class="col-md-4 mb-3">
									<label for="cc-name">Name on card</label> <input type="text"
										class="form-control" id="cc-name" name="cc-name"
										placeholder="Nhập loại thẻ" />
								</div>
								<div class="col-md-4 mb-3">
									<label for="cc-number">Credit card number</label> <input
										type="text" class="form-control" id="cc-number"
										name="cc-number" placeholder="Nhập mã số thẻ" />
								</div>
								<div class="col-md-4 mb-3">
									<label for="cc-expiration">Expiration</label> <input
										type="text" class="form-control" id="cc-expiration"
										name="cc-expiration" placeholder="Nhập hạn dùng" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<h4>Phương thức vận chuyển</h4>
					<select class="form-control"
						style="margin-left: 10px; width: auto;"
						onchange="Shop.selectShipment(value)" name="shipmentId">
						<c:forEach var="shipment" items="${shipments }">
							<option value="${shipment.id }">${shipment.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="row summary">
					<div class="col-8" style="align-self: center"></div>
					<div class="col-4" style="align-self: center">
						<h4 class="summary-left">
							Tổng tiền hàng <span class="summary-right" style="float: right"><fmt:formatNumber
									type="number" maxFractionDigits="3"
									value="${cart.totalAmount }" /> ₫</span>
						</h4>
						<h4 class="summary-left">
							Phí vận chuyển<span class="summary-right" style="float: right"
								id="shipment-price"><fmt:formatNumber type="number"
									maxFractionDigits="0" value="${shipments.get(0).price}" /> ₫</span>
						</h4>
						<h4 class="summary-left">
							Tổng thanh toán:<span class="summary-right red-color"
								style="float: right" id="payment-price"><fmt:formatNumber
									type="number" maxFractionDigits="0"
									value="${cart.totalAmount + shipments.get(0).price }" /> ₫</span>
						</h4>
						<button type="submit" class="btn btn-danger button-submit">
							Đặt hàng</button>
					</div>
				</div>
			</form>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<div style="text-align: center; width: 100%;">
							<h5 class="modal-title" id="exampleModalLabel"
								style="font-size: 25px; font-weight: 600">Sửa địa chỉ</h5>
						</div>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="required">Số nhà:</label> <input type="text"
								value="${customerLogin.address.number }" class="form-control"
								id="number" />
						</div>
						<div class="form-group">
							<label class="required">Tên đường:</label> <input type="text"
								value="${customerLogin.address.street }" class="form-control"
								id="street" />
						</div>
						<div class="form-group">
							<label class="required">Quận/Huyện:</label> <input type="text"
								value="${customerLogin.address.district }" class="form-control"
								id="district" />
						</div>
						<div class="form-group">
							<label class="required">Thành phố:</label> <input type="text"
								value="${customerLogin.address.city }" class="form-control"
								id="city" />
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="Shop.editAddress()">Lưu lại</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal" type="submit">Đóng</button>
					</div>
				</div>
			</div>
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