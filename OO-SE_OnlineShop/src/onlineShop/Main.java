package onlineShop;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		// Instanziieren des Einkaufswagen und des Produktkatalogs
		ShoppingCart myShopCart = new ShoppingCart();
		ProductCatalog myProdCatalog = new ProductCatalog();

		// Variablen deklarieren
		Boolean isRunning = true;
		Products[] prodArray = new Products[10];
		String inputName;
		String inputPassword;
		String aktion;

		// Auslesung von Inputs des Users
		Scanner sc = new Scanner(System.in);

		// Anmeldedaten f�r Privatkunde
		String privKundeName = "Leon";
		String privKundePassword = "Leon";

		// Anmeldedaten f�r Firmenkunde
		String uKundeName = "GeldIstCool";
		String uKundePassword = "IchBinReich123";

		// Produkt-Array bef�llen
		prodArray = fillProdListingArray();

		// For-Loop um die Produkte in den Shop zu laden (Zuweisung zur ArrayList)
		for (int i = 0; i <= 9; i++) {
			myProdCatalog.addProductToListing(prodArray[i]);
		}

		// Benutzernamen zuweisen
		inputName = getUsername();

		// Password zuweisen
		inputPassword = getPassword();

		// Kontrolle, welcher Benutzer sich anmeldet
		if (inputName.equals(privKundeName.toLowerCase()) && inputPassword.equals(privKundePassword.toLowerCase())) {

			Customer customer1 = new Customer("Herbert", "Winkler", "Kuckhoffstra�e 2");
			System.out.println("------------------------------------------------");
			System.out.println("(Privat) Anmeldung erfolgreich, willkommen " + inputName.toUpperCase());
			System.out.println("------------------------------------------------");

		} else if (inputName.equals(uKundeName.toLowerCase()) && inputPassword.equals(uKundePassword.toLowerCase())) {

			BusinessCustomer businessCustomer1 = new BusinessCustomer("Michael", "Wendler", "Prenzlauer Allee 17", 3);
			System.out.println("------------------------------------------------");
			System.out.println("(Firma) Anmeldung erfolgreich, willkommen " + inputName.toUpperCase());
			System.out.println("------------------------------------------------");

		} else {
			System.out.println("Benutzername oder Password falsch, versuche es bitte erneut!");
			isRunning = false;
		}

		while (isRunning) {
			System.out.println("\nWas m�chtest du tun?");
			System.out.println("1: Produktkatalog anschauen \n" + "2: Warenkorb anschauen \n"
					+ "3: Bezahlvorgang starten \n" + "4: Programm beenden");
			System.out.println("------------------------------------------------");
			aktion = sc.nextLine().toLowerCase();

			switch (aktion) {
			case "1":

				myProdCatalog.printListing();
				System.out.println("\nWas m�chtest du nun tun?");
				System.out
						.println("1: Produkt in den Warenkorb hinzuf�gen\n" + "2: Sortiment sortieren\n" + "3: Zur�ck");
				System.out.println("------------------------------------------------");
				aktion = sc.nextLine().toLowerCase();

				switch (aktion) {
				case "1":
					System.out.println("Tester");
					break;
				case "2":
					System.out
							.println("Wie soll sortiert werden?\n" + "1: Preis aufsteigend\n" + "2: Preis absteigend");
					System.out.println("------------------------------------------------");
					aktion = sc.nextLine().toLowerCase();
					if (aktion.equals("1")) {
						myProdCatalog.sortAfterPriceAsc();
						myProdCatalog.printListing();
					} else if (aktion.equals("2")) {
						myProdCatalog.sortAfterPriceDesc();
						myProdCatalog.printListing();
					}
					break;
				case "3":
					break;
				}

				break;
			case "2":
				System.out.println("input 2");
				break;
			case "3":
				System.out.println("input 3");
				break;
			case "4":
				isRunning = false;
				System.out.println("Danke f�r's benutzen!");
			default:
				System.out.println("Bitte gebe eine g�ltige Zahl ein! (1 - 4)");
			}

		}

		sc.close();

	}

	public static Products[] fillProdListingArray() {

		Products[] prodArray = new Products[10];

		// Produkt-Objekte instanziieren und in einem Array speichern, f�r einfachen
		// sp�teren Zugriff
		prodArray[0] = new Products(104, "Kettens�ge", 7, 550.00, 0);
		prodArray[1] = new Products(710, "Panzertape", 25, 5.00, 0);
		prodArray[2] = new Products(471, "SkiMaske", 37, 25.00, 0);
		prodArray[3] = new Products(293, "Baseballschl�ger", 8, 75.00, 0);
		prodArray[4] = new Products(510, "Tisch", 5, 250.00, 0);
		prodArray[5] = new Products(410, "Laptop", 11, 1050.00, 0);
		prodArray[6] = new Products(790, "Tastatur", 5, 110.00, 0);
		prodArray[7] = new Products(669, "Kopfh�rer", 5, 250.00, 0);
		prodArray[8] = new Products(912, "Bildschirm", 3, 478.00, 0);
		prodArray[9] = new Products(244, "Maus", 7, 45.00, 0);

		return prodArray;

	}

	public static String getUsername() {

		String inputName;

		// Auslesung von Inputs des Users
		Scanner sc = new Scanner(System.in);

		System.out.println("------------------------------------------------");
		System.out.println("Willkommen in unserem kleinen, aber feinen Shop. \n"
				+ "Schau dich gerne etwas um und durchst�bere unser Produktsortiment - vielleicht findest du ja etwas tolles!");
		System.out.println("------------------------------------------------ \n");
		System.out.println("Logge dich bitte bei uns eins!\n");

		System.out.println("Benutzername:");
		inputName = sc.nextLine();
		inputName = inputName.toLowerCase();

		return inputName;

	}

	public static String getPassword() {

		String inputPassword;

		// Auslesung von Inputs des Users
		Scanner sc = new Scanner(System.in);

		System.out.println("Password:");
		inputPassword = sc.nextLine();
		inputPassword = inputPassword.toLowerCase();

		return inputPassword;
	}

}
