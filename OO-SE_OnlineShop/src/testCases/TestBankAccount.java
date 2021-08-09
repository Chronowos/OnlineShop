package testCases;

import static org.junit.Assert.*;
import org.junit.Test;
import onlineShop.BankAccount;

public class TestBankAccount {

	// Message, Expected, Actual
	@Test
	public void Getter() {
		BankAccount myBank = new BankAccount("Sparkasse", 10050000, 123123123, 150.00);

		assertEquals("GetName", "Sparkasse", myBank.getBank());

		assertEquals("GetBankcode", 10050000, myBank.getBankCode());

		assertEquals("GetAccountnumber", 123123123, myBank.getAccountNumber());

		assertEquals("GetBalance", 150.00, myBank.getBankBalance(), 1e-15);

	}

	@Test
	public void Setter() {
		BankAccount myBank = new BankAccount("Sparkasse", 10050000, 123123123, 150.00);
		myBank.setBankCode(10050001);
		myBank.setBank("Sächische Sparkasse");
		myBank.setBankBalance(6.69);
		myBank.setAccountNumber(321321321);

		assertEquals("SetName", "Sächische Sparkasse", myBank.getBank());

		assertEquals("SetBankcode", 10050001, myBank.getBankCode());

		assertEquals("SetAccountnumber", 321321321, myBank.getAccountNumber());

		assertEquals("SetBalance", 6.69, myBank.getBankBalance(), 1e-15);
	}

	@Test
	public void StringTest() {
		BankAccount myBank = new BankAccount("Sparkasse", 10050000, 123123123, 150.00);
		assertEquals("Output string = toString()",
				"BankAccount [bank=" + myBank.getBank() + ", bankCode=" + myBank.getBankCode() + ", accountNumber="
						+ myBank.getAccountNumber() + ", moneyAmount=" + myBank.getBankBalance() + "]",
				myBank.toString());
	}

}
