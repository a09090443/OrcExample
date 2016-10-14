import java.io.File;
import java.io.IOException;

public class TestOCR {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "/home/zipe/tmp/test.png";   
        try {   
            String valCode = new OCR().recognizeText(new File(path), "png");   
            System.out.println(valCode);   
        } catch (IOException e) {   
            e.printStackTrace();   
        } catch (Exception e) {
			e.printStackTrace();
		}    
	}

}