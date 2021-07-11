package onlineShop;

import java.io.Serializable;

public class BusinessCustomer extends Customer implements Serializable {

	private int BusinessSize;

	public BusinessCustomer(String customerName, String password, int BusinessSize) {
		super(customerName, password);
		if (BusinessSize > 3 || BusinessSize < 1) {
			System.out.println("Zu gro� oder zu klein");
		}else {
			this.BusinessSize = BusinessSize;
		}

	}

	@Override
	public String toString() {
		
		return "BusinessCustomer [BusinessSize=" + BusinessSize + ", CustomerName=" + getCustomerName() + ", Password="		
				+ getPassword() + "]";

	}
	


}
