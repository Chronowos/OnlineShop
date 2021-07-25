package onlineShop;

public interface Payment {

	public void payMoneyBank(double amount, BankAccount account);
	
	public void showMoneyAmount(BankAccount account);
	
	
	
	
	public void payMoneyCard(double amount, CreditCard card);

}
