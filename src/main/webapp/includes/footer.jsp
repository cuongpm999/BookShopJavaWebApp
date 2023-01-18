<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
	src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v8.0"
	nonce="ChmFo6ST"></script>

<!-- FOOTER -->
<div id="footer">
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<h5>CHÍNH HÃNG, UY TÍN, CHẤT LƯỢNG</h5>
				<ul>
					<li>
						<h4>BOOK STORE ONLINE</h4>
					</li>
					<li><a href="javascript:void(0);"><i
							class="fas fa-map-marker-alt"></i>&ensp;Address: PTIT, Hà Nội</a></li>
					<li><a href="javascript:void(0);"><i
							class="fas fa-envelope"></i>&ensp;Email:
							phamcuongth2000@gmail.com</a></li>
					<li><a href="javascript:void(0);"><i class="fas fa-phone"></i>&ensp;Mobile:
							0961656067</a></li>
				</ul>
			</div>
			<div class="col-md-3">
				<h5>SẢN PHẨM</h5>
				<ul>
					<li><a href="/BookStoreOnline/collections?category=Lịch sử truyền thống">Lịch sử truyền thống</a></li>
					<li><a href="/BookStoreOnline/collections?category=Kiến thức khoa học">Kiến thức khoa học</a></li>
					<li><a href="/BookStoreOnline/collections?category=Văn học Việt Nam">Văn học Việt Nam</a></li>
					<li><a href="/BookStoreOnline/collections?category=Văn học nước ngoài">Văn học nước ngoài</a></li>	
					<li><a href="/BookStoreOnline/collections?category=Truyện tranh">Truyện tranh</a></li>		
				</ul>
			</div>

			<div class="col-md-4">
				<h5>KẾT NỐI VỚI CHÚNG TÔI</h5>
				<div class="fb-page"
					data-href="https://www.facebook.com/Computer-CuongPham-110156041221389"
					data-tabs="timeline" data-width="300" data-height="230"
					data-small-header="false" data-adapt-container-width="true"
					data-hide-cover="false" data-show-facepile="true">
					<blockquote
						cite="https://www.facebook.com/Computer-CuongPham-110156041221389"
						class="fb-xfbml-parse-ignore">
						<a
							href="https://www.facebook.com/Computer-CuongPham-110156041221389">Computer
							CuongPham</a>
					</blockquote>
				</div>

			</div>

			<hr>
			<div class="footer-copyright text-center py-3" style="width: 100%;">
				©
				<script>
					var now = new Date();
					document.write(now.getFullYear());
				</script>
				Copyright: <a href="https://www.facebook.com/cuongphamptit999/">
					CuongPham.com</a>
			</div>
		</div>
	</div>
</div>
<!-- ------ -->

<div id="toTop">
	<img src="${pageContext.request.contextPath}/static/img/arrow5-up-512.png" id="onTop" />
</div>

<div id="hotline">
	<a href="tel:0961656067" class="call-icon" rel="nofollow"> <img
		src="${pageContext.request.contextPath}/static/img/icon-hotline.gif" id="hotline_img"></a>
</div>