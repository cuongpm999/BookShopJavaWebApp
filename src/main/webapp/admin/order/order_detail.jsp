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
	href="${pageContext.request.contextPath}/static/css/order_detail.css">
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

			<div class="form-add">
				<div class="hoadon">
					<div class="row header_hoadon">
						<div class="col-md-3">
							<img src="${pageContext.request.contextPath}/static/img/logo.png"
								alt="logo">
						</div>
						<div class="col-md-6">
							<h2 style="text-align: center;">HÓA ĐƠN</h2>
							<h3>GIÁ TRỊ GIA TĂNG</h3>
							<h6 style="text-align: center;">Liên 1: Lưu</h6>
							<h6 style="text-align: center;">
								<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy"
									value="${order.dateCreate}" />
							</h6>
						</div>
						<div class="col-md-3 ma_hoadon">
							<h6 style="text-align: center;">Mẫu số: 01GTKT3/001</h6>
							<h6 style="text-align: center;">Kí hiệu: CM/17P</h6>
							<h6 style="text-align: center;">Số: 000000</h6>
						</div>
					</div>
					<hr>
					<div class="row congty_hoadon">
						<ul class="list-unstyled">
							<li><span class="chuthich">Đơn vị bán hàng:</span> <span
								class="congty" style="margin-left: 64px;">BOOK STORE ONLINE</span></li>
							<li><span class="chuthich">Mã số thuế:</span> <span
								class="thongtin" style="margin-left: 107px;">0123456789 -
									${order.id }</span></li>
							<li><span class="chuthich">Địa chỉ:</span> <span
								class="thongtin" style="margin-left: 144px;">19A Xa La,
									Hà Đông, Hà Nội</span></li>
							<li><span class="chuthich">Điện thoại:</span> <span
								class="thongtin" style="margin-left: 116px;">0961656067</span></li>
							<li><span class="chuthich">Số tài khoản:</span> <span
								class="thongtin" style="margin-left: 99px;">999969999</span></li>
						</ul>
					</div>
					<hr>
					<div class="row congty_hoadon">
						<ul class="list-unstyled">
							<li><span class="chuthich">Họ tên khách hàng:</span> <span
								class="tenkhach" style="margin-left: 45px;">${customer.fullName.lastName }
									${customer.fullName.middleName } ${customer.fullName.firstName }</span></li>
							<li><span class="chuthich">Email:</span> <span
								class="thongtin" style="margin-left: 155px;">${customer.email }</span></li>
							<li><span class="chuthich">Điện thoại:</span> <span
								class="thongtin" style="margin-left: 115px;">${customer.mobile }</span></li>
							<li><span class="chuthich">Địa chỉ:</span> <span
								class="thongtin" style="margin-left: 145px;">${customer.address.number },
									${customer.address.street }, ${customer.address.district },
									${customer.address.city }</span></li>
							<li><span class="chuthich">Đơn vị vận chuyển:</span> <span
								class="thongtin" style="margin-left: 50px;">${order.shipment.name }&emsp;<fmt:formatNumber
										type="number" maxFractionDigits="0"
										value="${order.shipment.price }" /> ₫
							</span></li>
							<li><span class="chuthich">Loại thanh toán:</span> <span
								class="thongtin" style="margin-left: 73px;"><c:choose>
										<c:when test="${not empty paymentTypeCash }">
											Thanh toán khi nhận hàng
										</c:when>
										<c:when test="${not empty paymentTypeDigitalWallet }">
											${paymentTypeDigitalWallet.name }
										</c:when>
										<c:when test="${not empty paymentTypeCredit }">
											Loại thẻ: ${paymentTypeCredit.type } - Mã thẻ: ${paymentTypeCredit.number } - Hạn dùng: ${paymentTypeCredit.date }
										</c:when>
									</c:choose> </span></li>
							<li><span class="chuthich">Tổng tiền tất cả:</span> <span
								class="thongtin"
								style="margin-left: 73px; color: red; font-weight: 700; font-size: 20px;"><fmt:formatNumber
										type="number" maxFractionDigits="0"
										value="${order.payment.totalMoney }" /> ₫ </span></li>
						</ul>
						<hr>

					</div>
					<div class="row">
						<table class="table">
							<thead class="thead-light">
								<tr>
									<th style="padding-left: 10%;">Sản phẩm</th>
									<th>Giá</th>
									<th>Số lượng</th>
									<th>Thành tiền</th>
									<th></th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${mapLineItem }" var="lineBookItem">
									<tr>
										<td class="tensanpham">
											<div class="row">
												<div class="col-md-5">
													<img
														src="https://res.cloudinary.com/cuongpham/image/upload/${lineBookItem.key.img }"
														alt="sanpham">
												</div>
												<div class="col-md-7">
													${lineBookItem.key.book.title } <br>
												</div>

											</div>
										</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="0"
												value="${lineBookItem.key.price*(100-lineBookItem.key.discount)/100 }" />&nbsp;₫</td>
										<td>${lineBookItem.value }</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="0"
												value="${(lineBookItem.key.price*(100-lineBookItem.key.discount)/100) * lineBookItem.value }" />&nbsp;₫</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<div class="card-body" style="text-align: right;">
							<div class="tongtienthanhtoan">
								Tổng tiền đơn hàng : <strong><span id="total_value"><fmt:formatNumber
											type="number" maxFractionDigits="0"
											value="${order.cart.totalAmount }" /> </span> ₫</strong>
							</div>
						</div>
					</div>

					<hr>
					<div class="row footer_hoadon">
						<div class="col-md-4">
							<h5 style="text-align: center;">Người mua hàng</h5>
							<h6 style="text-align: center;">(Kí, ghi rõ họ tên)</h6>

						</div>
						<div class="col-md-4">
							<h5 style="text-align: center;">Người bán hàng</h5>
							<h6 style="text-align: center;">(Kí, ghi rõ họ tên)</h6>

						</div>
						<div class="col-md-4">
							<h5 style="text-align: center;">Thủ trưởng đơn vị</h5>
							<h6 style="text-align: center;">(Kí, đóng dấu, ghi rõ họ
								tên)</h6>

						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/admin/footer_admin.jsp"%>
	</div>
	</div>
	<!-- ---- -->

</body>
</html>