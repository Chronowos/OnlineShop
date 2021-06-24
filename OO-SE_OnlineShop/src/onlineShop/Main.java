package onlineShop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		// Erstellung Einkaufswagen- und Produktkatalog-Objekt
		ShoppingCart myShopCart = new ShoppingCart();
		ProductCatalog myProdCatalog = new ProductCatalog();

		// Variablen deklarieren
		Boolean isRunning = true;
		Boolean caseRunning = true;
		Boolean shoppingCartRunning = true;
		Products[] prodArray = new Products[10];
		String inputName;
		String inputPassword;
		String aktion;
		int productNumberToSearch;
		int productNumber;
		int productQuantity;

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

			Customer customer1 = new Customer("Herbert", "Winkler", "");
			printWelcomeMessage(inputName);

		} else if (inputName.equals(uKundeName.toLowerCase()) && inputPassword.equals(uKundePassword.toLowerCase())) {

			BusinessCustomer businessCustomer1 = new BusinessCustomer("Michael", "Wendler", "", 3);
			printWelcomeMessage(inputName);

		} else {
			System.out.println("Benutzername oder Password falsch, versuche es bitte erneut!");
			isRunning = false;
		}

		while (isRunning) {
			System.out.println("\nWas m�chtest du tun?");
			// Wichtig f�r Erweiterungen Println und Case aktualisieren!
			System.out.println("1: Produktkatalog anschauen \n" + "2: Warenkorb anschauen \n" + "3: Programm beenden");
			System.out.println("------------------------------------------------");
			aktion = sc.nextLine().toLowerCase();

			// Bei Erweiterung, neue Case erstellen
			switch (aktion) {

			// Produktkatalog anschauen
			case "1":

				while (caseRunning) {
					myProdCatalog.printListing();
					System.out.println("\nWas m�chtest du nun tun?");
					System.out.println(
							"1: Produkt in den Warenkorb hinzuf�gen\n" + "2: Sortiment sortieren\n" + "3: Zur�ck");
					System.out.println("------------------------------------------------");
					aktion = sc.nextLine().toLowerCase();

					switch (aktion) {

					// Produkt in den Warenkorb hinzuf�gen
					case "1":
						System.out.println("------------------------------------------------");
						System.out.println(
								"Welches Produkt m�chtest du in den Warenkorb hinzuf�gen? Bitte gib die Artikelnummer an!");
						System.out.println("------------------------------------------------");
						productNumberToSearch = sc.nextInt();
						productNumber = findPosInArray(prodArray, productNumberToSearch);
						System.out.println("Wie viele m�chtest du davon? Verf�gbar von: "
								+ prodArray[productNumber].getProductName() + ", "
								+ prodArray[productNumber].getQuantity() + " St�ck.");
						productQuantity = sc.nextInt();
						myShopCart.addProductToCart(prodArray[productNumber], productQuantity);
						saveCatalogue(prodArray);
						break;

					// Sortiment sortieren
					case "2":
						System.out.println(
								"Wie soll sortiert werden?\n" + "1: Preis aufsteigend\n" + "2: Preis absteigend");
						System.out.println("------------------------------------------------");
						aktion = sc.nextLine().toLowerCase();
						if (aktion.equals("1")) {
							myProdCatalog.sortAfterPriceAsc();
							// myProdCatalog.printListing();
						} else if (aktion.equals("2")) {
							myProdCatalog.sortAfterPriceDesc();
							// myProdCatalog.printListing();
						}
						break;

					// Zur�ck
					case "3":
						caseRunning = false;
						break;
					}
				}
				caseRunning = true;
				break;

			// Warenkorb anschauen
			case "2":

				while (shoppingCartRunning) {

					myShopCart.printProducts();
					System.out.println("\nWas m�chtest du nun tun?");
					System.out.println("1: Kaufen\n" + "2: Bearbeiten\n" + "3: Zur�ck");
					aktion = sc.nextLine().toLowerCase();

					switch (aktion) {

					case "1":
					case "2":
					case "3":
						shoppingCartRunning = false;
						break;
					}
				}

				shoppingCartRunning = true;
				break;

			// Shop beenden
			case "3":
				isRunning = false;
				System.out.println("Danke f�r's benutzen!");
				break;

			default:
				System.out.println("Bitte gebe eine g�ltige Zahl ein! (1 - 3)");
				break;
			}

		}

		sc.close();

	}

	public static Products[] fillProdListingArray() {

		Products[] prodArray = new Products[10];
		Products[] serArray = new Products[10];

		File checkFile = new File(System.getProperty("user.home") + "\\Desktop\\ProductCatalogue.ser");

		if (checkFile.exists()) {
			serArray = loadCatalogue();
			return serArray;
		}

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
		@SuppressWarnings("resource")
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
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Password:");
		inputPassword = sc.nextLine();
		inputPassword = inputPassword.toLowerCase();

		return inputPassword;
	}

	public static void printWelcomeMessage(String name) {

		System.out.println("------------------------------------------------");
		System.out.println("Anmeldung erfolgreich, willkommen " + name.toUpperCase());
		System.out.println("------------------------------------------------");

	}

	public static int findPosInArray(Products[] myArray, long pNumber) {

		for (int i = 0; i < myArray.length; i++) {
			if (myArray[i].getProductNumber() == pNumber) {
				return i;
			}
		}
		return -1;

	}

	public static void saveCatalogue(Products[] prodArray) {

		try {
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.home") + "\\Desktop\\ProductCatalogue.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(prodArray);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Products[] loadCatalogue() {

		Products[] returnArray = new Products[10];

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.home") + "\\Desktop\\ProductCatalogue.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			returnArray = (Products[]) ois.readObject();
			ois.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return returnArray;
	}

}
