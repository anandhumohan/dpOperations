package com.chaipoint.dphelper;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.helperclasses.AddressInfo;
import com.chaipoint.helperclasses.ItemMaster;
import com.chaipoint.helperclasses.ItemsDetail;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.PriceDetails;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class OrderDetailsDaoImpl implements OrderDetailsDAO {
	HibernateTemplate template = null;

	@Override
	public ArrayList<ItemsDetail> getItemdetails(String orderId) {
		Criteria criteria = template.getSession().createCriteria(AddressInfo.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		ArrayList<ItemsDetail> ItemList = (ArrayList<ItemsDetail>) getTemplate().get(criteria);

		for (ItemsDetail details : ItemList) {
			details.setItem_name(getProductNameFromId(details.getItem_id()));
		}
		return ItemList;

	}

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

	public HibernateTemplate getTemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}

	public ArrayList<String> getOrderIdsfromStoreId(String storeId) {
		Criteria criteria = template.getSession().createCriteria(ItemMaster.class);
		criteria.add(Restrictions.eq("storeId", storeId));
		criteria.setProjection(Projections.property("orderId"));
		ArrayList<String> DPList = (ArrayList<String>) getTemplate().get(criteria);
		return DPList;
	}

	@Override
	public PriceDetails getPriceDetails(String orderId) {

		Criteria criteria = template.getSession().createCriteria(OrderDetails.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		criteria.setProjection(Projections.property("basePrice"));
		criteria.setProjection(Projections.property("finalPrice"));
		ArrayList<PriceDetails> DPList = (ArrayList<PriceDetails>) getTemplate().get(criteria);
		return DPList.get(0);

	}

}
