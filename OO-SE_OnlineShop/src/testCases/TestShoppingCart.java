package testCases;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import onlineShop.Products;
import onlineShop.ShoppingCart;

public class TestShoppingCart {

	@Test
	public void addItemToCart_get_Test() {

		Products[] prodArray = new Products[10];
		ShoppingCart myCart = new ShoppingCart();

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

		myCart.addProductToCart(prodArray[5], 5);
		myCart.addProductToCart(prodArray[9], 2);
		myCart.addProductToCart(prodArray[1], 17);

		assertEquals("AddProductToCart", prodArray[5].getProductName(), myCart.get(0).getProductName());
		assertEquals("AddProductToCart", prodArray[9].getProductNumber(), myCart.get(1).getProductNumber());
		assertEquals("AddProductToCart", prodArray[1].getBasePrice(), myCart.get(2).getBasePrice(), 1e-15);

	}

	@Test
	public void getTotalItemsTest() {

		Products[] prodArray = new Products[10];
		ShoppingCart myCart = new ShoppingCart();

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

		myCart.addProductToCart(prodArray[5], 5);
		myCart.addProductToCart(prodArray[9], 2);
		myCart.addProductToCart(prodArray[1], 17);

		assertEquals("Number matching", 3, myCart.getTotalItems());

	}

	@Test
	public void hasItemsTest() {

		Products[] prodArray = new Products[10];
		ShoppingCart myCart = new ShoppingCart();

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

		assertEquals("Has no items", false, myCart.hasItems());

		myCart.addProductToCart(prodArray[5], 5);
		myCart.addProductToCart(prodArray[9], 2);
		myCart.addProductToCart(prodArray[1], 17);

		assertEquals("Has items", true, myCart.hasItems());
	}
	
	@Test
	public void getTotalCostTest() {
		
		Products[] prodArray = new Products[10];
		ShoppingCart myCart = new ShoppingCart();

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

		myCart.addProductToCart(prodArray[5], 5);
		myCart.addProductToCart(prodArray[9], 2);
		myCart.addProductToCart(prodArray[1], 17);
		
		assertEquals("Price matches", 5425.00, myCart.getTotalCost(), 1e-15);
		
	}
	
	@Test
	public void printPDFTest() {
		
		Products[] prodArray = new Products[10];
		ShoppingCart myCart = new ShoppingCart();

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

		myCart.addProductToCart(prodArray[5], 5);
		myCart.addProductToCart(prodArray[9], 2);
		myCart.addProductToCart(prodArray[1], 17);
		
		myCart.createPDFBank("Teststraße 10", "Sparkasse", 10050000, 123123123);
		
		File checkFile = new File(System.getProperty("user.home") + "\\Desktop\\Quittung.pdf");
		assertEquals("File was created", true, checkFile.exists());
	}
}
