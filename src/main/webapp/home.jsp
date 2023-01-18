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

<body>
	<!-- HEADER -->
	<%@ include file="/includes/header.jsp"%>
	<!-- ------ -->

	<!-- MAIN  -->
	<div id="banner">
		<div id="demo" class="carousel slide" data-ride="carousel">

			<ul class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
				<li data-target="#demo" data-slide-to="3"></li>
			</ul>

			<div class="carousel-inner">
				<div class="carousel-item active">
					<img
						src="${pageContext.request.contextPath}/static/img/banner1.webp"
						alt="Banner">
				</div>
				<div class="carousel-item">
					<img
						src="${pageContext.request.contextPath}/static/img/banner2.webp"
						alt="Banner">
				</div>
				<div class="carousel-item">
					<img
						src="${pageContext.request.contextPath}/static/img/banner3.webp"
						alt="Banner">
				</div>
				<div class="carousel-item">
					<img
						src="${pageContext.request.contextPath}/static/img/banner4.webp"
						alt="Banner">
				</div>
			</div>

			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#demo" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>

		</div>
	</div>

	<div id="main">
		<div class="container">

			<div class="book">
				<h3 class="tieu-de" style="margin-top: 50px; text-align: center;">LỊCH
					SỬA TRUYỀN THỐNG</h3>
				<div class="row">
					<c:forEach var="bookItem" items="${bookItems1 }" varStatus="loop">
						<c:if test="${loop.index <4 }">
							<div class="col-md-3">
								<div style="text-align: center;">
									<a href="/BookStoreOnline/book/detail?barCode=${bookItem.barCode }"><img
										src="https://res.cloudinary.com/cuongpham/image/upload/${bookItem.img }"
										alt="product"></a>
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
									<a
										href="javascript:Shop.addToCart('${bookItem.barCode}');"><i
										class="fas fa-shopping-cart"></i>&nbsp;Mua ngay</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>

			<div class="book">
				<h3 class="tieu-de" style="margin-top: 20px; text-align: center;">KIẾN
					THỨC KHOA HỌC</h3>
				<div class="row">
					<c:forEach var="bookItem" items="${bookItems2 }" varStatus="loop">
						<c:if test="${loop.index <4 }">
							<div class="col-md-3">
								<div style="text-align: center;">
									<a href="/BookStoreOnline/book/detail?barCode=${bookItem.barCode }"><img
										src="https://res.cloudinary.com/cuongpham/image/upload/${bookItem.img }"
										alt="product"></a>
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
									<a
										href="javascript:Shop.addToCart('${bookItem.barCode}');"><i
										class="fas fa-shopping-cart"></i>&nbsp;Mua ngay</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>

			<div class="book">
				<h3 class="tieu-de" style="margin-top: 20px; text-align: center;">VĂN HỌC VIỆT NAM</h3>
				<div class="row">
					<c:forEach var="bookItem" items="${bookItems3 }" varStatus="loop">
						<c:if test="${loop.index <4 }">
							<div class="col-md-3">
								<div style="text-align: center;">
									<a href="/BookStoreOnline/book/detail?barCode=${bookItem.barCode }"><img
										src="https://res.cloudinary.com/cuongpham/image/upload/${bookItem.img }"
										alt="product"></a>
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
									<a
										href="javascript:Shop.addToCart('${bookItem.barCode}');"><i
										class="fas fa-shopping-cart"></i>&nbsp;Mua ngay</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			
			<div class="book">
				<h3 class="tieu-de" style="margin-top: 20px; text-align: center;">VĂN HỌC NƯỚC NGOÀI</h3>
				<div class="row">
					<c:forEach var="bookItem" items="${bookItems4 }" varStatus="loop">
						<c:if test="${loop.index <4 }">
							<div class="col-md-3">
								<div style="text-align: center;">
									<a href="/BookStoreOnline/book/detail?barCode=${bookItem.barCode }"><img
										src="https://res.cloudinary.com/cuongpham/image/upload/${bookItem.img }"
										alt="product"></a>
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
									<a
										href="javascript:Shop.addToCart('${bookItem.barCode}');"><i
										class="fas fa-shopping-cart"></i>&nbsp;Mua ngay</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			
			<div class="book">
				<h3 class="tieu-de" style="margin-top: 20px; text-align: center;">TRUYỆN TRANH</h3>
				<div class="row">
					<c:forEach var="bookItem" items="${bookItems5 }" varStatus="loop">
						<c:if test="${loop.index <4 }">
							<div class="col-md-3">
								<div style="text-align: center;">
									<a href="/BookStoreOnline/book/detail?barCode=${bookItem.barCode }"><img
										src="https://res.cloudinary.com/cuongpham/image/upload/${bookItem.img }"
										alt="product"></a>
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
									<a
										href="javascript:Shop.addToCart('${bookItem.barCode}');"><i
										class="fas fa-shopping-cart"></i>&nbsp;Mua ngay</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
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