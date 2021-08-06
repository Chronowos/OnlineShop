package onlineShop;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public class Order {

	private ShoppingCart shoppingCart;

	public Order(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public void completeOrder(Customer targetCustomer) {

		String adresse;
		String aktion;
		String bank;
		String pdf_path;
		long bankCode;
		long accountNumber;
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

		System.out.println("Wie m�chtest du bezahlen? \n1: Bank | 2: Kreditkarte");

		aktion = sc.nextLine();

		switch (aktion) {
		case "1":

			System.out.println("Bankname:");
			bank = sc.nextLine();
			System.out.println("Bankleitzahl:");
			bankCode = sc.nextLong();
			System.out.println("Kontonummer:");
			accountNumber = sc.nextLong();

			BankAccount myBank = new BankAccount(bank, bankCode, accountNumber,
					Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

			myBank.payMoneyBank(this.shoppingCart.getTotalCost());

			// PDF ERSTELLUNG ANFANG
			try {
				pdf_path = System.getProperty("user.home") + "\\Desktop\\Quittung.pdf";

				Document myDoc = new Document();
				PdfWriter.getInstance(myDoc, new FileOutputStream(pdf_path));

				myDoc.open();

				myDoc.close();

			} catch (FileNotFoundException | DocumentException e) {
				// TODO Auto-generated catch block
				System.out.println("Fehler PDF-Erstellung");
				e.printStackTrace();
			}

			// PDF ERSTELLUNG ENDE

			System.out.println("Erfolg! Du hast gerade " + cartPrice + " � bezahlt.\nLieferadresse: " + adresse
					+ "\nDu hast noch " + myBank.getBankBalance() + "� zur Verf�gung.");
			break;

		case "2":

			System.out.println("Kartennummer:\n");
			cardNumber = sc.nextLong();
			System.out.println("Verfallsdatum (DDMMYYYY):\n");
			expDate = sc.nextInt();
			System.out.println("CVV-Code (dreistellig):\n");
			cvv = sc.nextInt();

			CreditCard myCard = new CreditCard(cardNumber, expDate, cvv,
					Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

			myCard.payMoneyCard(this.shoppingCart.getTotalCost());

			break;
		default:
			System.out.println("Bitte gebe eine g�ltige Zahl ein.");
			break;

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
		int expDate;
		int cvv;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

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
				.println("Du bist Unternehmensgr��e: " + targetCustomer.getBusinessSize() + " -> Rabatt: " + discount);
		System.out.println("------------------------------------------------");
		System.out.println("Preis mit Rabatt: " + discounted);
		System.out.println("------------------------------------------------");
		System.out.println("Gebe zu erst deine Lieferadresse an: \n");
		adresse = sc.nextLine();

		System.out.println("Wie m�chtest du bezahlen? \n 1: Bank | 2: Kreditkarte");

		aktion = sc.nextLine();

		switch (aktion) {

		// Bezahlung per Bank
		case "1":

			System.out.println("Bankname:\n");
			bank = sc.nextLine();
			System.out.println("Bankleitzahl:\n");
			bankCode = sc.nextLong();
			System.out.println("Kontonummer:\n");
			accountNumber = sc.nextLong();

			BankAccount myBank = new BankAccount(bank, bankCode, accountNumber,
					Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

			myBank.payMoneyBank(this.shoppingCart.getTotalCost());

			System.out.println("Erfolg! Du hast gerade " + discounted + " � bezahlt.\nLieferadresse: " + adresse
					+ "\nDu hast noch " + myBank.getBankBalance() + "� zur Verf�gung.");
			break;

		// Bezahlung per Kreditkarte
		case "2":

			System.out.println("Kartennummer:\n");
			cardNumber = sc.nextLong();
			System.out.println("Verfallsdatum (DDMMYYYY):\n");
			expDate = sc.nextInt();
			System.out.println("CVV-Code (dreistellig):\n");
			cvv = sc.nextInt();

			CreditCard myCard = new CreditCard(cardNumber, expDate, cvv,
					Math.round(ThreadLocalRandom.current().nextDouble(2000.00, 10000.00)));

			myCard.payMoneyCard(this.shoppingCart.getTotalCost());

			break;

		// Falsche Eingabe
		default:
			System.out.println("Bitte gebe eine g�ltige Zahl ein.");
			break;
		}

	}

}
