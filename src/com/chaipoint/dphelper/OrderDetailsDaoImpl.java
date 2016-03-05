package com.chaipoint.dphelper;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.dppojos.CpOrderAddress;
import com.chaipoint.dppojos.CpOrderProduct;
import com.chaipoint.dppojos.CpOrders;
import com.chaipoint.dppojos.CpRetailCustomer;
import com.chaipoint.dppojos.ProductMaster;
import com.chaipoint.dppojos.StoreMaster;
import com.chaipoint.helperclasses.AddressInfo;
import com.chaipoint.helperclasses.ItemMaster;
import com.chaipoint.helperclasses.ItemsDetails;
import com.chaipoint.hibernatehelper.HibernateOperations;

public class OrderDetailsDaoImpl implements OrderDetailsDAO {
	HibernateOperations template = null;

	@Override
	public AddressInfo getCustomerDeliveryAddress(String orderId) {
		Criteria criteria = template.getSession().createCriteria(AddressInfo.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		ArrayList<AddressInfo> DPList = (ArrayList<AddressInfo>) getTemplate().get(criteria);
		return DPList.get(0);

	}

	@Override
	public String getProductNameFromId(String productId) {

		Criteria criteria = template.getSession().createCriteria(ItemMaster.class);
		criteria.add(Restrictions.eq("productId", productId));
		criteria.setProjection(Projections.property("name"));
		ArrayList<String> DPList = (ArrayList<String>) getTemplate().get(criteria);
		return DPList.get(0);
	}

	public ArrayList<String> getOrderIdsfromStoreId(String storeId) {
		Criteria criteria = template.getSession().createCriteria(ItemMaster.class);
		criteria.add(Restrictions.eq("storeId", storeId));
		criteria.setProjection(Projections.property("orderId"));
		ArrayList<String> DPList = (ArrayList<String>) getTemplate().get(criteria);
		return DPList;
	}

	@Override
	public ArrayList<CpOrders> getOrderDetailsFromOrderId(int storeId) {
		// ArrayList<CpOrders> orderList = new ArrayList<CpOrders>();
		Criteria criteria = template.getSession().createCriteria(CpOrders.class);
		criteria.add(Restrictions.eq("storeId", storeId));
		ArrayList<CpOrders> orderList = (ArrayList<CpOrders>) getTemplate().get(criteria);
		return orderList;

	}

	@Override
	public ArrayList<Integer> getAllOrderId(int storeId) {
		Criteria criteria = getTemplate().getSession().createCriteria(CpOrders.class);
		criteria.add(Restrictions.eq("storeId", storeId));
		criteria.setProjection(Projections.property("id"));
		ArrayList<Integer> orderList = (ArrayList<Integer>) getTemplate().get(criteria);
		return orderList;

	}

	@Override
	public String getstoreName(int storeId) {
		Criteria criteria = getTemplate().getSession().createCriteria(StoreMaster.class);
		criteria.add(Restrictions.eq("id", storeId));
		criteria.setProjection(Projections.property("name"));
		ArrayList<String> storeName = (ArrayList<String>) getTemplate().get(criteria);
		return storeName.get(0);
	}

	@Override
	public ArrayList<ItemsDetails> getOrderDeatils(int orderId) {
		int i = 0;
		ArrayList<ItemsDetails> Items = new ArrayList<ItemsDetails>();
		Criteria criteria = getTemplate().getSession().createCriteria(CpOrderProduct.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		ArrayList<CpOrderProduct> itemList = (ArrayList<CpOrderProduct>) getTemplate().get(criteria);
		for (CpOrderProduct details : itemList) {
			ItemsDetails item = new ItemsDetails();
			item.setSerialNo(i++);
			item.setItemName(getProductName(details.getProductId()));
			item.setItemUnitCount(details.getQty());
			item.setItemUnitPrice(details.getCost());
			item.setItemTotalPrice(details.getTotal_product_cost());
			Items.add(item);
		}

		return Items;
	}
	@Override
	public String getProductName(int id) {
		Criteria criteria = getTemplate().getSession().createCriteria(ProductMaster.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setProjection(Projections.property("name"));
		ArrayList<String> itemList = (ArrayList<String>) getTemplate().get(criteria);

		return itemList.get(0);

	}

	HibernateOperations getTemplate() {
		if (template == null) {
			template = new HibernateOperations();
		}
		return template;

	}

	public CpOrderAddress getAddressdetails(int orderList) {

		Criteria criteria = getTemplate().getSession().createCriteria(CpOrderAddress.class);
		criteria.add(Restrictions.eq("orderId", orderList));
		ArrayList<CpOrderAddress> address = (ArrayList<CpOrderAddress>) getTemplate().get(criteria);

		return address.get(0);
	}
	
	public AddressInfo getAddressdetailsFromId(String customerId) {

		Criteria criteria = template.getSession().createCriteria(CpOrderAddress.class);
		criteria.add(Restrictions.eq("id", customerId));
		ArrayList<AddressInfo> address = (ArrayList<AddressInfo>) getTemplate().get(criteria);

		return address.get(0);
	}

	public CpOrders getOrderdetails(int orderList) {
		orderList = 1;

		Criteria criteria = template.getSession().createCriteria(CpOrders.class);
		criteria.add(Restrictions.eq("id", orderList));
		ArrayList<CpOrders> address = (ArrayList<CpOrders>) getTemplate().get(criteria);

		return address.get(0);
	}

	public ArrayList<Integer> getAllOrderIds(ArrayList<String> storeIds) {
		Criteria criteria = template.getSession().createCriteria(CpOrders.class);
		for (String storeId : storeIds) {
			criteria.add(Restrictions.eq("storeId", storeId));
		}
		criteria.setProjection(Projections.property("orderId"));
		ArrayList<Integer> orderList = (ArrayList<Integer>) getTemplate().get(criteria);
		return orderList;
	}

	@Override
	public ArrayList<Integer> getAllOrderId(String storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getstoreName(String storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPhoneNumber(int id) {
		Criteria criteria = getTemplate().getSession().createCriteria(CpRetailCustomer.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setProjection(Projections.property("phone"));
		ArrayList<String> phone = (ArrayList<String>) getTemplate().get(criteria);

		return phone .get(0);

	}

}
