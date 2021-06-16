package onlineShop;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShoppingCart myShopCart = new ShoppingCart();
		Boolean isRunning = true;
		String inputName;
		String inputPassword;
		String aktion;

		Scanner sc = new Scanner(System.in);

		String privKundeName = "DanielMeier123";
		String privKundePassword = "Obunga123";

		String uKundeName = "GeldIstCool";
		String uKundePassword = "IchBinReich123";

		System.out.println("------------------------------------------------");
		System.out.println("Willkommen in unserem kleinen, aber feinen Shop. \n"
				+ "Schau dich gerne etwas um und durchst�bere unser Produktsortiment - vielleicht findest du ja etwas tolles!");
		System.out.println("------------------------------------------------ \n");
		System.out.println("Logge dich bitte bei uns eins!");

		System.out.println("Benutzername:");
		inputName = sc.nextLine();
		inputName = inputName.toLowerCase();

		System.out.println("\nPassword:");
		inputPassword = sc.nextLine();
		inputPassword = inputPassword.toLowerCase();

		if (inputName.equals(privKundeName.toLowerCase()) && inputPassword.equals(privKundePassword.toLowerCase())) {

			Customer customer1 = new Customer("Herbert", "Winkler", "Kuckhoffstra�e 2");
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
			System.out.println("\nWas m�chtest du tun?");
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
				System.out.println("Danke f�r's benutzen!");
			default:
				System.out.println("Bitte gebe eine g�ltige Zahl ein! (1 - 4)");
			}

		}

		Products Kettens�ge = new Products(104, "Kettens�ge", 2, 550.00, 0);
		Products Panzertape = new Products(710, "Panzertape", 3, 5.00, 0);
		Products SkiMaske = new Products(471, "SkiMaske", 1, 25.00, 0);
		Products Baseballschl�ger = new Products(293, "Baseballschl�ger", 2, 75.00, 0);

		myShopCart.addProductToCart(Kettens�ge);
		myShopCart.addProductToCart(Panzertape);
		myShopCart.addProductToCart(SkiMaske);
		myShopCart.addProductToCart(Baseballschl�ger);
		myShopCart.addProductToCart(new Products(684, "Coole Wippe", 2, 300.00, 0));

		myShopCart.printProducts();
		System.out.println(myShopCart.getTotalCost());
		sc.close();

	}

}
