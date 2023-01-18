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
				<div class="col-md-3">
					<div class="r3_counter_box">
						<img
							src="${pageContext.request.contextPath}/static/img/icons8-money-100.png"
							alt="manage">
						<div class="stats">
							<h5>
								<strong> <c:choose>
										<c:when test="${totalMoney>99999999999 }">
											<fmt:formatNumber type="number" maxFractionDigits="0"
												value="${totalMoney/1000000000}" /> tỷ
											</c:when>
										<c:when test="${totalMoney<=99999999999 }">
											<fmt:formatNumber type="number" maxFractionDigits="0"
												value="${totalMoney}" />
										</c:when>

									</c:choose>
								</strong>
							</h5>
							<span>Total Revenue</span>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="r3_counter_box">
						<img
							src="${pageContext.request.contextPath}/static/img/icons8-user-male-100.png"
							alt="manage">
						<div class="stats">
							<h5>
								<strong> <fmt:formatNumber type="number"
										maxFractionDigits="0" value="${totalCustomer }" />
								</strong>
							</h5>
							<span>Total Customers</span>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="r3_counter_box">
						<img
							src="${pageContext.request.contextPath}/static/img/icons8-laptop-100.png"
							alt="manage">
						<div class="stats">
							<h5>
								<strong> <fmt:formatNumber type="number"
										maxFractionDigits="0" value="${totalOrder }" />
								</strong>
							</h5>
							<span>Total Orders</span>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="r3_counter_box">
						<img
							src="${pageContext.request.contextPath}/static/img/icons8-book-100.png"
							alt="manage">
						<div class="stats">
							<h5>
								<strong> <fmt:formatNumber type="number"
										maxFractionDigits="0" value="${totalBookItem}" />
								</strong>
							</h5>
							<span>Total Book Item</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-7 chart">
					<div class="charts">
						<canvas id="myChart"></canvas>
					</div>
				</div>
				<div class="col-md-5">
					<div class="table-product scrollbar1" id="style-1">
						<table class="table">
							<thead style="background-color: #03a9f4; color: white;">
								<tr>
									<th>#</th>
									<th>Name</th>
									<th>Amount</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itemStat" items="${itemStats }" varStatus="loop">
									<tr>
										<td>${loop.index + 1 }</td>
										<td>${itemStat.book.title }</td>
										<td>${itemStat.quantityBought }</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
				<input type="hidden" value="${chartReport.label[0]}" id="input0">
				<input type="hidden" value="${chartReport.label[1]}" id="input1">
				<input type="hidden" value="${chartReport.label[2]}" id="input2">
				<input type="hidden" value="${chartReport.label[3]}" id="input3">
				<input type="hidden" value="${chartReport.label[4]}" id="input4">
				<input type="hidden" value="${chartReport.label[5]}" id="input5">
				<script>
     					var datas=[${chartReport.data[0]}, ${chartReport.data[1]}, ${chartReport.data[2]}, ${chartReport.data[3]}, ${chartReport.data[4]}, ${chartReport.data[5]}];
     					var labels=[$('#input0').val(), $('#input1').val(), $('#input2').val(), $('#input3').val(), $('#input4').val(), $('#input5').val()];
     					
                    	const data = {
                        	labels: labels,
                        	datasets: [{
                            label: 'Thống kê tổng thu nhập theo tháng',
                            backgroundColor: '#03a9f4',
                            borderColor: '#72777a',
                            data: datas,
                        	}]
                   		};
     					const config = {
                            type: 'line',
                            data,
                            options: {}
                    	};
                      	var myChart = new Chart(
                            document.getElementById('myChart'),
                            config
                        );
                        
                    </script>
			</div>

			<div class="row">
				<div class="col-md-6">
					<iframe
						src="https://calendar.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23ffffff&amp;ctz=Asia%2FHo_Chi_Minh&amp;src=cGhhbWN1b25ndGgyMDAwQGdtYWlsLmNvbQ&amp;src=Y2xhc3Nyb29tMTE0Mzk5MjQ3MTIwMjM5NTc0MTk4QGdyb3VwLmNhbGVuZGFyLmdvb2dsZS5jb20&amp;src=YWRkcmVzc2Jvb2sjY29udGFjdHNAZ3JvdXAudi5jYWxlbmRhci5nb29nbGUuY29t&amp;src=Y2xhc3Nyb29tMTA2NDAwMzExNDc2NDc2Njk0OTk1QGdyb3VwLmNhbGVuZGFyLmdvb2dsZS5jb20&amp;color=%23039BE5&amp;color=%230047a8&amp;color=%2333B679&amp;color=%23007b83"
						width="100%" height="600px" frameborder="0" scrolling="no"></iframe>
				</div>
				<div class="col-md-6">
					<iframe
						src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3619.614774027218!2d105.78734215530791!3d20.96544475848515!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ad29b81cac63%3A0xe5796c9507084b90!2zMTlhIFBo4buRIFhhIExhLCBQLiBQaMO6YyBMYSwgSMOgIMSQw7RuZywgSMOgIE7hu5lpIDEyMTA4!5e0!3m2!1svi!2s!4v1619148320132!5m2!1svi!2s"
						width="100%" height="600px" style="border: 0;" allowfullscreen=""
						loading="lazy"></iframe>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 slide-view">
					<div id="demo" class="carousel slide" data-ride="carousel">

						<ul class="carousel-indicators">
							<li data-target="#demo" data-slide-to="0" class="active"></li>
							<li data-target="#demo" data-slide-to="1"></li>
						</ul>

						<div class="carousel-inner">
							<div class="carousel-item active">
								<div class="row">
									<div class="col-md-4">
										<img
											src="${pageContext.request.contextPath}/static/img/slider1.jpg"
											alt="slide">
									</div>
									<div class="col-md-4">
										<img
											src="${pageContext.request.contextPath}/static/img/slider2.jpg"
											alt="slide">
									</div>
									<div class="col-md-4">
										<img
											src="${pageContext.request.contextPath}/static/img/slider3.jpg"
											alt="slide">
									</div>
								</div>

							</div>
							<div class="carousel-item">
								<div class="row">
									<div class="col-md-4">
										<img
											src="${pageContext.request.contextPath}/static/img/slider4.jpg"
											alt="slide">
									</div>
									<div class="col-md-4">
										<img
											src="${pageContext.request.contextPath}/static/img/slider5.jpg"
											alt="slide">
									</div>
									<div class="col-md-4">
										<img
											src="${pageContext.request.contextPath}/static/img/slider6.jpg"
											alt="slide">
									</div>
								</div>
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

</html>