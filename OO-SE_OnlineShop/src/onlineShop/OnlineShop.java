package onlineShop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.itextpdf.text.DocumentException;

public class OnlineShop {

	public static String isBusiness;

	public OnlineShop() {

	}

	public void openShop() throws IOException, DocumentException {

		// Erstellung Einkaufswagen- und Produktkatalog-Objekt
		ShoppingCart myShopCart = new ShoppingCart();
		ProductCatalog myProdCatalog = new ProductCatalog();

		// Erstellung Privat- und Gesch�ftskunden-Objekt
		Customer customer1 = new Customer("Test", "Test");
		BusinessCustomer businessCustomer1 = new BusinessCustomer("Test", "Test", 1);

		// Variablen deklarieren
		Boolean isRunning = true;
		Boolean caseRunning = true;
		Boolean shoppingCartRunning = true;
		Boolean earlyLoop = true;
		Boolean putProductInCart = true;
		Boolean sortProducts = true;
		Boolean changeProductInCart = true;
		Products[] prodArray = new Products[10];
		String line = "------------------------------------------------";
		String aktion;
		String aktion2;
		Products changeProduct;
		int productNumberToSearch;
		int productNumber;
		int productQuantity;
		int productNumberInCart;
		int newQuantity;

		// Auslesung von Inputs des Users
		Scanner sc = new Scanner(System.in);

		// Login-Vorgang
		while (earlyLoop) {
			System.out.println("Bist du Privat- oder Gesch�ftskunde?\n" + "1: Privatkunde | 2: Gesch�ftskunde");
			isBusiness = sc.nextLine();
			if (isBusiness.equals("1")) {
				earlyLoop = false;
				customer1 = loginProcessCustomer();
			} else if (isBusiness.equals("2")) {
				earlyLoop = false;
				businessCustomer1 = loginProcessBusiness();
			} else {
				System.out.println("------------------------------------------------");
				System.out.println("Bitte gebe eine g�ltige Zahl ein.");
				System.out.println("------------------------------------------------\n");
			}

		}

		// Produkt-Array bef�llen
		prodArray = fillProdListingArray();

		// For-Loop um die Produkte in den Shop zu laden (Zuweisung zur ArrayList)
		for (int i = 0; i <= 9; i++) {
			myProdCatalog.addProductToListing(prodArray[i]);
		}

		// Main-Loop
		while (isRunning) {
			System.out.println("\nWas m�chtest du tun?");

			// Wichtig f�r Erweiterungen Println und Case aktualisieren!
			System.out.println("1: Produktkatalog anschauen \n" + "2: Warenkorb anschauen \n" + "3: Programm beenden");
			System.out.println(line);
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
					System.out.println(line);
					aktion2 = sc.nextLine().toLowerCase();

					switch (aktion2) {

					// Produkt in den Warenkorb hinzuf�gen
					case "1":
						while (putProductInCart == true) {
							System.out.println(line);
							System.out.println(
									"Welches Produkt m�chtest du in den Warenkorb hinzuf�gen? Bitte gib die Artikelnummer an!");
							System.out.println(line);
							productNumberToSearch = sc.nextInt();

							try {
								productNumber = findPosInArray(prodArray, productNumberToSearch);
								System.out.println("Wie viele m�chtest du davon? Verf�gbar von: "
										+ prodArray[productNumber].getProductName() + ", "
										+ prodArray[productNumber].getQuantity() + " St�ck.");
								productQuantity = sc.nextInt();
								sc.nextLine();
								myShopCart.addProductToCart(prodArray[productNumber], productQuantity);
								saveCatalogue(prodArray);
								putProductInCart = false;
							} catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
								System.out.println("------------------------------------------------");
								System.out.println("Fehler: Ung�ltige Produktnummer. Versuche es bitte erneut.");
								System.out.println("------------------------------------------------\n");

							} catch (IllegalArgumentException e) {
								System.out.println("------------------------------------------------");
								System.out.println("Fehler: Unzureichender Bestand. Versuche es bitte erneut.");
								System.out.println("------------------------------------------------\n");

							}
						}
						putProductInCart = true;
						break;

					// Sortiment sortieren
					case "2":

						while (sortProducts == true) {
							System.out.println(
									"Wie soll sortiert werden?\n" + "1: Preis aufsteigend\n" + "2: Preis absteigend");
							System.out.println(line);
							aktion = sc.nextLine().toLowerCase();
							if (aktion.equals("1")) {
								sortProducts = false;
								myProdCatalog.sortAfterPriceAsc();
							} else if (aktion.equals("2")) {
								sortProducts = false;
								myProdCatalog.sortAfterPriceDesc();
							} else {
								System.out.println("Bitte gebe eine g�ltige Zahl ein.");
							}
						}

						break;

					// Zur�ck
					case "3":
						caseRunning = false;
						break;

					default:
						System.out.println("Bitte gebe eine g�ltige Zahl ein.");
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
					System.out.println("1: Warenkorb kaufen\n" + "2: Warenkorb bearbeiten\n" + "3: Zur�ck");
					aktion = sc.nextLine().toLowerCase();

					switch (aktion) {

					// Warenkorb kaufen
					case "1":
						if (myShopCart.hasItems() == false) {
							System.out.println("Keine Produkte im Warenkorb.");
						} else {
							Order myOrder = new Order(myShopCart);
							if (isBusiness.equals("1")) {
								myOrder.completeOrder(customer1);
								System.out.println("------------------------------------------------");
								System.out.println("Wir bedanken uns f�r deinen Einkauf - auf Wiedersehen!");
								System.out.println("------------------------------------------------");
								isRunning = false;
							} else {
								myOrder.completeOrderBusiness(businessCustomer1);
								System.out.println("------------------------------------------------");
								System.out.println("Wir bedanken uns f�r deinen Einkauf - auf Wiedersehen!");
								System.out.println("------------------------------------------------");
								isRunning = false;
							}
							shoppingCartRunning = false;

						}
						break;

					// Warenkorb bearbeiten
					case "2":
						if (myShopCart.hasItems() == false) {
							System.out.println("Keine Produkte im Warenkorb.");
						} else {
							while (changeProductInCart) {
								System.out.println(
										"Welches Produkt soll bearbeitet werden? Schreibe die Produktposition!");
								try {
									productNumberInCart = sc.nextInt();
									// Produkt mit der Nummer im Warenkorb finden
									changeProduct = myShopCart.get(productNumberInCart);
									System.out.println("------------------------------------------------");
									System.out.println("Du hast momentan: "
											+ myShopCart.get(productNumberInCart).getQuantity() + " St�ck vom Produkt: "
											+ myShopCart.get(productNumberInCart).getProductName()
											+ " in deinem Einkaufswagen");
									System.out.println("Wie viele m�chtest du jetzt?");
									System.out.println("------------------------------------------------");
									newQuantity = sc.nextInt();
									sc.nextLine();
									myShopCart.get(productNumberInCart).setQuantity(newQuantity);

									System.out.println("Erfolgreiche Bearbeitung!");

									changeProductInCart = false;
								} catch (IndexOutOfBoundsException | InputMismatchException e) {
									System.out.println("------------------------------------------------");
									System.out.println("Fehler: Ung�ltige Produktposition. Versuche es bitte erneut.");
									System.out.println("------------------------------------------------\n");
								}
							}
							// shoppingCartRunning = false;
						}

						break;

					// Zur�ck
					case "3":
						shoppingCartRunning = false;
						break;

					// Falsche Eingabe
					default:
						System.out.println("Bitte gebe eine g�ltige Zahl ein.");
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
				System.out.println("Bitte gebe eine g�ltige Zahl ein.");
				break;
			}

		}

		sc.close();

	}

	// Suche Produkt anhand Produktnummer. Ben�tigt, um Produkte anhand ihrer Nummer
	// in den Warenkorb zu legen
	public static int findPosInArray(Products[] myArray, long pNumber) {

		for (int i = 0; i < myArray.length; i++) {
			if (myArray[i].getProductNumber() == pNumber) {
				return i;
			}
		}
		return -1;

	}

	// Einlesen und Bef�llung von Produktkatalog
	public static Products[] fillProdListingArray() {

		Products[] prodArray = new Products[10];
		Products[] serArray = new Products[10];
		String input;
		Boolean inputLoop = true;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		File checkFile = new File("logDataFiles\\ProductCatalogue.ser");

		while (inputLoop) {
			if (checkFile.exists()) {

				System.out.println("Ein Warenkorb wurde als Datei gefunden, m�chtest du diesen nutzen? (1-2)\n"
						+ "1: Ja\n" + "2: Nein");
				input = sc.nextLine();
				if (input.equals("1")) {
					inputLoop = false;
					serArray = loadCatalogue();
					System.out.println("Produktkatalog-Datei wurde geladen.");
					return serArray;
				} else if (input.equals("2")) {
					inputLoop = false;
					System.out.println("Standard Produktkatalog wurde geladen.");
				} else {
					System.out.println("------------------------------------------------");
					System.out.println("Gebe bitte eine g�ltige Zahl an.");
					System.out.println("------------------------------------------------\n");
					inputLoop = true;
				}

			} else {
				System.out.println("\nKeine Produktkatalog-Datei gefunden.\nStandard Produktkatalog wurde geladen.");
				inputLoop = false;
			}
		}

		// Produkt-Objekte instanziieren und in einem Array speichern, f�r einfachen
		// sp�teren Zugriff
		prodArray[0] = new Products(104, "Kettens�ge", 7, 550.00);
		prodArray[1] = new Products(710, "Panzertape", 25, 5.00);
		prodArray[2] = new Products(471, "SkiMaske", 37, 25.00);
		prodArray[3] = new Products(293, "Baseballschl�ger", 8, 75.00);
		prodArray[4] = new Products(510, "Tisch", 5, 250.00);
		prodArray[5] = new Products(410, "Laptop", 11, 1050.00);
		prodArray[6] = new Products(790, "Tastatur", 5, 110.00);
		prodArray[7] = new Products(669, "Kopfh�rer", 5, 250.00);
		prodArray[8] = new Products(912, "Bildschirm", 3, 478.00);
		prodArray[9] = new Products(244, "Maus", 7, 45.00);

		return prodArray;

	}

	// Speichern des Produktkatalogs
	public static void saveCatalogue(Products[] prodArray) {

		try {
			FileOutputStream fos = new FileOutputStream("logDataFiles\\ProductCatalogue.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(prodArray);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Laden des Produktkatalogs
	public static Products[] loadCatalogue() {

		Products[] returnArray = new Products[10];

		try {
			FileInputStream fis = new FileInputStream("logDataFiles\\ProductCatalogue.ser");
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

	// Anmelde-/Registrierungsprozess f�r Benutzer
	public static Customer loginProcessCustomer() {

		String aktion;
		Customer loginCustomer = new Customer("Empty", "Empty");
		String passwort;
		String benutzername;
		String regBenutzername;
		String regPasswort;
		Boolean loginLoop = true;
		Boolean aktionLoop = true;

		// Auslesung von Inputs des Users
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while (aktionLoop) {
			System.out.println("Willkommen im Anmeldeprozess! Was m�chtest du tun?");
			System.out.println("1: Anmelden | 2: Registrieren");

			aktion = sc.nextLine();

			switch (aktion) {
			case "1":
				File checkFile = new File("logDataFiles\\LoginDetailsCustomer.ser");
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
				aktionLoop = false;
				System.out.println("Dein Benutzername (Registrierung): ");
				regBenutzername = sc.nextLine();
				System.out.println("Dein Passwort (Registrierung): ");
				regPasswort = sc.nextLine();
				loginCustomer = new Customer(regBenutzername, regPasswort);
				break;

			default:
				System.out.println("------------------------------------------------");
				System.out.println("Bitte gebe eine g�ltige Zahl ein.");
				System.out.println("------------------------------------------------");
				break;
			}
		}

		writeLogin(loginCustomer);

		return loginCustomer;

	}

	// Einlesen bereits verwendeter Anmeldedaten
	public static Customer readLoginCustomer() {

		Customer returnCustomer = new Customer("Empty", "Empty");

		try {
			FileInputStream fis = new FileInputStream("logDataFiles\\LoginDetailsCustomer.ser");
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

	// Speicherung der Anmeldedaten des Benutzers
	public static void writeLogin(Customer customer) {
		try {
			FileOutputStream fos = new FileOutputStream("logDataFiles\\LoginDetailsCustomer.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(customer);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Anmelde-/Registrierungsprozess f�r Firmennutzer
	public static BusinessCustomer loginProcessBusiness() {

		String aktion;
		BusinessCustomer loginCustomer = new BusinessCustomer("Empty", "Empty", 1);
		String benutzername;
		String passwort;
		String regBenutzername;
		String regPasswort;
		Boolean loginLoop = true;
		Boolean aktionLoop = true;
		Boolean regLoop = true;
		int businessSize;

		// Auslesung von Inputs des Users
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (aktionLoop) {
			System.out.println("Willkommen im Anmeldeprozess! Was m�chtest du tun?");
			System.out.println("1: Anmelden | 2: Registrieren");

			aktion = sc.nextLine();

			switch (aktion) {
			case "1":
				File checkFile = new File("logDataFiles\\LoginDetailsBusiness.ser");
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
							System.out.println("1: Erneut versuchen | 2: Zur�ck");

							aktion = sc.nextLine();

							if (aktion.equals("1")) {

							} else {
								aktionLoop = true;
							}

						}
					}
				} else {
					System.out.println("Serverprobleme. (Logindatei wurde nicht gefunden)");
					// System.exit(0);
				}
				break;

			// Registrierung BusinessCustomer
			case "2":
				aktionLoop = false;
				while (regLoop) {
					try {
						System.out.println("Dein Benutzername (Registrierung): ");
						regBenutzername = sc.nextLine();
						System.out.println("Dein Passwort (Registrierung): ");
						regPasswort = sc.nextLine();
						System.out.println("Deine Unternehmensgr��e 1-3:");
						businessSize = sc.nextInt();
						sc.nextLine();
						loginCustomer = new BusinessCustomer(regBenutzername, regPasswort, businessSize);
						regLoop = false;
					} catch (IllegalArgumentException e) {
						System.out.println(
								"BusinessSize / Unternehmensgr��e muss zwischen 1-3 liegen. Versuche es bitte erneut.");
					}
				}
				break;

			default:
				System.out.println("------------------------------------------------");
				System.out.println("Bitte gebe eine g�ltige Zahl ein.");
				System.out.println("------------------------------------------------");
				break;
			}
		}

		writeLoginBusiness(loginCustomer);

		return loginCustomer;

	}

	// Einlesen bereits verwendeter Anmeldedaten Firmennutzer
	public static BusinessCustomer readLoginBusiness() {

		BusinessCustomer returnCustomer = new BusinessCustomer("Empty", "Empty", 1);

		try {
			FileInputStream fis = new FileInputStream("logDataFiles\\LoginDetailsBusiness.ser");
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

	// Speicherung der Anmeldedaten des Firmennutzer
	public static void writeLoginBusiness(BusinessCustomer customer) {
		try {
			FileOutputStream fos = new FileOutputStream("logDataFiles\\LoginDetailsBusiness.ser");
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
