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
		boolean detailLoop = true;
		boolean detailLoopCard = true;
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
		System.out.println("Gebe zu erst deine Lieferadresse an:");
		adresse = sc.nextLine();

		// Solange nicht alle Eingaben zur Bezahlung richtig eingegeben wurden
		while (buyLoop) {
			System.out.println("------------------------------------------------");
			System.out.println("Wie möchtest du bezahlen? \n1: Bank | 2: Kreditkarte");

			aktion = sc.nextLine();

			switch (aktion) {

			// Bezahlung per Bank
			case "1":
				// Solange nicht alle Eingaben richtig sind
				while (detailLoop) {
					try {
						System.out.println("Bankname:");
						bank = sc.nextLine();
						sc.nextLine();
						System.out.println("Bankleitzahl:");
						bankCode = sc.nextLong();
						sc.nextLine();
						System.out.println("Kontonummer:");
						accountNumber = sc.nextLong();
						sc.nextLine();
						BankAccount myBank = new BankAccount(bank, bankCode, accountNumber,
								Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

						System.out.println("Eine PDF wurde auf deinem Desktop abgelegt.");
						shoppingCart.createPDFBank(adresse, bank, bankCode, accountNumber);
						System.out.println("------------------------------------------------");
						System.out.println(
								"Erfolg! Du hast gerade " + cartPrice + " € bezahlt.\nLieferadresse: " + adresse);
						System.out.println("------------------------------------------------");
						buyLoop = false;
						detailLoop = false;
					} catch (InputMismatchException e) {
						System.out.println("------------------------------------------------");
						System.out.println("Fehler: Falscher Input. Versuche es bitte erneut");
						System.out.println("------------------------------------------------");
						sc.next();
					}
				}
				break;
			// Bezahlung per Kreditkarte
			case "2":
				// Solange nicht alle Eingaben richtig sind
				while (detailLoopCard) {
					try {
						System.out.println("Kartennummer:");
						cardNumber = sc.nextLong();
						sc.nextLine();
						System.out.println("Verfallsdatum (DDMMYYYY):");
						expDate = sc.nextInt();
						sc.nextLine();
						System.out.println("CVV-Code (dreistellig):");
						cvv = sc.nextInt();
						sc.nextLine();

						CreditCard myCard = new CreditCard(cardNumber, expDate, cvv,
								Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

						myCard.payMoneyCard(this.shoppingCart.getTotalCost());

						System.out.println("Eine PDF wurde auf deinem Desktop abgelegt.");
						shoppingCart.createPDFCard(adresse, cardNumber, cvv);
						System.out.println("------------------------------------------------");
						System.out.println(
								"Erfolg! Du hast gerade " + cartPrice + " € bezahlt.\nLieferadresse: " + adresse);
						System.out.println("------------------------------------------------");
						buyLoop = false;
						detailLoopCard = false;
					} catch (InputMismatchException e) {
						System.out.println("------------------------------------------------");
						System.out.println("Fehler: Falscher Input. Versuche es bitte erneut");
						System.out.println("------------------------------------------------");
						sc.next();
					}
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
		boolean detailLoop = true;
		boolean detailLoopCard = true;
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
		System.out.println("Gebe zu erst deine Lieferadresse an:");
		adresse = sc.nextLine();

		// Solange nicht alle Eingaben zur Bezahlung richtig eingegeben wurden
		while (buyLoop) {
			System.out.println("------------------------------------------------");
			System.out.println("Wie möchtest du bezahlen? \n 1: Bank | 2: Kreditkarte");

			aktion = sc.nextLine();

			switch (aktion) {

			// Bezahlung per Bank
			case "1":
				// Solange nicht alle Eingaben richtig sind
				while (detailLoop) {
					try {
						System.out.println("Bankname:");
						bank = sc.nextLine();
						System.out.println("Bankleitzahl:");
						bankCode = sc.nextLong();
						sc.nextLine();
						System.out.println("Kontonummer:");
						accountNumber = sc.nextLong();
						sc.nextLine();

						BankAccount myBank = new BankAccount(bank, bankCode, accountNumber,
								Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

						System.out.println("Eine PDF wurde auf deinem Desktop abgelegt.");
						shoppingCart.createPDFBank(adresse, bank, bankCode, accountNumber);

						System.out.println("------------------------------------------------");
						System.out.println(
								"Erfolg! Du hast gerade " + discounted + " € bezahlt.\nLieferadresse: " + adresse);
						System.out.println("------------------------------------------------");
						buyLoop = false;
						detailLoop = false;
					} catch (InputMismatchException e) {
						System.out.println("------------------------------------------------");
						System.out.println("Fehler: Falscher Input. Versuche es bitte erneut");
						System.out.println("------------------------------------------------");
						sc.next();
					}
				}

				break;

			// Bezahlung per Kreditkarte
			case "2":
				// Solange nicht alle Eingaben richtig sind
				while (detailLoopCard) {
					try {
						System.out.println("Kartennummer:");
						cardNumber = sc.nextLong();
						sc.nextLine();
						System.out.println("Verfallsdatum (DDMMYYYY):");
						expDate = sc.nextInt();
						sc.nextLine();
						System.out.println("CVV-Code (dreistellig):");
						cvv = sc.nextInt();
						sc.nextLine();

						CreditCard myCard = new CreditCard(cardNumber, expDate, cvv,
								Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

						myCard.payMoneyCard(this.shoppingCart.getTotalCost());

						System.out.println("Eine PDF wurde auf deinem Desktop abgelegt.");
						shoppingCart.createPDFCard(adresse, cardNumber, cvv);
						System.out.println("------------------------------------------------");
						System.out.println(
								"Erfolg! Du hast gerade " + cartPrice + " € bezahlt.\nLieferadresse: " + adresse);
						System.out.println("------------------------------------------------");
						buyLoop = false;
						detailLoopCard = false;
					} catch (InputMismatchException e) {
						System.out.println("------------------------------------------------");
						System.out.println("Fehler: Falscher Input. Versuche es bitte erneut");
						System.out.println("------------------------------------------------");
						sc.next();
					}
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
