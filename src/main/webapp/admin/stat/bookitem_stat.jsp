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
									<th>BarCode</th>
									<th style="width: 180px;">Title</th>
									<th style="width: 150px;">Category</th>
									<th>Publisher</th>
									<th style="width: 180px;">Author</th>
									<th>Price</th>
									<th>Discount</th>
									<th>Quantity bought</th>
								</tr>
							</thead>
							<tbody id="myTable">
								<c:forEach items="${bookItemStats }" var="bookItemStat">
									<tr>
										<td>${bookItemStat.barCode }</td>
										<td>${bookItemStat.book.title }</td>
										<td>${bookItemStat.book.category }</td>
										<td>${bookItemStat.book.publisher.name }</td>
										<td><c:forEach items="${bookItemStat.book.authors }"
												var="author" varStatus="loop">
												<c:choose>
													<c:when test="${loop.index < bookItemStat.book.authors.size() - 1 }">${author.name },&nbsp;</c:when>
													<c:when test="${loop.index == bookItemStat.book.authors.size() - 1 }">${author.name }</c:when>
												</c:choose>
											</c:forEach></td>
										<td><fmt:formatNumber type="number" maxFractionDigits="0"
												value="${bookItemStat.price }" /> ₫</td>
										<td>${bookItemStat.discount } %</td>
										<td>${bookItemStat.quantityBought }</td>
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
