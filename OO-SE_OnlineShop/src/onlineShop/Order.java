package onlineShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {

	private Customer targetCustomer;
	private BusinessCustomer targetBusinessCustomer;
	private ShoppingCart shoppingCart;

	public Order(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public void completeOrder(Customer targetCustomer) {

		String adresse;
		String aktion;
		String bank;
		long bankCode;
		long accountNumber;
		long cardNumber;
		int expDate;
		int cvv;
		Scanner sc = new Scanner(System.in);

		System.out.println("Bestellung eingegangen.\n Anzahl an Produkten im Warenkorb: "
				+ this.shoppingCart.getTotalItems() + "\n Gesamtkosten: " + this.shoppingCart.getTotalCost());
		System.out.println("------------------------------------------------");
		System.out.println("Gebe zu erst deine Lieferadresse an: \n");
		adresse = sc.nextLine();

		System.out.println("Wie möchtest du bezahlen? \n 1: Bank | 2: Kreditkarte");

		aktion = sc.nextLine();

		switch (aktion) {
		case "1":

			System.out.println("Bankname:\n");
			bank = sc.nextLine();
			System.out.println("Bankleitzahl:\n");
			bankCode = sc.nextLong();
			System.out.println("Kontonummer:\n");
			accountNumber = sc.nextLong();

			BankAccount myBank = new BankAccount(bank, bankCode, accountNumber, 100000.00);

			myBank.payMoney(this.shoppingCart.getTotalCost());

			break;

		case "2":

			System.out.println("Kartennummer:\n");
			cardNumber = sc.nextLong();
			System.out.println("Verfallsdatum (DDMMYYYY):\n");
			expDate = sc.nextInt();
			System.out.println("CVV-Code (dreistellig):\n");
			cvv = sc.nextInt();

			CreditCard myCard = new CreditCard(cardNumber, expDate, cvv, 100000.00);

			myCard.payMoney(this.shoppingCart.getTotalCost());

			break;

		}

	}

}
