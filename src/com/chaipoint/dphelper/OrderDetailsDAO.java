package com.chaipoint.dphelper;

import java.util.ArrayList;

import com.chaipoint.dppojos.CpOrders;
import com.chaipoint.helperclasses.AddressInfo;
import com.chaipoint.helperclasses.ItemsDetails;

public interface OrderDetailsDAO {

	AddressInfo getCustomerDeliveryAddress(String orderId);

	String getProductNameFromId(String productId);
	
	//finished
	ArrayList<CpOrders> getOrderDetailsFromOrderId(int storeId);
	
	//finished
	ArrayList<Integer> getAllOrderId(String storeId);
	//finished 
	String getstoreName(String storeId);
	//finished
	ArrayList<ItemsDetails> getOrderDeatils(int orderId);
	//finished
	CpOrders getOrderdetails(int orderId);

	ArrayList<Integer> getAllOrderId(int storeId);

	String getstoreName(int storeId);
	String getProductName(int id);
	
}
