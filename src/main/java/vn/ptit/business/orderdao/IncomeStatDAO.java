package vn.ptit.business.orderdao;

import java.util.Date;
import java.util.List;

import vn.ptit.model.order.IncomeStat;
import vn.ptit.model.order.ShipmentStat;

public interface IncomeStatDAO {
	public IncomeStat statisticIncome(Date from, Date to);
}
