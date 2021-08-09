package onlineShop;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Order {

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
		boolean buyLoop = true;
		long cardNumber;
		double cartPrice;
		int expDate;
		int cvv;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		cartPrice = this.shoppingCart.getTotalCost();

		System.out.println("------------------------------------------------");
		System.out.println("Bestellung eingegangen.");
		System.out.println("------------------------------------------------");
		System.out.println("Anzahl an Produkten im Warenkorb: " + this.shoppingCart.getTotalItems() + "\nGesamtkosten: "
				+ cartPrice);
		System.out.println("------------------------------------------------");
		System.out.println("Gebe zu erst deine Lieferadresse an: \n");
		adresse = sc.nextLine();

		while (buyLoop) {
			System.out.println("Wie möchtest du bezahlen? \n1: Bank | 2: Kreditkarte");

			aktion = sc.nextLine();

			switch (aktion) {
			case "1":
				buyLoop = false;
				try {
					System.out.println("Bankname:");
					bank = sc.nextLine();
					System.out.println("Bankleitzahl:");
					bankCode = sc.nextLong();
					System.out.println("Kontonummer:");
					accountNumber = sc.nextLong();

					BankAccount myBank = new BankAccount(bank, bankCode, accountNumber,
							Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

					myBank.payMoneyBank(this.shoppingCart.getTotalCost());

					System.out.println("Eine PDF wurde auf deinem Desktop abgelegt.");
					shoppingCart.createPDFBank(adresse, bank, bankCode, accountNumber);

					System.out.println("Erfolg! Du hast gerade " + cartPrice + " € bezahlt.\nLieferadresse: " + adresse
							+ "\nDu hast noch " + myBank.getBankBalance() + "€ zur Verfügung.");
				} catch (InputMismatchException e) {
					System.out.println(e);
				}
				break;

			case "2":
				buyLoop = false;
				try {
					System.out.println("Kartennummer:\n");
					cardNumber = sc.nextLong();
					System.out.println("Verfallsdatum (DDMMYYYY):\n");
					expDate = sc.nextInt();
					System.out.println("CVV-Code (dreistellig):\n");
					cvv = sc.nextInt();

					CreditCard myCard = new CreditCard(cardNumber, expDate, cvv,
							Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

					myCard.payMoneyCard(this.shoppingCart.getTotalCost());

					System.out.println("Eine PDF wurde auf deinem Desktop abgelegt.");
					shoppingCart.createPDFCard(adresse, cardNumber, cvv);

					System.out.println("Erfolg! Du hast gerade " + cartPrice + " € bezahlt.\nLieferadresse: " + adresse
							+ "\nDu hast noch " + myCard.getCardBalance() + "€ zur Verfügung.");
				} catch (InputMismatchException e) {
					System.out.println(e);
				}
				break;
			default:
				buyLoop = true;
				System.out.println("Bitte gebe eine gültige Zahl ein.");
				break;

			}
		}

	}

	public void completeOrderBusiness(BusinessCustomer targetCustomer) {

		String adresse;
		String aktion;
		String bank;
		String discount;
		long bankCode;
		long accountNumber;
		long cardNumber;
		double discounted;
		boolean buyLoop = true;
		double cartPrice;
		int expDate;
		int cvv;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		cartPrice = this.shoppingCart.getTotalCost();

		if (targetCustomer.getBusinessSize() == 3) {
			discount = "10%";
			discounted = Math.round((this.shoppingCart.getTotalCost() / 100) * 90);
		} else if (targetCustomer.getBusinessSize() == 2) {
			discount = "5%";
			discounted = Math.round((this.shoppingCart.getTotalCost() / 100) * 95);
		} else {
			discount = "2%";
			discounted = Math.round((this.shoppingCart.getTotalCost() / 100) * 98);
		}

		System.out.println("------------------------------------------------");
		System.out.println("Bestellung eingegangen.");
		System.out.println("------------------------------------------------");
		System.out.println("Anzahl an Produkten im Warenkorb: " + this.shoppingCart.getTotalItems());
		System.out
				.println("Du bist Unternehmensgröße: " + targetCustomer.getBusinessSize() + " -> Rabatt: " + discount);
		System.out.println("------------------------------------------------");
		System.out.println("Preis mit Rabatt: " + discounted);
		System.out.println("------------------------------------------------");
		System.out.println("Gebe zu erst deine Lieferadresse an: \n");
		adresse = sc.nextLine();

		while (buyLoop) {
			System.out.println("Wie möchtest du bezahlen? \n 1: Bank | 2: Kreditkarte");

			aktion = sc.nextLine();

			switch (aktion) {

			// Bezahlung per Bank
			case "1":
				try {
					System.out.println("Bankname:\n");
					bank = sc.nextLine();
					System.out.println("Bankleitzahl:\n");
					bankCode = sc.nextLong();
					System.out.println("Kontonummer:\n");
					accountNumber = sc.nextLong();

					BankAccount myBank = new BankAccount(bank, bankCode, accountNumber,
							Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

					myBank.payMoneyBank(this.shoppingCart.getTotalCost());

					System.out.println("Eine PDF wurde auf deinem Desktop abgelegt.");
					shoppingCart.createPDFBank(adresse, bank, bankCode, accountNumber);

					System.out.println("Erfolg! Du hast gerade " + discounted + " € bezahlt.\nLieferadresse: " + adresse
							+ "\nDu hast noch " + myBank.getBankBalance() + "€ zur Verfügung.");
					buyLoop = false;
				} catch (InputMismatchException e) {
					System.out.println(e);
				}
				break;

			// Bezahlung per Kreditkarte
			case "2":
				try {
					System.out.println("Kartennummer:\n");
					cardNumber = sc.nextLong();
					System.out.println("Verfallsdatum (DDMMYYYY):\n");
					expDate = sc.nextInt();
					System.out.println("CVV-Code (dreistellig):\n");
					cvv = sc.nextInt();

					CreditCard myCard = new CreditCard(cardNumber, expDate, cvv,
							Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

					myCard.payMoneyCard(this.shoppingCart.getTotalCost());

					System.out.println("Eine PDF wurde auf deinem Desktop abgelegt.");
					shoppingCart.createPDFCard(adresse, cardNumber, cvv);

					System.out.println("Erfolg! Du hast gerade " + cartPrice + " € bezahlt.\nLieferadresse: " + adresse
							+ "\nDu hast noch " + myCard.getCardBalance() + "€ zur Verfügung.");
					buyLoop = false;
				} catch (InputMismatchException e) {
					System.out.println(e);
				}

				break;

			// Falsche Eingabe
			default:
				buyLoop = true;
				System.out.println("Bitte gebe eine gültige Zahl ein.");
				break;
			}
		}

	}

}
