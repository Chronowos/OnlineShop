package onlineShop;

public class BankAccount {

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

	public double getBankBalance() {
		return moneyAmount;
	}

	public void setBankBalance(double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public void payMoneyBank(double amount) {
		this.setBankBalance(getBankBalance() - amount);
	}

	@Override
	public String toString() {
		return "BankAccount [bank=" + bank + ", bankCode=" + bankCode + ", accountNumber=" + accountNumber
				+ ", moneyAmount=" + moneyAmount;
	}
	
	
	

}
