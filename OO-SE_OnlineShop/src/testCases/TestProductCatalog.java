package testCases;

import static org.junit.Assert.*;
import org.junit.Test;
import onlineShop.ProductCatalog;
import onlineShop.Products;

public class TestProductCatalog {

	@Test
	public void addProductToListingTest() {
		ProductCatalog myCatalogue = new ProductCatalog();
		Products[] prodArray = new Products[10];

		prodArray[0] = new Products(104, "Kettensäge", 7, 550.00);
		prodArray[1] = new Products(710, "Panzertape", 25, 5.00);
		prodArray[2] = new Products(471, "SkiMaske", 37, 25.00);
		prodArray[3] = new Products(293, "Baseballschläger", 8, 75.00);
		prodArray[4] = new Products(510, "Tisch", 5, 250.00);
		prodArray[5] = new Products(410, "Laptop", 11, 1050.00);
		prodArray[6] = new Products(790, "Tastatur", 5, 110.00);
		prodArray[7] = new Products(669, "Kopfhörer", 5, 250.00);
		prodArray[8] = new Products(912, "Bildschirm", 3, 478.00);
		prodArray[9] = new Products(244, "Maus", 7, 45.00);

		myCatalogue.addProductToListing(prodArray[1]);
		myCatalogue.addProductToListing(prodArray[2]);
		myCatalogue.addProductToListing(prodArray[3]);

		assertEquals("Number in listing matches", 3, myCatalogue.getSize());
	}

	@Test
	public void getTest() {

		ProductCatalog myCatalogue = new ProductCatalog();
		Products[] prodArray = new Products[10];

		prodArray[0] = new Products(104, "Kettensäge", 7, 550.00);
		prodArray[1] = new Products(710, "Panzertape", 25, 5.00);
		prodArray[2] = new Products(471, "SkiMaske", 37, 25.00);
		prodArray[3] = new Products(293, "Baseballschläger", 8, 75.00);
		prodArray[4] = new Products(510, "Tisch", 5, 250.00);
		prodArray[5] = new Products(410, "Laptop", 11, 1050.00);
		prodArray[6] = new Products(790, "Tastatur", 5, 110.00);
		prodArray[7] = new Products(669, "Kopfhörer", 5, 250.00);
		prodArray[8] = new Products(912, "Bildschirm", 3, 478.00);
		prodArray[9] = new Products(244, "Maus", 7, 45.00);

		myCatalogue.addProductToListing(prodArray[1]);
		myCatalogue.addProductToListing(prodArray[2]);
		myCatalogue.addProductToListing(prodArray[3]);

		assertEquals("Product matches", prodArray[1].getProductName(), myCatalogue.get(0).getProductName());
		assertEquals("Product matches", prodArray[2].getBasePrice(), myCatalogue.get(1).getBasePrice(), 1e-15);
		assertEquals("Product matches", prodArray[3].getProductNumber(), myCatalogue.get(2).getProductNumber());

	}

}
