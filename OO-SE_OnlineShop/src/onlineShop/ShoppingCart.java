package onlineShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparators.ProductNumberComparator;
import comparators.ProductPriceComparatorAscending;
import comparators.ProductPriceComparatorDescending;

public class ShoppingCart {

	private List<Products> myShoppingCart = new ArrayList<Products>();
	private Double totalPrice = 0.0;
	private Products returnProduct;

	public ShoppingCart() {

	}

	public void addProductToCart(Products product, int amount) {

		int amountBefore = product.getQuantity();

		if (product.getQuantity() < amount) {
			System.out.println("Fehler Bestand");
		} else {
			product.setQuantity(product.getQuantity() - amount);
			myShoppingCart.add(new Products(product.getProductNumber(), product.getProductName(),
					amountBefore - product.getQuantity(), product.getBasePrice()));
		}
	}

	public void printProducts() {
		String outputText = String.format("%-10s %-10s %-20s %-10s %-15s", "Position", "Nummer", "Name", "Anzahl",
				"Preis");
		System.out.println(outputText);
		System.out.println("-------------------------------------------------------------");
		for (Products myProducts : myShoppingCart) {
			System.out.println(myShoppingCart.indexOf(myProducts) + "           " + myProducts.toString());
			totalPrice = totalPrice + (myProducts.getQuantity() * myProducts.getBasePrice());
		}
		System.out.println("-------------");
		System.out.println("Summe: " + totalPrice);
		totalPrice = 0.0;
	}

	public double getTotalCost() {
		for (Products myProducts : myShoppingCart) {
			totalPrice = totalPrice + (myProducts.getQuantity() * myProducts.getBasePrice());
		}
		return totalPrice;
	}

	// Produktnummer
	public void sortAfterNumber() {
		ProductNumberComparator numComparator = new ProductNumberComparator();
		Collections.sort(myShoppingCart, numComparator);
	}

	// Absteigend
	public void sortAfterPriceDesc() {
		ProductPriceComparatorDescending descComparator = new ProductPriceComparatorDescending();
		Collections.sort(myShoppingCart, descComparator);
	}

	// Aufsteigend
	public void sortAfterPriceAsc() {
		ProductPriceComparatorAscending ascComparator = new ProductPriceComparatorAscending();
		Collections.sort(myShoppingCart, ascComparator);
	}

	public int getTotalItems() {
		return myShoppingCart.size();
	}

	// Get-Index
	public Products get(int pos) {
		returnProduct = myShoppingCart.get(pos);
		return returnProduct;
	}

}
