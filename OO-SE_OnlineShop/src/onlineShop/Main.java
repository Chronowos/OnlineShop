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

	public static String isBusiness;

	public static void main(String[] args) throws IOException {

		// Erstellung Einkaufswagen- und Produktkatalog-Objekt
		ShoppingCart myShopCart = new ShoppingCart();
		ProductCatalog myProdCatalog = new ProductCatalog();

		// Erstellung Privat- und Geschäftskunden-Objekt
		Customer customer1 = new Customer("Test", "Test");
		BusinessCustomer businessCustomer1 = new BusinessCustomer("Test", "Test", 1);

		// Variablen deklarieren
		Boolean isRunning = true;
		Boolean caseRunning = true;
		Boolean shoppingCartRunning = true;
		Products[] prodArray = new Products[10];
		String line = "------------------------------------------------";
		String aktion;
		// String isBusiness;
		int productNumberToSearch;
		int productNumber;
		int productQuantity;

		// Auslesung von Inputs des Users
		Scanner sc = new Scanner(System.in);

		// Customer-Objekt, wenn Speicherdatei vorhanden
		System.out.println("Bist du Privat- oder Geschäftskunde?\n" + "1: Privatkunde | 2: Geschäftskunde");
		isBusiness = sc.nextLine();
		if (isBusiness.equals("1")) {
			customer1 = loginProcessCustomer();
		} else {
			businessCustomer1 = loginProcessBusiness();
		}

		// Produkt-Array befüllen
		prodArray = fillProdListingArray();

		// For-Loop um die Produkte in den Shop zu laden (Zuweisung zur ArrayList)
		for (int i = 0; i <= 9; i++) {
			myProdCatalog.addProductToListing(prodArray[i]);
		}

		while (isRunning) {
			System.out.println("\nWas möchtest du tun?");
			// Wichtig für Erweiterungen Println und Case aktualisieren!
			System.out.println("1: Produktkatalog anschauen \n" + "2: Warenkorb anschauen \n" + "3: Programm beenden");
			System.out.println(line);
			aktion = sc.nextLine().toLowerCase();

			// Bei Erweiterung, neue Case erstellen
			switch (aktion) {

			// Produktkatalog anschauen
			case "1":

				while (caseRunning) {
					myProdCatalog.printListing();
					System.out.println("\nWas möchtest du nun tun?");
					System.out.println(
							"1: Produkt in den Warenkorb hinzufügen\n" + "2: Sortiment sortieren\n" + "3: Zurück");
					System.out.println(line);
					aktion = sc.nextLine().toLowerCase();

					switch (aktion) {

					// Produkt in den Warenkorb hinzufügen
					case "1":
						System.out.println(line);
						System.out.println(
								"Welches Produkt möchtest du in den Warenkorb hinzufügen? Bitte gib die Artikelnummer an!");
						System.out.println(line);
						productNumberToSearch = sc.nextInt();
						productNumber = findPosInArray(prodArray, productNumberToSearch);
						System.out.println("Wie viele möchtest du davon? Verfügbar von: "
								+ prodArray[productNumber].getProductName() + ", "
								+ prodArray[productNumber].getQuantity() + " Stück.");
						productQuantity = sc.nextInt();
						myShopCart.addProductToCart(prodArray[productNumber], productQuantity);
						saveCatalogue(prodArray);
						break;

					// Sortiment sortieren
					case "2":
						System.out.println(
								"Wie soll sortiert werden?\n" + "1: Preis aufsteigend\n" + "2: Preis absteigend");
						System.out.println(line);
						aktion = sc.nextLine().toLowerCase();
						if (aktion.equals("1")) {
							myProdCatalog.sortAfterPriceAsc();
							// myProdCatalog.printListing();
						} else if (aktion.equals("2")) {
							myProdCatalog.sortAfterPriceDesc();
							// myProdCatalog.printListing();
						}
						break;

					// Zurück
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
					System.out.println("\nWas möchtest du nun tun?");
					System.out.println("1: Warenkorb kaufen\n" + "2: Warenkorb bearbeiten\n" + "3: Zurück");
					aktion = sc.nextLine().toLowerCase();

					switch (aktion) {

					// Warenkorb kaufen
					case "1":
						Order myOrder = new Order(myShopCart);
						if (Main.isBusiness.equals("1")) {
							System.out.println("Privat");
							myOrder.completeOrder(customer1);
						} else {
							System.out.println("Geschäft");
							// myOrder.completeOrder(businessCustomer1);
						}
						shoppingCartRunning = false;
						break;

					// Warenkorb bearbeiten
					case "2":
						shoppingCartRunning = false;
						break;

					// Zurück
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
				System.out.println("Danke für's benutzen!");
				break;

			default:
				System.out.println("Bitte gebe eine gültige Zahl ein! (1 - 3)");
				break;
			}

		}

		sc.close();

	}

	public static int findPosInArray(Products[] myArray, long pNumber) {

		for (int i = 0; i < myArray.length; i++) {
			if (myArray[i].getProductNumber() == pNumber) {
				return i;
			}
		}
		return -1;

	}

	public static Products[] fillProdListingArray() {

		Products[] prodArray = new Products[10];
		Products[] serArray = new Products[10];
		String input;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		File checkFile = new File(System.getProperty("user.home") + "\\Desktop\\ProductCatalogue.ser");

		if (checkFile.exists()) {

			System.out.println("Ein Warenkorb wurde als Datei gefunden, möchtest du diesen nutzen? (1-2)\n" + "1: Ja\n"
					+ "2: Nein");
			input = sc.nextLine();
			if (input.equals("1")) {
				serArray = loadCatalogue();
				return serArray;
			} else {
				System.out.println("Standard Produktkatalog wurde geladen.");
			}

		}

		// Produkt-Objekte instanziieren und in einem Array speichern, für einfachen
		// späteren Zugriff
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

		return prodArray;

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

	public static Customer loginProcessCustomer() {

		String aktion;
		Customer loginCustomer = new Customer("Empty", "Empty");
		String passwort;
		String benutzername;
		String regBenutzername;
		String regPasswort;
		Boolean loginLoop = true;

		// Auslesung von Inputs des Users
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Willkommen im Anmeldeprozess! Was möchtest du tun?");
		System.out.println("1: Anmelden | 2: Registrieren");

		aktion = sc.nextLine();

		switch (aktion) {
		case "1":
			File checkFile = new File(System.getProperty("user.home") + "\\Desktop\\LoginDetails.ser");
			if (checkFile.exists()) {
				while (loginLoop) {

					loginCustomer = readLoginCustomer();
					System.out.println("Dein Benutzername: ");
					benutzername = sc.nextLine();
					System.out.println("Dein Passwort: ");
					passwort = sc.nextLine();

					if (benutzername.equals(loginCustomer.getCustomerName())
							&& passwort.equals(loginCustomer.getPassword())) {
						loginLoop = false;
						return loginCustomer;
					} else {
						System.out.println("Deine Daten waren leider falsch.");
					}
				}
			} else {
				System.out.println("Serverprobleme. (Logindatei wurde nicht gefunden)");
			}
			break;

		case "2":
			System.out.println("Dein Benutzername (Registrierung): ");
			regBenutzername = sc.nextLine();
			System.out.println("Dein Passwort (Registrierung): ");
			regPasswort = sc.nextLine();
			loginCustomer = new Customer(regBenutzername, regPasswort);
			break;

		}

		// loginCustomer = new Customer(regBenutzername, regPasswort);

		writeLogin(loginCustomer);

		return loginCustomer;

	}

	public static Customer readLoginCustomer() {

		Customer returnCustomer = new Customer("Empty", "Empty");

		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "\\Desktop\\LoginDetails.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			returnCustomer = (Customer) ois.readObject();
			ois.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return returnCustomer;
	}

	public static void writeLogin(Customer customer) {
		try {
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.home") + "\\Desktop\\LoginDetails.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(customer);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BusinessCustomer loginProcessBusiness() {

		String aktion;
		BusinessCustomer loginCustomer = new BusinessCustomer("Empty", "Empty", 1);
		String benutzername;
		String passwort;
		String regBenutzername;
		String regPasswort;
		Boolean loginLoop = true;
		int businessSize;

		// Auslesung von Inputs des Users
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Willkommen im Anmeldeprozess! Was möchtest du tun?");
		System.out.println("1: Anmelden | 2: Registrieren");

		aktion = sc.nextLine();

		switch (aktion) {
		case "1":
			File checkFile = new File(System.getProperty("user.home") + "\\Desktop\\LoginDetails.ser");
			if (checkFile.exists()) {
				while (loginLoop) {

					loginCustomer = readLoginBusiness();
					System.out.println("Dein Benutzername: ");
					benutzername = sc.nextLine();
					System.out.println("Dein Passwort: ");
					passwort = sc.nextLine();

					if (benutzername.equals(loginCustomer.getCustomerName())
							&& passwort.equals(loginCustomer.getPassword())) {
						return loginCustomer;
					} else {
						System.out.println("Deine Daten waren leider falsch.");
					}
				}
			} else {
				System.out.println("Serverprobleme. (Logindatei wurde nicht gefunden)");
			}
			break;
		case "2":
			System.out.println("Dein Benutzername (Registrierung): ");
			regBenutzername = sc.nextLine();
			System.out.println("Dein Passwort (Registrierung): ");
			regPasswort = sc.nextLine();
			System.out.println("Deine Unternehmensgröße 1-3");
			businessSize = sc.nextInt();
			loginCustomer = new BusinessCustomer(regBenutzername, regPasswort, businessSize);
			break;

		}

		// loginCustomer = new Customer(regBenutzername, regPasswort);

		writeLogin(loginCustomer);

		return loginCustomer;

	}

	public static BusinessCustomer readLoginBusiness() {

		BusinessCustomer returnCustomer = new BusinessCustomer("Empty", "Empty", 1);

		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "\\Desktop\\LoginDetails.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			returnCustomer = (BusinessCustomer) ois.readObject();
			ois.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return returnCustomer;
	}

	public static void writeLoginBusiness(BusinessCustomer customer) {
		try {
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.home") + "\\Desktop\\LoginDetails.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(customer);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
