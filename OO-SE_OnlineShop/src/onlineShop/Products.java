package onlineShop;

public class Products {

	private long productNumber;
	private String productName;
	private int quantity;
	private double basePrice;
	private double productDiscount;

	public Products(long productNumber, String productName, int quantity, double basePrice, double productDiscount) {
		this.productNumber = productNumber;
		this.productName = productName;
		this.basePrice = basePrice;
		this.productDiscount = productDiscount;
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

	public double getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		String outputText = String.format("%-10s %-20s %-10s %-15s %-15s \n", productNumber, productName, quantity,
				basePrice, productDiscount);
		return outputText;
		// return productNumber + "\t\t | " + productName + "\t\t | " + quantity + "\t\t
		// | " + basePrice + "€\t\t | "
		// + productDiscount + "% Rabatt";
	}

	public String toStringListing() {
		String outputText = String.format("%-10s %-20s %-10s %-15s %-15s \n", productNumber, productName, quantity,
				basePrice, productDiscount);
		return outputText;
		// return productNumber + "\t\t | " + productName + "\t\t | " + quantity + "\t\t
		// | " + basePrice + "€\t\t | "
		// + productDiscount + "% Rabatt";
	}

}
