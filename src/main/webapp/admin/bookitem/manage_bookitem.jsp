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
							placeholder="Search.."> <a
							href="/BookStoreOnline/admin/manage/book-item/add"
							class="btn btn-success" style="margin-top: 10px"><i
							class="fas fa-plus-square"></i> Add</a>

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
									<th></th>
								</tr>
							</thead>
							<tbody id="myTable">
								<c:forEach items="${bookItems }" var="bookItem">
									<tr>
										<td>${bookItem.barCode }</td>
										<td>${bookItem.book.title }</td>
										<td>${bookItem.book.category }</td>
										<td>${bookItem.book.publisher.name }</td>
										<td><c:forEach items="${bookItem.book.authors }"
												var="author" varStatus="loop">
												<c:choose>
													<c:when
														test="${loop.index < bookItem.book.authors.size() - 1 }">${author.name },&nbsp;</c:when>
													<c:when
														test="${loop.index == bookItem.book.authors.size() - 1 }">${author.name }</c:when>
												</c:choose>
											</c:forEach></td>
										<td><fmt:formatNumber type="number" maxFractionDigits="0"
												value="${bookItem.price }" /> ₫</td>
										<td>${bookItem.discount } %</td>
										<td><a
											href="/BookStoreOnline/admin/manage/book-item/edit?barCode=${bookItem.barCode }"
											class="btn btn-primary"><i class="fas fa-edit"></i> Edit</a>
											<a href="javascript:void(0);"
											onclick="Shop.delete('/BookStoreOnline/admin/manage/book-item/delete?barCode=${bookItem.barCode }')"
											class="btn btn-danger"><i class="fas fa-eraser"></i>
												Delete</a></td>
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
