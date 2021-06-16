package onlineShop;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {

	List<Products> myItemListing = new ArrayList<Products>();

	public ProductCatalog() {

	}

	public void addProductToListing(Products myProduct) {
		myItemListing.add(myProduct);
	}

	public void printListing() {
		for (Products myProduct : myItemListing) {
			System.out.println(myProduct.toStringListing());
		}
	}

}
