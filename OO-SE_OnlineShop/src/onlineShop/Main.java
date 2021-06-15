package onlineShop;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShoppingCart myShopCart = new ShoppingCart();
		Boolean isRunning = true;
		String inputName;
		String inputPassword;

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

		System.out.println("Password:");
		inputPassword = sc.nextLine();
		inputPassword = inputPassword.toLowerCase();

		if (inputName.equals(privKundeName.toLowerCase()) && inputPassword.equals(privKundePassword.toLowerCase())) {

			Customer customer1 = new Customer("Herbert", "Winkler", "Storkower Stra�e 44");

		} else if (inputName.equals(uKundeName.toLowerCase()) && inputPassword.equals(uKundePassword.toLowerCase())) {

			BusinessCustomer businessCustomer1 = new BusinessCustomer("Michael", "Wendler", "Prenzlauer Allee 17", 3);

		} else {
			System.out.println("Benutzername oder Password falsch, versuche es bitte erneut!");
			isRunning = false;
		}

		Products Kettens�ge = new Products(104, "Kettens�ge", 2, 550.00, 0);
		Products Panzertape = new Products(710, "Panzertape", 3, 5.00, 0);
		Products SkiMaske = new Products(471, "SkiMaske", 1, 25.00, 0);
		Products Baseballschl�ger = new Products(293, "Baseballschl�ger", 2, 75.00, 0);

		myShopCart.addProduct(Kettens�ge);
		myShopCart.addProduct(Panzertape);
		myShopCart.addProduct(SkiMaske);
		myShopCart.addProduct(Baseballschl�ger);
		myShopCart.addProduct(new Products(684, "Coole Wippe", 2, 300.00, 0));

		myShopCart.printProducts();
		System.out.println(myShopCart.getTotalCost());
		
		System.out.println("Poggers");

		// while (isRunning) {

		// }

	}

}
