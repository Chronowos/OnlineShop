package onlineShop;

public class CreditCard extends Payment {
	
	private long cardNumber;
	private int expirationYear;
	private int cvv;
	
	public CreditCard(long cardNumber, int expirationYear, int cvv, double moneyAmount) {
		super(moneyAmount);

	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	

}
