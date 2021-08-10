package testCases;

import static org.junit.Assert.*;
import org.junit.Test;
import onlineShop.Products;

public class TestProducts {

	// Message, Expected, Actual
	@Test
	public void Getter() {
		Products myProduct = new Products(001, "Handschuhe", 15, 15.00);

		assertEquals("GetProductNumber", 001, myProduct.getProductNumber());

		assertEquals("GetProductName", "Handschuhe", myProduct.getProductName());

		assertEquals("GetQuantity", 15, myProduct.getQuantity());

		assertEquals("GetPrice", 15.00, myProduct.getBasePrice(), 1e-15);

	}

	@Test
	public void Setter() {
		Products myProduct = new Products(001, "Handschuhe", 15, 15.00);
		myProduct.setProductNumber(111);
		myProduct.setProductName("Handschuhe neue Ausführung");
		myProduct.setQuantity(5);
		myProduct.setBasePrice(20.99);

		assertEquals("SetProductNumber", 111, myProduct.getProductNumber());

		assertEquals("SetProductName", "Handschuhe neue Ausführung", myProduct.getProductName());

		assertEquals("SetQuantity", 5, myProduct.getQuantity());

		assertEquals("SetBasePrice", 20.99, myProduct.getBasePrice(), 1e-15);
	}

	@Test
	public void StringTest() {
		Products myProduct = new Products(001, "Handschuhe", 15, 15.00);
		String outputText = String.format("%-10s %-20s %-10s %-15s \n", myProduct.getProductNumber(),
				myProduct.getProductName(), myProduct.getQuantity(), myProduct.getBasePrice());

		assertEquals("Output String = toString()", outputText, myProduct.toString());
	}

	@Test
	public void StringListingTest() {
		Products myProduct = new Products(001, "Handschuhe", 15, 15.00);
		String outputText = String.format("%-10s %-20s %-10s %-15s", myProduct.getProductNumber(),
				myProduct.getProductName(), myProduct.getQuantity(), myProduct.getBasePrice());

		assertEquals("Output Listing String = toString()", outputText, myProduct.toStringListing());
	}

}
