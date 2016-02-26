package com.chaipoint.dphelper;

import java.util.ArrayList;

import com.chaipoint.helperclasses.AddressInfo;
import com.chaipoint.helperclasses.ItemsDetail;
import com.chaipoint.helperclasses.PriceDetails;

public interface OrderDetailsDAO {

	AddressInfo getCustomerDeliveryAddress(String orderId);

	String getProductNameFromId(String productId);

	ArrayList<ItemsDetail> getItemdetails(String orderId);
	
	PriceDetails getPriceDetails(String orderId);
	
	ArrayList<String> getOrderListFromDpId(String dpId);
	
	
}
