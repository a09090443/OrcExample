package net.sourceforge.tess4j;

import java.io.File;

public class TesseractExample {
	public static void main(String[] args) {
		File imageFile = new File("/home/zipe/tmp/test.png");
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
//		instance.setDatapath("/home/zipe/tesseract-ocr");
		instance.setLanguage("eng");
		// Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping

		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}
}
