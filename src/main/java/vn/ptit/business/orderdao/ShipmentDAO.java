package vn.ptit.business.orderdao;

import java.util.List;

import vn.ptit.model.order.Shipment;
import vn.ptit.model.order.ShipmentStat;

public interface ShipmentDAO {
	public int insert(Shipment shipment);
	public List<Shipment> findAll();
	public int delete(int id);
	public int update(Shipment shipment);
	public Shipment findById(int id);

}
