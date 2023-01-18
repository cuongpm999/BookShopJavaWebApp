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

<!-- CSS & JS -->
<%@ include file="/includes/css_js.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/category.css">
<!-- --- -->

<title>Book Store Online</title>
</head>

<body>

	<!-- HEADER -->
	<%@ include file="/includes/header.jsp"%>
	<!-- ------ -->

	<!-- MAIN -->
	<div id="main" class="container all-item">
		<h4 class="tieude-tp">DANH MỤC SÁCH</h4>
		<div class="loc-sp">
			<span class="title-x">LỌC SẢN PHẨM</span>
			<div class="danh-muc">
				<span class="title">DANH MỤC</span>
				<ul class="list-unstyled">
					<li><a href="/BookStoreOnline/collections"><i
							class="fas fa-angle-double-right"></i> Tất cả</a></li>
					<li><a
						href="javascript:Shop.addUrlParameter('category', 'Lịch sử truyền thống')"><i
							class="fas fa-angle-double-right"></i> Lịch sử truyền thống</a></li>
					<li><a
						href="javascript:Shop.addUrlParameter('category', 'Kiến thức khoa học')"><i
							class="fas fa-angle-double-right"></i> Kiến thức khoa học</a></li>
					<li><a
						href="javascript:Shop.addUrlParameter('category', 'Văn học Việt Nam')"><i
							class="fas fa-angle-double-right"></i> Văn học Việt Nam</a></li>
					<li><a
						href="javascript:Shop.addUrlParameter('category', 'Văn học nước ngoài')"><i
							class="fas fa-angle-double-right"></i> Văn học nước ngoài</a></li>
					<li><a
						href="javascript:Shop.addUrlParameter('category', 'Truyện tranh')"><i
							class="fas fa-angle-double-right"></i> Truyện tranh</a></li>
				</ul>
			</div>

			<div class="danh-muc">
				<span class="title">TÁC GIẢ</span>
				<ul class="list-unstyled">
					<c:choose>
						<c:when test="${empty author_ }">
							<c:forEach var="author" items="${authors }" varStatus="loop">
								<c:if test="${loop.index < 6 }">
									<li><a href="javascript:void(0);"
										onclick="Shop.addUrlParameter('author_', ${author.id })"><i
											class="far fa-square"></i> ${author.name }</a></li>
								</c:if>
							</c:forEach>
						</c:when>
						<c:when test="${not empty author_ }">
							<c:forEach var="author" items="${authors }" varStatus="loop">
								<c:if test="${author_== author.id }">
									<li><a href="javascript:void(0);"
										onclick="Shop.deleteUrlParameter('author_')"><i
											class="far fa-check-square"></i> ${author.name }</a></li>
								</c:if>
							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</div>
			<div class="danh-muc">
				<span class="title">NHÀ XUẤT BẢN</span>
				<ul class="list-unstyled">
					<c:choose>
						<c:when test="${empty publisher_ }">
							<c:forEach var="publisher" items="${publishers }"
								varStatus="loop">
								<c:if test="${loop.index < 6 }">
									<li><a href="javascript:void(0);"
										onclick="Shop.addUrlParameter('publisher_', ${publisher.id })"><i
											class="far fa-square"></i> ${publisher.name }</a></li>
								</c:if>
							</c:forEach>
						</c:when>

						<c:when test="${not empty publisher_ }">
							<c:forEach var="publisher" items="${publishers }"
								varStatus="loop">
								<c:if test="${publisher_== publisher.id }">
									<li><a href="javascript:void(0);"
										onclick="Shop.deleteUrlParameter('publisher_')"><i
											class="far fa-check-square"></i> ${publisher.name }</a></li>
								</c:if>
							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</div>
			<div class="khoang-gia">
				<span class="title">KHOẢNG GIÁ</span>
				<ul class="list-unstyled">
					<c:choose>
						<c:when test="${empty price }">
							<li><a href="javascript:void(0);"
								onclick="Shop.addUrlParameter('price', 'duoi50')"><i
									class="far fa-square"></i> Dưới 50.000 ₫</a></li>
							<li><a href="javascript:void(0);"
								onclick="Shop.addUrlParameter('price', '50den100')"><i
									class="far fa-square"></i> Từ 50.000 đ đến 100.000 ₫</a></li>
							<li><a href="javascript:void(0);"
								onclick="Shop.addUrlParameter('price', '100den200')"><i
									class="far fa-square"></i> Từ 100.000 ₫ đến 200.000 ₫</a></li>
							<li><a href="javascript:void(0);"
								onclick="Shop.addUrlParameter('price', '200den300')"><i
									class="far fa-square"></i> Từ 200.000 ₫ đến 300.000 ₫</a></li>
							<li><a href="javascript:void(0);"
								onclick="Shop.addUrlParameter('price', 'tren300')"><i
									class="far fa-square"></i> Trên 300.000 ₫</a></li>
						</c:when>

						<c:when test="${price=='duoi50' }">
							<li><a href="javascript:void(0);"
								onclick="Shop.deleteUrlParameter('price')"><i
									class="far fa-check-square"></i> Dưới 50.000 ₫</a></li>
						</c:when>
						
						<c:when test="${price=='50den100' }">
							<li><a href="javascript:void(0);"
								onclick="Shop.deleteUrlParameter('price')"><i
									class="far fa-check-square"></i> Từ 50.000 ₫ đến 100.000 ₫</a></li>
						</c:when>

						<c:when test="${price=='100den200' }">
							<li><a href="javascript:void(0);"
								onclick="Shop.deleteUrlParameter('price')"><i
									class="far fa-check-square"></i> Từ 100.000 ₫ đến 200.000 ₫</a></li>
						</c:when>
						
						<c:when test="${price=='200den300' }">
							<li><a href="javascript:void(0);"
								onclick="Shop.deleteUrlParameter('price')"><i
									class="far fa-check-square"></i> Từ 200.000 ₫ đến 300.000 ₫</a></li>
						</c:when>

						<c:when test="${price=='tren300' }">
							<li><a href="javascript:void(0);"
								onclick="Shop.deleteUrlParameter('price')"><i
									class="far fa-check-square"></i> Trên 300.000 ₫</a></li>
						</c:when>

					</c:choose>
				</ul>
			</div>

		</div>

		<div class="product-sp">
			<div class="product-list">
				<div class="list-sort" style="margin-bottom: 10px;">
					<c:choose>
						<c:when test="${sort == '' || empty sort }">
							<select id="sort-select"
								onchange="Shop.addUrlParameter('sort', this.value)">
								<option value="" selected>Sắp xếp sản phẩm</option>
								<option value="low-to-high">Giá tăng dần</option>
								<option value="high-to-low">Giá giảm dần</option>
								<option value="moi-nhat">Mới nhất</option>
							</select>
						</c:when>

						<c:when test="${sort == 'low-to-high' }">
							<select id="sort-select"
								onchange="Shop.addUrlParameter('sort', this.value)">
								<option value="">Sắp xếp sản phẩm</option>
								<option value="low-to-high" selected>Giá tăng dần</option>
								<option value="high-to-low">Giá giảm dần</option>
								<option value="moi-nhat">Mới nhất</option>
							</select>
						</c:when>

						<c:when test="${sort == 'high-to-low' }">
							<select id="sort-select"
								onchange="Shop.addUrlParameter('sort', this.value)">
								<option value="">Sắp xếp sản phẩm</option>
								<option value="low-to-high">Giá tăng dần</option>
								<option value="high-to-low" selected>Giá giảm dần</option>
								<option value="moi-nhat">Mới nhất</option>
							</select>
						</c:when>
						
						<c:when test="${sort == 'moi-nhat' }">
							<select id="sort-select"
								onchange="Shop.addUrlParameter('sort', this.value)">
								<option value="">Sắp xếp sản phẩm</option>
								<option value="low-to-high">Giá tăng dần</option>
								<option value="high-to-low">Giá giảm dần</option>
								<option value="moi-nhat" selected>Mới nhất</option>
							</select>
						</c:when>
					</c:choose>

					<c:choose>
						<c:when test="${state == '' || empty state }">
							<select id="sort-select"
								onchange="Shop.addUrlParameter('state', this.value)">
								<option value="" selected>Tất cả</option>
								<option value="khuyen-mai">Khuyến mại</option>
							</select>
						</c:when>

						<c:when test="${state == 'khuyen-mai'}">
							<select id="sort-select"
								onchange="Shop.addUrlParameter('state', this.value)">
								<option value="">Tất cả</option>
								<option value="khuyen-mai" selected>Khuyến mại</option>
							</select>
						</c:when>
					</c:choose>

				</div>

				<div class="list-book">
					<div class="row">
						<c:forEach var="bookItem" items="${bookItems }">
							<div class="col-md-3">
								<div style="text-align: center; width: 100%;">
									<a
										href="/BookStoreOnline/book/detail?barCode=${bookItem.barCode }"><img
										src="https://res.cloudinary.com/cuongpham/image/upload/${bookItem.img }"
										alt="${bookItem.book.title }"></a>
								</div>
								<div class="infor" style="text-align: center;">
									<a
										href="/BookStoreOnline/book/detail?barCode=${bookItem.barCode }">
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
									<a href="javascript:Shop.addToCart('${bookItem.barCode}');"><i
										class="fas fa-shopping-cart"></i>&nbsp;Mua ngay</a>
								</div>
							</div>

						</c:forEach>
					</div>
				</div>

				<div class="text-center" style="margin-top: 40px;">
					<ul class="page-numbers">
						<li><a class="prev page-numbers"
							href="javascript:Shop.goPrev()"><i class="fas fa-angle-left"></i></a></li>
						<li><c:choose>
								<c:when test="${page != 1 && not empty page}">
									<a class="page-numbers"
										href="javascript:Shop.addUrlParameter('page', 1)">1</a>
								</c:when>
								<c:when test="${page == 1 || empty page}">
									<span aria-current="page" class="page-numbers current">1</span>
								</c:when>
							</c:choose></li>
						<li><c:choose>
								<c:when test="${page != 2}">
									<a class="page-numbers"
										href="javascript:Shop.addUrlParameter('page', 2)">2</a>
								</c:when>
								<c:when test="${page == 2}">
									<span aria-current="page" class="page-numbers current">2</span>
								</c:when>
							</c:choose></li>

						<li><c:choose>
								<c:when test="${page != 3}">
									<a class="page-numbers"
										href="javascript:Shop.addUrlParameter('page', 3)">3</a>
								</c:when>
								<c:when test="${page == 3}">
									<span aria-current="page" class="page-numbers current">3</span>
								</c:when>
							</c:choose></li>

						<li><c:choose>
								<c:when test="${page != 4}">
									<a class="page-numbers"
										href="javascript:Shop.addUrlParameter('page', 4)">4</a>
								</c:when>
								<c:when test="${page == 4}">
									<span aria-current="page" class="page-numbers current">4</span>
								</c:when>
							</c:choose></li>

						<li><c:choose>
								<c:when test="${page != 5}">
									<a class="page-numbers"
										href="javascript:Shop.addUrlParameter('page', 5)">5</a>
								</c:when>
								<c:when test="${page == 5}">
									<span aria-current="page" class="page-numbers current">5</span>
								</c:when>
							</c:choose></li>
						<li><a class="next page-numbers"
							href="javascript:Shop.goNext()"><i class="fas fa-angle-right"></i></a></li>
					</ul>
				</div>



			</div>
		</div>

	</div>
	<div class="clear-with-height"></div>
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
