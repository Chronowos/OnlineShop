package onlineShop;

import java.io.Serializable;

public class BusinessCustomer extends Customer implements Serializable {

	private int BusinessSize;

	public BusinessCustomer(String customerName, String password, int BusinessSize) {
		super(customerName, password);
		// Bei einer BusinessSize gr��er als 3, bzw. kleiner als 1 soll ein Fehler
		// eintreten
		if (BusinessSize > 3 || BusinessSize < 1) {
			throw new IllegalArgumentException(
					"BusinessSize / Unternehmensgr��e muss zwischen 1-3 liegen. Gefunden wurde aber: " + BusinessSize);
		} else {
			this.BusinessSize = BusinessSize;
		}

	}

	public int getBusinessSize() {
		return BusinessSize;
	}

	public void setBusinessSize(int businessSize) {
		BusinessSize = businessSize;
	}

	@Override
	public String toString() {
		return "BusinessCustomer [BusinessSize=" + BusinessSize + ", CustomerName=" + getCustomerName() + ", Password="
				+ getPassword() + "]";
	}

}
