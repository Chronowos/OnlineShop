package onlineShop;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Customer targetCustomer;
	private BusinessCustomer targetBusinessCustomer;
	private ShoppingCart shoppingCart;

	public Order(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public void completeOrder() {
		System.out.println("Bestellung eingegangen.\n Anzahl an Produkten im Warenkorb: "
				+ this.shoppingCart.getTotalItems() + "\n Gesamtkosten: " + this.shoppingCart.getTotalCost());

	}

}
