package onlineShop;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import comparators.ProductNumberComparator;
import comparators.ProductPriceComparatorAscending;
import comparators.ProductPriceComparatorDescending;

public class ShoppingCart {

	private List<Products> myShoppingCart = new ArrayList<Products>();
	private Double totalPrice = 0.0;
	private Products returnProduct;

	// Schriftarten für die PDF-Ausgabe
	private static Font timesFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
	private static Font timesFontSmall = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL);
	private static Font timesFontBold = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);

	// Konstruktor
	public ShoppingCart() {

	}

	// Produkt hinzufügen
	public void addProductToCart(Products product, int amount) {

		int amountBefore = product.getQuantity();

		// Wenn mehr Produkte hinzugefügten werden sollen, als wir im Bestand haben
		if (product.getQuantity() < amount) {
			throw new IllegalArgumentException();
		} else {
			product.setQuantity(product.getQuantity() - amount);
			// Füge das Produkt hinzu
			myShoppingCart.add(new Products(product.getProductNumber(), product.getProductName(),
					amountBefore - product.getQuantity(), product.getBasePrice()));
		}
	}

	// Auflistung printen
	public void printProducts() {
		// Formatierung für die Ausgabe
		String outputText = String.format("%-10s %-10s %-20s %-10s %-15s", "Position", "Nummer", "Name", "Anzahl",
				"Preis");
		System.out.println(outputText);
		System.out.println("-------------------------------------------------------------");
		for (Products myProducts : myShoppingCart) {
			System.out.println(myShoppingCart.indexOf(myProducts) + "           " + myProducts.toString());
			totalPrice = totalPrice + (myProducts.getQuantity() * myProducts.getBasePrice());
		}
		System.out.println("-------------");
		System.out.println("Summe: " + totalPrice);
		totalPrice = 0.0;
	}

	// Gesamtkosten errechnen
	public double getTotalCost() {
		for (Products myProducts : myShoppingCart) {
			totalPrice = totalPrice + (myProducts.getQuantity() * myProducts.getBasePrice());
		}
		return totalPrice;
	}

	// Enthält der Warenkorb Produkte?
	public boolean hasItems() {
		if (myShoppingCart.size() == 0) {
			return false;
		}
		return true;
	}

	// Sortierung nach Produktnummer
	public void sortAfterNumber() {
		ProductNumberComparator numComparator = new ProductNumberComparator();
		Collections.sort(myShoppingCart, numComparator);
	}

	// Sortierung nach Preis: absteigend
	public void sortAfterPriceDesc() {
		ProductPriceComparatorDescending descComparator = new ProductPriceComparatorDescending();
		Collections.sort(myShoppingCart, descComparator);
	}

	// Sortierung nach Preis: aufsteigend
	public void sortAfterPriceAsc() {
		ProductPriceComparatorAscending ascComparator = new ProductPriceComparatorAscending();
		Collections.sort(myShoppingCart, ascComparator);
	}

	// Gesamtanzahl an Produkten
	public int getTotalItems() {
		return myShoppingCart.size();
	}

	// Get-Index
	public Products get(int pos) {
		returnProduct = myShoppingCart.get(pos);
		return returnProduct;
	}

	// Quittung-Bank
	public void createPDFBank(String adresse, String bank, long BankCode, long accountNumber) {

		try {

			float docWidth;
			String pdf_path = System.getProperty("user.home") + "\\Desktop\\Quittung.pdf";
			Image my_img = Image.getInstance("res\\shopbild.png");
			Document myDoc = new Document();

			docWidth = myDoc.getPageSize().getWidth() - myDoc.leftMargin() - myDoc.rightMargin();

			my_img.scaleAbsolute(docWidth, 150);

			PdfWriter.getInstance(myDoc, new FileOutputStream(pdf_path));

			myDoc.open();

			myDoc.add(my_img);
			myDoc.add(new Paragraph("Vielen Dank für deinen Einkauf in unserem Shop!\n", timesFont));
			myDoc.add(new Paragraph("Hier ist eine Zusammenfassung deines Einkaufs: \n\n", timesFontSmall));

			float[] columnWidths = { 6, 6, 3, 5 };

			PdfPTable myTable = new PdfPTable(columnWidths);
			PdfPCell c1 = new PdfPCell(new Paragraph("Produktnummer", timesFontBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			myTable.addCell(c1);

			c1 = new PdfPCell(new Paragraph("Produktname", timesFontBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			myTable.addCell(c1);

			c1 = new PdfPCell(new Paragraph("Anzahl", timesFontBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			myTable.addCell(c1);

			c1 = new PdfPCell(new Paragraph("Preis pro Stück", timesFontBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			myTable.addCell(c1);

			for (Products myProducts : myShoppingCart) {

				PdfPCell e1 = new PdfPCell(
						new Paragraph(String.format("%d", myProducts.getProductNumber()), timesFontSmall));
				e1.setHorizontalAlignment(Element.ALIGN_CENTER);
				e1.setVerticalAlignment(Element.ALIGN_CENTER);
				myTable.addCell(e1);

				e1 = new PdfPCell(new Paragraph(myProducts.getProductName(), timesFontSmall));
				e1.setHorizontalAlignment(Element.ALIGN_CENTER);
				e1.setVerticalAlignment(Element.ALIGN_CENTER);
				myTable.addCell(e1);

				e1 = new PdfPCell(new Paragraph(String.format("%d", myProducts.getQuantity()), timesFontSmall));
				e1.setHorizontalAlignment(Element.ALIGN_CENTER);
				e1.setVerticalAlignment(Element.ALIGN_CENTER);
				myTable.addCell(e1);

				e1 = new PdfPCell(new Paragraph(myProducts.getBasePrice() + " €", timesFontSmall));
				e1.setHorizontalAlignment(Element.ALIGN_CENTER);
				e1.setVerticalAlignment(Element.ALIGN_CENTER);
				myTable.addCell(e1);
			}

			myDoc.add(myTable);
			myDoc.add(new Paragraph("\nGesamtkosten: " + getTotalCost() + "\n", timesFont));

			myDoc.add(new Paragraph("\nDie Lieferung und Abbuchung erfolgt auf folgende Daten:\n", timesFont));
			myDoc.add(new Paragraph("\nAdresse: " + adresse, timesFontSmall));
			myDoc.add(new Paragraph("Bank: " + bank, timesFontSmall));
			myDoc.add(new Paragraph("Banknummer: " + BankCode, timesFontSmall));
			myDoc.add(new Paragraph("Kontonummer: " + accountNumber, timesFontSmall));

			myDoc.close();

		} catch (DocumentException | IOException e) {
			System.out.println("Fehler PDF-Erstellung");
		}
	}

	// Quittung-Karte
	public void createPDFCard(String adresse, long cardNumber, int cvv) {
		try {

			float docWidth;
			String pdf_path = System.getProperty("user.home") + "\\Desktop\\Quittung.pdf";
			Image my_img = Image.getInstance("res\\shopbild.png");
			Document myDoc = new Document();

			docWidth = myDoc.getPageSize().getWidth() - myDoc.leftMargin() - myDoc.rightMargin();

			my_img.scaleAbsolute(docWidth, 150);

			PdfWriter.getInstance(myDoc, new FileOutputStream(pdf_path));

			myDoc.open();

			myDoc.add(my_img);
			myDoc.add(new Paragraph("Vielen Dank für deinen Einkauf in unserem Shop!\n", timesFont));
			myDoc.add(new Paragraph("Hier ist eine Zusammenfassung deines Einkaufs: \n\n", timesFontSmall));

			float[] columnWidths = { 6, 6, 3, 5 };

			PdfPTable myTable = new PdfPTable(columnWidths);
			PdfPCell c1 = new PdfPCell(new Paragraph("Produktnummer", timesFontBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			myTable.addCell(c1);

			c1 = new PdfPCell(new Paragraph("Produktname", timesFontBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			myTable.addCell(c1);

			c1 = new PdfPCell(new Paragraph("Anzahl", timesFontBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			myTable.addCell(c1);

			c1 = new PdfPCell(new Paragraph("Preis pro Stück", timesFontBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_CENTER);
			myTable.addCell(c1);

			for (Products myProducts : myShoppingCart) {
				PdfPCell e1 = new PdfPCell(
						new Paragraph(String.format("%d", myProducts.getProductNumber()), timesFontSmall));
				e1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_CENTER);
				myTable.addCell(e1);

				e1 = new PdfPCell(new Paragraph(myProducts.getProductName(), timesFontSmall));
				e1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_CENTER);
				myTable.addCell(e1);

				e1 = new PdfPCell(new Paragraph(String.format("%d", myProducts.getQuantity()), timesFontSmall));
				e1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_CENTER);
				myTable.addCell(e1);

				e1 = new PdfPCell(new Paragraph(myProducts.getBasePrice() + " €", timesFontSmall));
				e1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_CENTER);
				myTable.addCell(e1);
			}
			myDoc.add(myTable);

			myDoc.add(new Paragraph("\nDie Lieferung und Abbuchung erfolgt auf folgende Daten:\n", timesFont));
			myDoc.add(new Paragraph("\nAdresse: " + adresse, timesFontSmall));
			myDoc.add(new Paragraph("Kartennummer: " + cardNumber, timesFontSmall));
			myDoc.add(new Paragraph("CVV-Code: " + cvv, timesFontSmall));

			myDoc.close();

		} catch (DocumentException | IOException e) {
			System.out.println("Fehler PDF-Erstellung");
		}

	}

}