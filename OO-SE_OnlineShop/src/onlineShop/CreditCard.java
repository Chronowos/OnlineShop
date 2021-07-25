package onlineShop;

public class CreditCard {

	private long cardNumber;
	private int expirationYear;
	private int cvv;
	private double moneyAmount;

	public CreditCard(long cardNumber, int expirationYear, int cvv, double moneyAmount) {
		this.cardNumber = cardNumber;
		this.expirationYear = expirationYear;
		this.cvv = cvv;
		this.moneyAmount = moneyAmount;

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

	public double getCardBalance() {
		return moneyAmount;
	}

	public void setCardBalance(double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public void payMoneyCard(double amount) {
		this.setCardBalance(getCardBalance() - amount);
	}

}
