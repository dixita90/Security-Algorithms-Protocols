public class AEScipher {

	private static final String[][] S_BOX = {
			{ "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67",
					"2B", "FE", "D7", "AB", "76" },
			{ "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2",
					"AF", "9C", "A4", "72", "C0" },
			{ "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5",
					"F1", "71", "D8", "31", "15" },
			{ "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80",
					"E2", "EB", "27", "B2", "75" },
			{ "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6",
					"B3", "29", "E3", "2F", "84" },
			{ "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE",
					"39", "4A", "4C", "58", "CF" },
			{ "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02",
					"7F", "50", "3C", "9F", "A8" },
			{ "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA",
					"21", "10", "FF", "F3", "D2" },
			{ "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E",
					"3D", "64", "5D", "19", "73" },
			{ "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8",
					"14", "DE", "5E", "0B", "DB" },
			{ "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC",
					"62", "91", "95", "E4", "79" },
			{ "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4",
					"EA", "65", "7A", "AE", "08" },
			{ "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74",
					"1F", "4B", "BD", "8B", "8A" },
			{ "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57",
					"B9", "86", "C1", "1D", "9E" },
			{ "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87",
					"E9", "CE", "55", "28", "DF" },
			{ "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D",
					"0F", "B0", "54", "BB", "16" } };

	private static final String[][] R_CON = {
			{ "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36",
					"6C", "D8", "AB", "4D", "9A" },
			{ "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D",
					"FA", "EF", "C5", "91", "39" },
			{ "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33",
					"66", "CC", "83", "1D", "3A" },
			{ "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40",
					"80", "1B", "36", "6C", "D8" },
			{ "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A",
					"D4", "B3", "7D", "FA", "EF" },
			{ "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25",
					"4A", "94", "33", "66", "CC" },
			{ "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08",
					"10", "20", "40", "80", "1B" },
			{ "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6",
					"97", "35", "6A", "D4", "B3" },
			{ "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61",
					"C2", "9F", "25", "4A", "94" },
			{ "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01",
					"02", "04", "08", "10", "20" },
			{ "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E",
					"BC", "63", "C6", "97", "35" },
			{ "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4",
					"D3", "BD", "61", "C2", "9F" },
			{ "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8",
					"CB", "8D", "01", "02", "04" },
			{ "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D",
					"9A", "2F", "5E", "BC", "63" },
			{ "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91",
					"39", "72", "E4", "D3", "BD" },
			{ "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D",
					"3A", "74", "E8", "CB", "8D" } };

	private static final int[][] GALOIS_CONST = { { 2, 3, 1, 1 },
			{ 1, 2, 3, 1 }, { 1, 1, 2, 3 }, { 3, 1, 1, 2 } };

	protected String keyArray[][] = new String[4][4];
	protected String plainTextArray[][] = new String[4][4];
	protected String WArray[][] = new String[4][44];

	/*
	 * Generate the 4x4 matrix from the input key inHex is the 128 bit hex key
	 */
	protected void getKeyMatrix(String inHex) {
		try {
			char[] keyChars = inHex.toCharArray();
			int counter = 0;

			for (int col = 0; col < 4; col++) {
				for (int row = 0; row < 4; row++) {
					if (keyChars.length == counter)
						break;
					else {
						keyArray[row][col] = Character
								.toString(keyChars[counter])
								+ Character.toString(keyChars[counter + 1]);
						counter = counter + 2;
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Exception in aescipher class getKeyArray(): "
					+ ex.getMessage());
		}
	}
		
		protected void stringToMatrix(String plainText) {
			try {
				char[] keyChars = plainText.toCharArray();
				int counter = 0;

				for (int col = 0; col < 4; col++) {
					for (int row = 0; row < 4; row++) {
						if (keyChars.length == counter)
							break;
						else {
							plainTextArray[row][col] = Character
									.toString(keyChars[counter])
									+ Character.toString(keyChars[counter + 1]);
							counter = counter + 2;
						}
					}
				}
			} catch (Exception ex) {
				System.out.println("Exception in aescipher class stringToMatrix(): "
						+ ex.getMessage());
			}
	}

	// Copy the first four columns from keyArray
	protected void getWMatrix() {
		try {
			for (int col = 0; col < 4; col++) {
				for (int row = 0; row < 4; row++) {
					WArray[row][col] = keyArray[row][col];
				}
			}

			// Initialize remaining elements to 0
			for (int col = 4; col < 44; col++) {
				for (int row = 0; row < 4; row++)
					WArray[row][col] = "0";
			}

		} catch (Exception ex) {
			System.out.println("Exception in aescipher class getWMatrix() "
					+ ex.getMessage());
		}
	}

	/*
	 * this function is called from the driver class It calculates the round
	 * keys keyHex is the 128 bit hex key input
	 */
	protected void aesRoundKeys(String keyHex) {
		try {
			// Step 1:Generate the 4x4 matrix
			getKeyMatrix(keyHex);
			// Step 2:Generate the 4x44 matrix
			getWMatrix();
			// Step 3:Calculate the elements in the WMatrix
			for (int col = 4; col < 44; col++) {
				// if col is not a multiple of 4
				if (col % 4 != 0) {
					for (int row = 0; row < 4; row++) {
						WArray[row][col] = xor(WArray[row][col - 4],
								WArray[row][col - 1]);
					}
				} else {
					String[][] Wnew = new String[1][4];
					// left shift and value from Sbox
					Wnew[0][0] = aesSBox(WArray[1][col - 1]);
					Wnew[0][1] = aesSBox(WArray[2][col - 1]);
					Wnew[0][2] = aesSBox(WArray[3][col - 1]);
					Wnew[0][3] = aesSBox(WArray[0][col - 1]);

					// Rcon(col/4) value
					String rconVal = aesRound(col / 4);
					// xor with rcon value
					Wnew[0][0] = xor(rconVal, Wnew[0][0]);

					for (int i = 0; i < 4; i++)
						WArray[i][col] = xor(WArray[i][col - 4], Wnew[0][i]);
				}
			}
			// Print the round keys
			printRoundKeys();
		} catch (Exception ex) {
			System.out.println("Exception in class aescipher aesRoundKeys() "
					+ ex.getMessage());
		}
	}

	/*
	 * Join four columns to display the 128 bit round keys
	 */
	private void printRoundKeys() {
		try {
			String roundKey = "";
			for (int col = 0; col < 44; col++) {
				for (int row = 0; row < 4; row++) {
					roundKey += WArray[row][col];
					if ((col + 1) % 4 == 0 && row == 3) {
						System.out.println(roundKey.toUpperCase());
						roundKey = "";
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Exception in aescipher class printRoundKeys() "
					+ ex.getMessage());
		}
	}

	/*
	 * Calculates the xor of the hex values hexVal1 is the first hex value
	 * hexVal2 is the second hex value to be xored
	 */
	private String xor(String hexVal1, String hexVal2) {
		try {
			int val1 = Integer.parseInt(hexVal1, 16);
			int val2 = Integer.parseInt(hexVal2, 16);
			int xorResult = val1 ^ val2;
			String result = Integer.toHexString(xorResult);
			return result.length() == 1 ? ("0" + result) : result;
		} catch (Exception ex) {
			System.out.println("Exception in aescipher class xor method:"
					+ ex.getMessage());
			return "";
		}
	}

	/*
	 * Get the value from SBox based on inHex by dividing the inHex value to row
	 * and column to get the corresponding SBox value
	 */
	protected String aesSBox(String inHex) {
		try {
			String output = S_BOX[Integer.parseInt(inHex.split("")[0], 16)][Integer
					.parseInt(inHex.split("")[1], 16)];
			return output;
		} catch (Exception ex) {
			System.out.println("Error in aescipher class aesSBox() "
					+ ex.getMessage());
			return "";
		}
	}

	/*
	 * Get the Rcon value by using round as the index
	 */
	protected String aesRound(int round) {
		try {
			String rconVal = R_CON[0][round];
			return rconVal;
		} catch (Exception ex) {
			System.out.println("Exception in aescipher class aesRound() "
					+ ex.getMessage());
			return "";
		}
	}

	protected String[][] aesStateXOR(String[][] sHex, String[][] keyHex) {
		try {
			String[][] outHex = new String[4][4];
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					int val1 = Integer.parseInt(sHex[row][col], 16);
					int val2 = Integer.parseInt(keyHex[row][col], 16);
					int xorResult = val1 ^ val2;
					String result = Integer.toHexString(xorResult);
					outHex[row][col] = result.length() == 1 ? ("0" + result)
							: result;
					// System.out.println(outHex[row][col]);
				}
			}
			return outHex;
		} catch (Exception ex) {
			System.out.println("Exception in aesStateXOR method: "
					+ ex.getMessage());
			return null;
		}
	}

	protected String[][] aesNibbleSub(String[][] inStateHex) {
		String[][] outStateHex = new String[4][4];
		try {
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					outStateHex[row][col] = aesSBox(inStateHex[row][col]);
					// System.out.println(outStateHex[row][col]);
				}
			}
			return outStateHex;
		} catch (Exception ex) {
			System.out.println("Exception in aesNibbleSub method: "
					+ ex.getMessage());
			return null;
		}
	}

	protected String[][] aesShiftRow(String[][] inStateHex) {
		String[][] outStateHex = new String[4][4];
		try {
			int shift;
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					shift = row;
					outStateHex[row][col] = inStateHex[row][(col + shift) % 4];
					//System.out.println(outStateHex[row][col]);
				}
			}
			return outStateHex;
		} catch (Exception ex) {
			System.out.println("Error in aesShiftRow(): " + ex.getMessage());
			return null;
		}
	}

	protected String[][] aesMixColumn(String[][] inStateHex) {
		String[][] outStateHex = new String[4][4];
		try {
			int value;
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					value = 0;
					for (int counter = 0; counter < 4; counter++) {
						if (GALOIS_CONST[row][counter] == 1)
							value = value
									^ Integer
											.parseInt(inStateHex[counter][col],16);
						else if (GALOIS_CONST[row][counter] == 2) 
							value=Integer.parseInt(xor(Integer.toString(value),multiplicationByTwo(inStateHex[counter][col])),16);
						else 
							value=value^multiplicationbyThree(inStateHex[counter][col]);
					}
					outStateHex[row][col]=Integer.toHexString(value);
					System.out.println(outStateHex[row][col]);
				}
			}

			return outStateHex;
		} catch (Exception ex) {
			System.out.println("Exception in aesMixColumn(): "
					+ ex.getMessage());
			return null;
		}
	}
	
	private  String multiplicationByTwo(String input) {
		try {
			// convert hex and pad with zeros.
			int hexVal=Integer.parseInt(input,16);
			System.out.println(hexVal);
			String binary=Integer.toBinaryString(hexVal);
			System.out.println(binary);

			//no. of zeroes to pad 
		    int zeroReq= 8-binary.length();
		    
		    for(int i=0;i<zeroReq;i++){
		          binary="0"+binary;
		    }
			
			System.out.println(binary);
			// left shift by 1 and get msb
			String msb = binary.substring(0, 1);
			//System.out.println(Integer.toHexString(hexVal << 1));
			int leftShift =hexVal << 1;
			binary = Integer.toString(leftShift,2);//.substring(1);
			System.out.println(binary);

			if(msb.equals("1")){
				
				//binary = Integer.toString(leftShift,2).substring(1);
				String constant = Integer.toString(25,2);
				//System.out.println("binary after shift " +binary);
				String hello =xor(binary,constant);
				//System.out.println("xoroutput: " +hello);
				return hello;
			} else {
				
				//binary = Integer.toString(leftShift,2).substring(1);
				//System.out.println("witout xoroutput: "+binary);
				return binary;
			}
			
			
			/* if (msb.equals("1")) {
				System.out.println(Integer.parseInt(leftShift,16) ^ 0x1b);
				return xor(leftShift,"0x1b");
			} else	
				return leftShift; */

		} catch (Exception ex) {
			System.out.println("Error in multiplicationByTwo(): "
					+ ex.getMessage());
			return "";
		}
	}

private int multiplicationbyThree(String input)
{
	try
	{
		int multByTwo=Integer.parseInt(multiplicationByTwo(input),16);
		return multByTwo^Integer.parseInt(input,16);
	}
	catch(Exception ex)
	{
		System.out.println("Exception in multiplicationbyThree(): "+ex.getMessage());
		return 0;
	}
	}

protected void aes(String key, String plainText)
{
	try
	{
		//Generate the round Keys
		aesRoundKeys(key);
		
		//convert plaintext to matrix
		stringToMatrix(plainText);
	    
		//Initial Round
		String[][] tempArray=aesStateXOR(plainTextArray, keyArray);
		
		//Rounds
		tempArray=aesNibbleSub(tempArray);
		tempArray=aesShiftRow(tempArray);
		tempArray=aesMixColumn(tempArray);
		//aesStateXOR(tempArray,)
	   
	}
	catch(Exception ex)
	{
		System.out.println("Exception in aes(): "+ex.getMessage());
	}
}
}
