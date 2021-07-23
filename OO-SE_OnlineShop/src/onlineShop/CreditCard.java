package onlineShop;

public class CreditCard implements Payment {

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

	public double getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	@Override
	public void payMoney(double amount) {
		// TODO Auto-generated method stub

	}

}
