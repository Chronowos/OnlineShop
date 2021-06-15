package onlineShop;

public class BusinessCustomer extends Customer {

	private int BusinessSize;

	public BusinessCustomer(String customerName, String customerSurname, String customerAddress, int BusinessSize) {
		super(customerName, customerSurname, customerAddress);
		this.BusinessSize = BusinessSize;
	}

}
