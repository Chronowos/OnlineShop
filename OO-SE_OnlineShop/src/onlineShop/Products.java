package onlineShop;

import java.io.Serializable;

public class Products implements Serializable {

	private long productNumber;
	private String productName;
	private int quantity;
	private double basePrice;

	public Products(long productNumber, String productName, int quantity, double basePrice) {
		this.productNumber = productNumber;
		this.productName = productName;
		this.basePrice = basePrice;
		this.quantity = quantity;
	}

	public long getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(long productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		// Formatierung für die Ausgabe
		String outputText = String.format("%-10s %-20s %-10s %-15s \n", productNumber, productName, quantity,
				basePrice);
		return outputText;
	}

	public String toStringListing() {
		// Formatierung für die Ausgabe
		String outputText = String.format("%-10s %-20s %-10s %-15s", productNumber, productName, quantity, basePrice);
		return outputText;
	}

}
