package onlineShop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	List<Products> myShoppingCart = new ArrayList<Products>();
	private Double totalPrice = 0.0;

	public ShoppingCart() {

	}

	public void addProductToCart(Products product) {
		myShoppingCart.add(product);
	}

	public void printProducts() {
		String outputText = String.format("%-10s %-20s %-10s %-15s %-15s", "Nummer", "Name", "Anzahl", "Preis",
				"Rabatt");
		System.out.println(outputText);
		System.out.println("------------------------------------------------------------------------");
		for (Products myProducts : myShoppingCart) {
			System.out.println(myProducts.toString());
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

}
