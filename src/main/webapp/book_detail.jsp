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
	href="${pageContext.request.contextPath}/static/css/item-detail.css">
<!-- --- -->

<title>Book Store Online</title>
</head>


<body>

	<!-- HEADER -->
	<%@ include file="/includes/header.jsp"%>
	<!-- ------ -->

	<div id="main" class="container ">
		<div class="information">
			<div class="row">
				<div class="detail-img col-md-5">
					<img
						src="https://res.cloudinary.com/cuongpham/image/upload/${bookItem.img }"
						alt="product">
					<div class="list-img">
						<img
							src="https://res.cloudinary.com/cuongpham/image/upload/${bookItem.img }"
							alt="product">
					</div>
				</div>

				<div class="col-md-7" style="margin-left: -55px;">
					<div>
						<h4>${bookItem.book.title }</h4>
					</div>
					<div style="margin-top: 30px;">
						<table class="table" style="width: 400px">
							<tbody>
								<tr>
									<td>Thể loại</td>
									<td>${bookItem.book.category }</td>
								</tr>
								<tr>
									<td>Tác giả</td>
									<td><c:forEach items="${bookItem.book.authors }"
											var="author" varStatus="loop">
											<c:choose>
												<c:when
													test="${loop.index < bookItem.book.authors.size() - 1 }">${author.name },&nbsp;</c:when>
												<c:when
													test="${loop.index == bookItem.book.authors.size() - 1 }">${author.name }</c:when>
											</c:choose>
										</c:forEach></td>
								</tr>
								<tr>
									<td>Nhà xuất bản</td>
									<td>${bookItem.book.publisher.name }</td>
								</tr>
								<tr>
									<td>Số trang</td>
									<td>${bookItem.book.pages }</td>
								</tr>
								<tr>
									<td>Ngôn ngữ</td>
									<td>${bookItem.book.language }</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div style="display: flex; align-items: center">
						<h3 class="price" style="color: #d51c24; margin-right: 20px;">
							<fmt:formatNumber type="number" maxFractionDigits="0"
								value="${bookItem.price*(100-bookItem.discount)/100 }" />
							₫
						</h3>
						<c:choose>
							<c:when test="${bookItem.discount > 0}">
								<h4
									style="text-decoration: line-through; line-height: 35px; color: #666; margin-right: 20px;">
									<fmt:formatNumber type="number" maxFractionDigits="0"
										value="${bookItem.price }" />
									₫
								</h4>
								<span style="color: red; font-size: 15px">(Bạn tiết kiệm
									được ${bookItem.discount } %)</span>
							</c:when>
						</c:choose>
					</div>
					<div class="table-mid" style="margin-top: 20px">
						<div class="button" style="display: flex">
							<a
								href="javascript:Shop.addToCart('${bookItem.barCode}');"
								class="btn1"
								style="background-color: #ffeee8; line-height: 46px; margin-right: 40px; color: #ee4d2d;">
								<i class="fas fa-cart-plus"></i> Thêm vào giỏ hàng
							</a> <a
								href="javascript:Shop.addToCartNow('${bookItem.barCode}','book');"
								class="btn2"
								style="line-height: 46px; color: white; font-size: 16px; font-weight: 600;">MUA
								NGAY</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="book">
			<h4 class="mb-3">Mô tả đánh giá</h4>
			<div
				style="font-size: 15px">
				${bookItem.book.summary}</div>
		</div>

		<div class="book">
			<h4 class="mb-4">Các sản phẩm tương tự</h4>
			<div class="row">
				<c:forEach var="bookItem" items="${bookItemSames }" varStatus="loop">
					<c:if test="${loop.index <4 }">
						<div class="col-md-3">
							<div style="text-align: center; width: 100%;">
								<a href="/BookStoreOnline/book/detail?barCode=${bookItem.barCode }"><img
									src="https://res.cloudinary.com/cuongpham/image/upload/${bookItem.img }"
									alt="${bookItem.book.title }"></a>
							</div>
							<div class="infor" style="text-align: center;">
								<a href="/BookStoreOnline/book/detail?barCode=${bookItem.barCode }">
									<h6>${bookItem.book.title }</h6>
								</a>
								<c:choose>

									<c:when test="${bookItem.discount > 0}">
										<div class="gia-goc">
											<p class="gia-chinh">
												<fmt:formatNumber type="number" maxFractionDigits="0"
													value="${bookItem.price }" />
												₫
											</p>
											<p class="khuyen-mai">(Tiết kiệm: ${bookItem.discount}%)</p>
										</div>
									</c:when>
								</c:choose>
								<h6 class="gia-ban">
									<fmt:formatNumber type="number" maxFractionDigits="0"
										value="${bookItem.price*(100-bookItem.discount)/100 }" />
									₫
								</h6>
								<a href="javascript:Shop.addToCart('${bookItem.barCode}','book');"><i
									class="fas fa-shopping-cart"></i>&nbsp;Mua ngay</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div> 

		<div class="book">
			<h4 class="mb-3">Hỏi đáp về sản phẩm</h4>
			<div class="comment-fb">
				<div class="fb-comments"
					data-href="https://developers.facebook.com/docs/plugins/${bookItem.barCode}"
					data-width="100%" data-numposts="5"></div>
			</div>
		</div>
	</div>
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