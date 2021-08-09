package testCases;

import static org.junit.Assert.*;
import org.junit.Test;
import onlineShop.BusinessCustomer;

public class TestBusinessCustomer {

	@Test
	public void Getter() {
		BusinessCustomer myBusiness = new BusinessCustomer("Bernhard", "safetyfirst1", 3);

		assertEquals("GetName", "Bernhard", myBusiness.getCustomerName());

		assertEquals("GetPassword", "safetyfirst1", myBusiness.getPassword());

		assertEquals("GetBusinessSize", 3, myBusiness.getBusinessSize());

	}

	@Test
	public void Setter() {
		BusinessCustomer myBusiness = new BusinessCustomer("Bernhard", "safetyfirst1", 3);

		myBusiness.setBusinessSize(1);
		myBusiness.setCustomerName("Werner");
		myBusiness.setPassword("testtest1");

		assertEquals("SetName", "Werner", myBusiness.getCustomerName());

		assertEquals("SetPassword", "testtest1", myBusiness.getPassword());

		assertEquals("SetBusinessSize", 1, myBusiness.getBusinessSize());

	}

	@Test
	public void StringTest() {
		BusinessCustomer myBusiness = new BusinessCustomer("Bernhard", "safetyfirst1", 3);
		assertEquals("Output string = toString()",
				"BusinessCustomer [BusinessSize=" + myBusiness.getBusinessSize() + ", CustomerName="
						+ myBusiness.getCustomerName() + ", Password=" + myBusiness.getPassword() + "]",
				myBusiness.toString());
	}

	@Test
	public void BusinessSizeError() {
		assertThrows("BusinessSize zu groß / klein", IllegalArgumentException.class, () -> {
			BusinessCustomer myBusiness = new BusinessCustomer("Bernhard", "safetyfirst1", 5);
		});
	}

}
