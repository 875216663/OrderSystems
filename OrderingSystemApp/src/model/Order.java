package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import application.Main;

public class Order {
	/*********************************************Variables****************************************************/
	// order ID controlled by global static int Main.orderID
	// But it will be shown in the 6 digit formatted form: formattedorderID
	private int orderID;
	private String formattedOrderID;
	// customerID will be shown in the 6 digit formatted form: formattedorderID
	private int customerID;
	private String formattedCustomerID;
	private String customerName;
	private String createTime;
	private String finishTime;
	private ArrayList<Food> foodItems;
	private String foodNameString; // for testing displaying food items in TableView.
	private String status; // the status of this order:"received", "preparing", "completed"
	private double price;
	/*********************************************Constructor****************************************************/
	// constructors
	
	public Order(int customerID, String customerName, ArrayList<Food> foodItems) {
		this.orderID=Main.orderID;// The global static orderID, order_ID+=1 when a new order is created.
		this.customerID = customerID;
		this.customerName = customerName;
		setCreateTime();
		this.foodItems = foodItems;
		this.status = "received";
		// set formatted output for ID upon initiation
		setFormattedOrderID();
		setFormattedCustomerID();
		setFoodNameString();
		// add a order upon creating new one
		Main.orderID+=1;		
	}
	
	/*********************************************Methods****************************************************/
	// the orderID will be initiated upon constructor call.
	// therefore, there is no setter for orderID
	
	// getter of orderID, formatted to a String, for UI. 
	// though orderID is an int, 
	// getFormattedOrderID() will give a formatted form 
	public String getFormattedOrderID() {
		this.setFormattedOrderID();
		return this.formattedOrderID;
	}
	// setter of formatted order id
	public void setFormattedOrderID() {
		this.formattedOrderID=String.format("%06d", this.orderID);
	}
	// getter of orderID
	public int getOrderID() {
		return this.orderID;
	}
	
	// getter and setter of int customer_ID;
	public int getCustomerID() {
		return customerID;
	}
	public String getFormattedCustomerID() {
		return this.formattedCustomerID;
	}
	public void setFormattedCustomerID() {
		this.formattedCustomerID=String.format("%06d",this.customerID);
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	// getter and setter of String customer_Name;
	public String getCustomerName() {
		return this.customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	// getter and setter of String createTtime;
	public String getCreateTime() {
		return this.createTime;
	}
	private void setCreateTime() {
		// get the current time
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String timeString = formatter.format(date);
		this.createTime = timeString;
	}
	// getter and setter of String finishTime;
	public String getFinishTime() {
		return this.finishTime;
	}
	private void setFinishTime() {
		// get the current time
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String timeString = formatter.format(date);
		this.finishTime = timeString;
	}
	// getter, remover, and adder of the foodItems
	public ArrayList<Food> getFoodItems() {
		return this.foodItems;
	}
	public void removeFood(Food food) {
		if (this.foodItems.contains(food)) {
			this.foodItems.remove(food);
		} else {
			//pass;
		}
	}
	public void addFood(Food food) {
		this.foodItems.add(food);
	}
	// getter of  foodNameString
	private void setFoodNameString() {
		String result = "";
		for (Food food:this.foodItems) {
			result += food.getName();
			result += " \n";
		}
		this.foodNameString=result;
	}
	public String getFoodNameString() {
		setFoodNameString();
		return this.foodNameString;
	}
	// getter and setter of String status;
	public String getStatus() {
		return this.status;
	}
	// use -1, 0, 1 to represent "received", "preparing","completed" 
	private void setStatus(int x) {
		// get the current time
		switch (x) {
		case -1:
			this.status = "received";
			break;
		case 0:
			this.status = "preparing";
			break;
		case 1:
			this.status = "completed";
			break;
		default:
			this.status = "error";
		}

	}
	// getter and setter of price
	public double getPrice() {
		return this.price;
	}
	private double setPrice() {
		double result=0;
		for (Food food: this.foodItems) {
			result += food.getPrice();
		}
		return result;
	}
}
