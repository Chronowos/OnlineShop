package comparators;

import java.util.Comparator;

import onlineShop.Products;

public class ProductPriceComparatorAscending implements Comparator<Products> {

	public int compare(Products arg0, Products arg1) {
		return Double.compare(arg0.getBasePrice(), arg1.getBasePrice());

	}

}
