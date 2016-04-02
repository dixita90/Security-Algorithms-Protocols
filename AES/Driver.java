import java.util.Scanner;

public class Driver {
	// This variable will hold the 128 bit hex key
	private static String inputKey = "";
	private static String plainText = "";

	public static void main(String[] args) {

		try {
			// Take input from console
			Scanner input = new Scanner(System.in);
			inputKey = input.nextLine();

			if (input.hasNext())
				plainText = input.nextLine();

			AEScipher roundKeys = new AEScipher();
			roundKeys.aes(inputKey, plainText);

		} catch (Exception ex) {
			System.out.println("Exception in driver.java: " + ex.getMessage());
		}
	}

}
