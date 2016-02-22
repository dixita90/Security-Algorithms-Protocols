import java.io.BufferedReader;
import java.io.InputStreamReader;

public class driver {
	// This variable will hold the 128 bit hex key
	private static String inputKey="";

	public static void main(String[] args) {

		try{
			//Take input from console
			BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the 128 bit hex key:");

			//Check for empty, null values and check the length
			boolean blnValidate=true;
			do
			{
				inputKey = br.readLine();
				if(inputKey !="" && inputKey!=null && inputKey.length()==32)
				{   /*Create an instance of aescipher class & call
				the function to generate round keys*/
					aescipher roundKeys=new aescipher();
					roundKeys.aesRoundKeys(inputKey);
				}
				else
				{
					System.out.println("Enter a valid 128 bit hex key");
					blnValidate=false;
				}
			}while(!blnValidate);
		}
		catch(Exception ex){
			System.out.println("Exception in driver.java: "+ex.getMessage());
		}
	}

}
