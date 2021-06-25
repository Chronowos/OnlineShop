package onlineShop;

public class BankAccount extends Payment {
	
	private String bank;
	private long bankCode;
	private long accountNumber;
	
	public BankAccount(String bank, long BankCode, long accountNumber, double moneyAmount) {
		super(moneyAmount);
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
	
	
	
	

}
