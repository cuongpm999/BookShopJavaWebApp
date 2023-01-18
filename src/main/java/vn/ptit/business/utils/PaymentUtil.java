package vn.ptit.business.utils;

import vn.ptit.model.order.Cart;
import vn.ptit.model.order.Shipment;

public class PaymentUtil {
	public static double calTotalMoney(Cart cart, Shipment shipment) {
		return cart.getTotalAmount() + shipment.getPrice();
	}
}
