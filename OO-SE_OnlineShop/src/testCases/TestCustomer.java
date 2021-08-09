package testCases;

import static org.junit.Assert.*;
import org.junit.Test;
import onlineShop.Customer;

public class TestCustomer {

	@Test
	public void Getter() {
		Customer myCustomer = new Customer("Bernhard", "safetyfirst1");

		assertEquals("GetName", "Bernhard", myCustomer.getCustomerName());

		assertEquals("GetPassword", "safetyfirst1", myCustomer.getPassword());

	}

	@Test
	public void Setter() {
		Customer myCustomer = new Customer("Bernhard", "safetyfirst1");

		myCustomer.setCustomerName("Werner");
		myCustomer.setPassword("testtest1");

		assertEquals("SetName", "Werner", myCustomer.getCustomerName());

		assertEquals("SetPassword", "testtest1", myCustomer.getPassword());

	}

	@Test
	public void StringTest() {
		Customer myCustomer = new Customer("Bernhard", "safetyfirst1");
		assertEquals("Output string = toString()", "Customer [customerName=" + myCustomer.getCustomerName()
				+ ", password=" + myCustomer.getPassword() + "]", myCustomer.toString());
	}
}
