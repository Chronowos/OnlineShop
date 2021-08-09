package testCases;

import static org.junit.Assert.*;
import org.junit.Test;
import onlineShop.CreditCard;

public class TestCreditCard {

	@Test
	public void Getter() {
		CreditCard myCard = new CreditCard(123123123, 2021, 555, 150.00);

		assertEquals("GetCardNumber", 123123123, myCard.getCardNumber());

		assertEquals("GetExpDate", 2021, myCard.getExpirationYear());

		assertEquals("GetCVV", 555, myCard.getCvv());

		assertEquals("GetBalance", 150.00, myCard.getCardBalance(), 1e-15);

	}

	@Test
	public void Setter() {
		CreditCard myCard = new CreditCard(123123123, 2021, 555, 150.00);
		myCard.setCardNumber(321321321);
		myCard.setExpirationYear(2022);
		myCard.setCvv(111);
		myCard.setCardBalance(6.69);

		assertEquals("GetCardNumber", 321321321, myCard.getCardNumber());

		assertEquals("GetExpDate", 2022, myCard.getExpirationYear());

		assertEquals("GetCVV", 111, myCard.getCvv());

		assertEquals("GetBalance", 6.69, myCard.getCardBalance(), 1e-15);
	}

	@Test
	public void StringTest() {
		CreditCard myCard = new CreditCard(123123123, 2021, 555, 150.00);
		assertEquals("Output string = toString()",
				"CreditCard [cardNumber=" + myCard.getCardNumber() + ", expirationYear=" + myCard.getExpirationYear()
						+ ", cvv=" + myCard.getCvv() + ", moneyAmount=" + myCard.getCardBalance() + "]",
				myCard.toString());
	}

}
