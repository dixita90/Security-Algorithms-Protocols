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
			roundKeys.aesRoundKeys(inputKey);

			// LAB 3 testing
			String[][] sHex = { { "54", "4F", "4E", "20" },
					{ "77", "6E", "69", "54" }, { "6F", "65", "6E", "77" },
					{ "20", "20", "65", "6F" } };
			String[][] keyHex = { { "54", "73", "20", "67" },
					{ "68", "20", "4B", "20" }, { "61", "6D", "75", "46" },
					{ "74", "79", "6E", "75" } };
			AEScipher a = new AEScipher();
			a.aesStateXOR(sHex, keyHex);

			a.aesMixColumn(a.aesShiftRow(a.aesNibbleSub(a.aesStateXOR(sHex, keyHex))));

		} catch (Exception ex) {
			System.out.println("Exception in driver.java: " + ex.getMessage());
		}
	}

}
