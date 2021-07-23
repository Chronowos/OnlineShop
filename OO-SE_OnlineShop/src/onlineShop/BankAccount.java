package onlineShop;

public class BankAccount implements Payment {

	private String bank;
	private long bankCode;
	private long accountNumber;
	private double moneyAmount;

	public BankAccount(String bank, long BankCode, long accountNumber, double moneyAmount) {
		this.bank = bank;
		this.bankCode = BankCode;
		this.accountNumber = accountNumber;
		this.moneyAmount = moneyAmount;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public long getBankCode() {
		return bankCode;
	}

	public void setBankCode(long bankCode) {
		this.bankCode = bankCode;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
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
