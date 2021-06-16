package comparators;

import java.util.Comparator;

import onlineShop.Products;

public class ProductNumberComparator implements Comparator<Products> {

	@Override
	public int compare(Products p1, Products p2) {
		return Long.compare(p1.getProductNumber(), p2.getProductNumber());
	}

}
