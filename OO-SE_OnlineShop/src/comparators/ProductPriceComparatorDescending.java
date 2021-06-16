package comparators;

import java.util.Comparator;

import onlineShop.Products;

public class ProductPriceComparatorDescending implements Comparator<Products> {

	public int compare(Products arg0, Products arg1) {
		return Double.compare(arg1.getBasePrice(), arg0.getBasePrice());
	}

}
