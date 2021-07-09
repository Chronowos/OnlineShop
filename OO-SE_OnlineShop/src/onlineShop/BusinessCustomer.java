package onlineShop;

import java.io.Serializable;

public class BusinessCustomer extends Customer implements Serializable {

	private int BusinessSize;

	public BusinessCustomer(String customerName, String password, int BusinessSize) {
		super(customerName, password);
		if (BusinessSize != 1 || BusinessSize != 2 || BusinessSize != 3) {
			System.out.println("Fehler BusinessCustomer");
			System.exit(0);
		} else {
			this.BusinessSize = BusinessSize;
		}

	}

}
