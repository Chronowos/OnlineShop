package onlineShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparators.ProductNumberComparator;
import comparators.ProductPriceComparatorAscending;
import comparators.ProductPriceComparatorDescending;

public class ProductCatalog {

	private List<Products> myItemListing = new ArrayList<Products>();
	private Products returnProduct;

	public ProductCatalog() {

	}

	public void addProductToListing(Products myProduct) {
		myItemListing.add(myProduct);
	}

	public Products get(int pos) {
		returnProduct = myItemListing.get(pos);
		return returnProduct;
	}

	// Wie viele Produkte bieten wir an? Bzw. sind im Katalog
	public int getSize() {
		int size;
		size = myItemListing.size();
		return size;
	}

	public void printListing() {
		// Formatierung für Ausgabe
		String outputText = String.format("%-10s %-20s %-10s %-15s", "Nummer", "Name", "Anzahl", "Preis");
		System.out.println(outputText);
		System.out.println("-------------------------------------------------------------");
		for (Products myProducts : myItemListing) {
			System.out.println(myProducts.toStringListing());
		}
	}

	// Sortierung nach Produktnummer
	public void sortAfterNumber() {
		ProductNumberComparator numComparator = new ProductNumberComparator();
		Collections.sort(myItemListing, numComparator);
	}

	// Sortierung nach Preis: absteigend
	public void sortAfterPriceDesc() {
		ProductPriceComparatorDescending descComparator = new ProductPriceComparatorDescending();
		Collections.sort(myItemListing, descComparator);
	}

	// Sortierung nach Preis: aufsteigend
	public void sortAfterPriceAsc() {
		ProductPriceComparatorAscending ascComparator = new ProductPriceComparatorAscending();
		Collections.sort(myItemListing, ascComparator);
	}

}
