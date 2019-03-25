package kush.hadoop.mapreduce.FraudCustomers;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class CustomerWritable implements Writable {
	
	/*
	 * [0] GGYZ333519YS Customer Id 
	 * [1] Allison Customer Name
	 * [2] 08-01-2017 Date on Which Customer Place the Order
	 * [3] 10-01-2017 Shiping Date
	 * [4] Delhivery Shiping Coriour name
	 * [5] 13-01-2017 Date on which product is received by customer
	 * [6] yes Weather customer returned the product or not Y/N
	 * [7] 15-01-2017 Return Date on which the product is returned back
	 * [8] Damaged Item Reason for Return
	 * 
	 */
	public int customer_Id;
	public String customer_Name;
	public String orderPlacedDate;
	public String shippingDate;
	public String cCoriourName;
	public String productReceivedDate;
	public boolean productReturned;
	public String productReturnedDate;
	public String reasonReject;
	
	
	public CustomerWritable() {
		setCustomer(0, " ",  " ", " ", " ", " ", "no"  ," " ," ") ;
		 
	}

	public CustomerWritable(int customer_Id, String customer_Name, String orderPlacedDate, String shippingDate,
			String cCoriourName, String productReceivedDate, Boolean productReturned, String productReturnedDate,
			String reasonReject) {
		
		this.customer_Id = customer_Id;
		this.customer_Name = customer_Name;
		this.orderPlacedDate = orderPlacedDate;
		this.shippingDate = shippingDate;
		this.cCoriourName = cCoriourName;
		this.productReceivedDate = productReceivedDate;
		this.productReturned = productReturned;
		this.productReturnedDate = productReturnedDate;
		this.reasonReject = reasonReject;
	}

	
	
	
	public int getCustomer_Id() {
		return customer_Id;
	}
	public void setCustomer_Id(int customer_Id) {
		this.customer_Id = customer_Id;
	}
	public String getCustomer_Name() {
		return customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	public String getOrderPlacedDate() {
		return orderPlacedDate;
	}
	public void setOrderPlacedDate(String orderPlacedDate) {
		this.orderPlacedDate = orderPlacedDate;
	}
	public String getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}
	public String getcCoriourName() {
		return cCoriourName;
	}
	public void setcCoriourName(String cCoriourName) {
		this.cCoriourName = cCoriourName;
	}
	public String getProductReceivedDate() {
		return productReceivedDate;
	}
	public void setProductReceivedDate(String productReceivedDate) {
		this.productReceivedDate = productReceivedDate;
	}
	public Boolean getProductReturned() {
		return productReturned;
	}
	public void setProductReturned(Boolean productReturned) {
		this.productReturned = productReturned;
	}
	public String getProductReturnedDate() {
		return productReturnedDate;
	}
	public void setProductReturnedDate(String productReturnedDate) {
		this.productReturnedDate = productReturnedDate;
	}
	public String getReasonReject() {
		return reasonReject;
	}
	public void setReasonReject(String reasonReject) {
		this.reasonReject = reasonReject;
	}
	
	@Override
	public String toString() {
		return "FraudCustomersReducer [customer_Id=" + customer_Id + ", customer_Name=" + customer_Name
				+ ", orderPlacedDate=" + orderPlacedDate + ", shippingDate=" + shippingDate + ", cCoriourName="
				+ cCoriourName + ", productReceivedDate=" + productReceivedDate + ", productReturned=" + productReturned
				+ ", productReturnedDate=" + productReturnedDate + ", reasonReject=" + reasonReject + "]";
	}
	
	

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.customer_Id = in.readInt();
		this.customer_Name = WritableUtils.readString(in);
		this.orderPlacedDate = WritableUtils.readString(in);
		this.shippingDate = WritableUtils.readString(in);
		this.cCoriourName = WritableUtils.readString(in);
		this.productReceivedDate = WritableUtils.readString(in);
		this.productReturned = in.readBoolean();
		this.productReturnedDate = WritableUtils.readString(in);
		this.reasonReject= WritableUtils.readString(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {

		// TODO Auto-generated method stub
		out.writeInt(this.customer_Id);
		WritableUtils.writeString(out, this.customer_Name);
		WritableUtils.writeString(out, this.orderPlacedDate);
		WritableUtils.writeString(out, this.shippingDate);
		WritableUtils.writeString(out, this.cCoriourName);
		WritableUtils.writeString(out, this.productReceivedDate);
		out.writeBoolean(this.productReturned);
		WritableUtils.writeString(out, this.productReturnedDate);
		WritableUtils.writeString(out, this.reasonReject);
	}
	
	 public void setCustomer(int customer_Id, String customer_Name, String orderPlacedDate, String shippingDate, String cCoriourName, String productReceivedDate, String productReturned  ,String productReturnedDate ,String reasonReject ) 
	    {
		 	this.customer_Id = customer_Id;
			this.customer_Name = customer_Name;
			this.orderPlacedDate = orderPlacedDate;
			this.shippingDate = shippingDate;
			this.cCoriourName = cCoriourName;
			this.productReceivedDate = productReceivedDate;
			this.reasonReject= reasonReject;
		
		if (productReturned.equalsIgnoreCase("yes")) {
			 this.productReturned = true;
		}
		else {
		    this.productReturned = false;
		}
		
		this.productReturnedDate = productReturned;
	    }
	    
	
	

}
