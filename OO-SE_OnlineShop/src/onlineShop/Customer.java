package onlineShop;

import java.io.Serializable;

public class Customer implements Serializable {

	private String customerName;
	private String password;

	public Customer(String customerName, String password) {
		this.customerName = customerName;
		this.password = password;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", password=" + password + "]";
	}

}
