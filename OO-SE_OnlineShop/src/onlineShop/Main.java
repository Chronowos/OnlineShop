package onlineShop;

import java.io.IOException;
import com.itextpdf.text.DocumentException;

public class Main {

	public static void main(String[] args) throws IOException, DocumentException {
		OnlineShop ourShop = new OnlineShop();
		ourShop.openShop();
	}

}
