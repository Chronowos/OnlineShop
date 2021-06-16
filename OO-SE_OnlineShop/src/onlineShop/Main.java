package onlineShop;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		//Instanziieren des Einkaufswagen und des Produktkatalogs
		ShoppingCart myShopCart = new ShoppingCart();
		ProductCatalog myProdCatalog = new ProductCatalog();

		//Variablen deklarieren
		Boolean isRunning = true;
		Products[] prodArray = new Products[10];
		String inputName;
		String inputPassword;
		String aktion;

		//Auslesung von Inputs
		Scanner sc = new Scanner(System.in);

		//Anmeldedaten für Privatkunde
		String privKundeName = "DanielMeier123";
		String privKundePassword = "Obunga123";

		//Anmeldedaten für Firmenkunde
		String uKundeName = "GeldIstCool";
		String uKundePassword = "IchBinReich123";
		
		//Produkt-Objekte instanziieren und in einem Array speichern, für einfachen späteren Zugriff
		prodArray[0] = new Products(104, "Kettensäge", 7, 550.00, 0);
		prodArray[1] = new Products(710, "Panzertape", 25, 5.00, 0);
		prodArray[2] = new Products(471, "SkiMaske", 37, 25.00, 0);
		prodArray[3] = new Products(293, "Baseballschläger", 8, 75.00, 0);
		prodArray[4] = new Products(510, "Tisch", 5, 250.00, 0);
		prodArray[5] = new Products(410, "Laptop", 11, 1050.00, 0);
		prodArray[6] = new Products(790, "Tastatur", 5, 110.00, 0);
		prodArray[7] = new Products(669, "Kopfhörer", 5, 250.00, 0);
		prodArray[8] = new Products(912, "Bildschirm", 3, 478.00, 0);
		prodArray[9] = new Products(244, "Maus", 7, 45.00, 0);
		
		//For-Loop um die Produkte in den Shop zu laden (Zuweisung zur ArrayList)
		for(int i = 0; i<=9; i++) {
			myProdCatalog.addProductToListing(prodArray[i]);
		}
		
		System.out.println("------------------------------------------------");
		System.out.println("Willkommen in unserem kleinen, aber feinen Shop. \n"
				+ "Schau dich gerne etwas um und durchstöbere unser Produktsortiment - vielleicht findest du ja etwas tolles!");
		System.out.println("------------------------------------------------ \n");
		System.out.println("Logge dich bitte bei uns eins!\n");

		System.out.println("Benutzername:");
		inputName = sc.nextLine();
		inputName = inputName.toLowerCase();

		System.out.println("Password:");
		inputPassword = sc.nextLine();
		inputPassword = inputPassword.toLowerCase();

		if (inputName.equals(privKundeName.toLowerCase()) && inputPassword.equals(privKundePassword.toLowerCase())) {

			Customer customer1 = new Customer("Herbert", "Winkler", "Kuckhoffstraße 2");
			System.out.println("------------------------------------------------");
			System.out.println("(Privat) Anmeldung erfolgreich, willkommen " + customer1.getCustomerName());
			System.out.println("------------------------------------------------");

		} else if (inputName.equals(uKundeName.toLowerCase()) && inputPassword.equals(uKundePassword.toLowerCase())) {

			BusinessCustomer businessCustomer1 = new BusinessCustomer("Michael", "Wendler", "Prenzlauer Allee 17", 3);
			System.out.println("------------------------------------------------");
			System.out.println("(Firma) Anmeldung erfolgreich, willkommen " + businessCustomer1.getCustomerName());
			System.out.println("------------------------------------------------");

		} else {
			System.out.println("Benutzername oder Password falsch, versuche es bitte erneut!");
			isRunning = false;
		}

		while (isRunning) {
			System.out.println("\nWas möchtest du tun?");
			System.out.println("1: Produktkatalog anschauen \n" + "2: Warenkorb anschauen \n"
					+ "3: Bezahlvorgang starten \n" + "4: Programm beenden");
			System.out.println("------------------------------------------------");
			aktion = sc.nextLine().toLowerCase();

			switch (aktion) {
			case "1":
				System.out.println("input 1");
				break;
			case "2":
				System.out.println("input 2");
				break;
			case "3":
				System.out.println("input 3");
				break;
			case "4":
				isRunning = false;
				System.out.println("Danke für's benutzen!");
			default:
				System.out.println("Bitte gebe eine gültige Zahl ein! (1 - 4)");
			}

		}

		myProdCatalog.printListing();
		sc.close();

	}

}
