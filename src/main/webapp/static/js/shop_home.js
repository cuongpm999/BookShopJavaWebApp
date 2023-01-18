$(document).ready(function() {
	// jquery cho onTop
	$(document).scroll(function() {
		if ($(document).scrollTop() != 0) {
			$("#onTop").fadeIn();
		} else {
			$("#onTop").fadeOut();
		}
	});
	$("#onTop").click(function() {
		$("html, body").animate({ scrollTop: 0 }, 700);
	});

	$("#myInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#myTable tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});

});

var Shop = {

	addToCart: function(barCode) {
		var data = {};
		data["barCode"] = barCode;

		$.ajax({
			url: "/BookStoreOnline/rest/api/cart/addToCart",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),

			dataType: "json",
			success: function(jsonResult) {
				if (jsonResult == null) {
					location.href = "/BookStoreOnline/login";
				} else {
					alert("Bạn đã thêm hàng thành công");
					$("span.count-item").html(jsonResult.totalQuantity);
				}

			}
		});
	},

	addToCartNow: function(barCode) {
		var data = {};
		data["barCode"] = barCode;

		$.ajax({
			url: "/BookStoreOnline/rest/api/cart/addToCart",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),

			dataType: "json",
			success: function(jsonResult) {
				if (jsonResult == null) {
					location.href = "/BookStoreOnline/login";
				} else {
					location.href = "/BookStoreOnline/cart";
					$("span.count-item").html(jsonResult.totalQuantity);
				}
			}
		});
	},

	deleteCart: function(barCode) {
		var flag = confirm("Bạn có chắc chắn muốn xóa sản phẩm này khỏi giỏ hàng?");
		if (flag == true) {
			var data = {};
			data["barCode"] = barCode;

			$.ajax({
				url: "/BookStoreOnline/rest/api/cart/deleteCart",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),

				dataType: "json",
				success: function(jsonResult) {
					if (jsonResult == 6000) {
						location.href = "/BookStoreOnline/login";
					} else {
						location.href = "/BookStoreOnline/cart";
					}
				}
			});
		}
	},

	editCart: function(barCode) {
		var quantity = $("#quantity" + barCode).val();
		var data = {};
		data["quantity"] = quantity;
		data["barCode"] = barCode;

		$.ajax({
			url: "/BookStoreOnline/rest/api/cart/editCart",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),

			dataType: "json",
			success: function(jsonResult) {
				if (jsonResult == null) {
					location.href = "/BookStoreOnline/login";
				} else {
					$("span.count-item").html(jsonResult[0]);
					$("#price" + barCode).html(jsonResult[1] + " ₫");
					$("#total_value").html(jsonResult[2]);
				}
			}
		});
	},

	goNext() {
		var tech = Shop.getUrlParameter('page') || 1;
		Shop.addUrlParameter('page', (parseInt(tech) + 1));
	},

	getUrlParameter: function(sParam) {
		var sPageURL = window.location.search.substring(1),
			sURLVariables = sPageURL.split('&'),
			sParameterName,
			i;

		for (i = 0; i < sURLVariables.length; i++) {
			sParameterName = sURLVariables[i].split('=');

			if (sParameterName[0] === sParam) {
				return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
			}
		}
	},

	goPrev() {
		var tech = Shop.getUrlParameter('page') || 1;
		if (parseInt(tech) > 1)
			Shop.addUrlParameter('page', (parseInt(tech) - 1));

	},

	addUrlParameter(name, value) {
		var searchParams = new URLSearchParams(window.location.search);
		searchParams.set(name, value);
		window.location.search = searchParams.toString();
	},

	deleteUrlParameter(name) {
		var searchParams = new URLSearchParams(window.location.search);
		searchParams.delete(name);
		window.location.search = searchParams.toString();
	},

	selectShipment: function(idShipment) {
		var data = {};
		data["idShipment"] = idShipment;

		$.ajax({
			url: "/BookStoreOnline/rest/api/shipment/select",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),

			dataType: "json",
			success: function(jsonResult) {
				$("#shipment-price").html(jsonResult[0] + " ₫");
				$("#payment-price").html(jsonResult[1] + " ₫");

			}
		});
	},

	editAddress: function() {
		var data = {};
		data["number"] = $('#number').val();
		data["street"] = $('#street').val();
		data["district"] = $('#district').val();
		data["city"] = $('#city').val();

		$.ajax({
			url: "/BookStoreOnline/rest/api/customer/edit-address",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),

			dataType: "json",
			success: function(jsonResult) {
				window.location.reload();
			}
		});
	},

	delete: function(link) {
		var flag = confirm("Bạn có chắc chắn muốn xóa?");
		if (flag == true) {
			location.href = link;
		}
	},

	setPaymentWith: function(value) {
		$('#paymentWith').val(value);
	},
}