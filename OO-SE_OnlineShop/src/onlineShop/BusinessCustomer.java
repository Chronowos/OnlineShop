package onlineShop;

public class BusinessCustomer extends Customer {

	private int BusinessSize;

	public BusinessCustomer(String customerName, String customerSurname, String customerAddress, int BusinessSize) {
		super(customerName, customerSurname, customerAddress);
		if(BusinessSize != 1 || BusinessSize != 2 || BusinessSize != 3) {
			System.out.println("Fehler BusinessCustomer");
		}else {
			this.BusinessSize = BusinessSize;
		}
	}

}
