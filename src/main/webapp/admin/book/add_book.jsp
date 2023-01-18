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
	
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js"></script>
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
						<c:choose>
							<c:when test="${status=='failed'}">
								<div class="alert alert-danger">
									<strong>Faile!</strong> Bạn đã thêm thất bại!

								</div>
							</c:when>
						</c:choose>
						<div class="card-body">
							<div class="panel-body">
								<form action="/BookStoreOnline/admin/manage/book/add"
									method="post" id="validateForm">
									<div class="form-group">
										<label class="required">Author: </label>
										<div style="height: 100px; overflow-y: scroll;">
											<c:forEach var="author" items="${authors }">
												<label
													style="display: block; color: #495057; font-size: 16px;">
													<input name="authors" type="checkbox" value="${author.id }">
													${author.name }
												</label>
											</c:forEach>
										</div>
									</div>
									<div class="form-group">
										<label class="required">Publisher: </label> <select
											class="form-control" name="publisherId">
											<c:forEach var="publisher" items="${publishers }">
												<option value="${publisher.id }">${publisher.name }</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label class="required">Title: </label> <input name="title"
											class="form-control" value="${book.title }" placeholder="Nhập tên sách"/>
									</div>
									<div class="form-group">
										<label class="required">Summary: </label>
										<textarea name="summary" class="form-control" rows="5"
											style="width: 100%;" placeholder="Nhập tóm tắt">${book.summary }</textarea>
									</div>
									<div class="form-group">
										<label class="required">Category: </label> <input name="category"
											class="form-control" value="${book.category }" placeholder="Nhập thể loại"/>
									</div>
									<div class="form-group">
										<label class="required">Pages: </label> <input name="pages"
											class="form-control" type="number" value="${book.pages }" placeholder="Nhập số trang"/>
									</div>
									<div class="form-group">
										<label class="required">Language: </label> <input
											name="language" class="form-control" value="${book.language }" placeholder="Nhập ngôn ngữ"/>
									</div>

									<button type="submit" class="btn btn-success">
										<i class="fas fa-download"></i> Save
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/admin/footer_admin.jsp"%>
	</div>
	</div>

</body>

<script type="text/javascript">
	$().ready(function() {
		$('#validateForm').validate({
			rules : {
				authors : "required",
				title : "required",
				summary : "required",
				category : "required",
				language : "required",	
				pages : "required",
			},
			messages : {
				authors : "Please select author",
				title : "Please enter title book",
				summary : "Please enter summary book",
				category : "Please enter category book",
				language : "Please enter language book",
				pages : "Please enter pages book",
				
			},
		})
	})
</script>

<style>
.error {
	font-weight: 400 !important;
	color: red;
	font-size: 15px !important;
}
</style>

</html>