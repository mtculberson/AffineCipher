import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class AffineCipher {
	
	public static final int L4PN = 7621;
	
	public static void main(String[] args) {
		AffineCipher cipher = new AffineCipher();
		ArrayList<String> keys = new ArrayList<String>();
		
		try {
			keys = cipher.ReadKey("keys.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(cipher.Encryption("Every Day An Apple Keeps A Doctor Away", keys.get(0), L4PN));
		String ciphertext = cipher.Encryption("Every Day An Apple Keeps A Doctor Away", keys.get(0), L4PN);
		String decipheredText = cipher.Decryption(ciphertext, keys.get(0), L4PN);
		
		System.out.println(decipheredText);
	
	}
	
	
	public String strToInt(String str){
		char[] c = str.toCharArray();
		String result = "";
		for (int i = 0; i <c.length; i++){
			int temp = c[i];
			result += temp+" ";
		}
		return result;
	}
	
	public String intToStr(String num){
		String[] str = num.split(" ");
		char[] result = new char[str.length];
		for(int i = 0; i < str.length; i++){
			result[i] = (char) Integer.parseInt(str[i]);
		}
		return String.valueOf(result);
	}

	public boolean GCD(int input1, int input2){
		while(input1 !=0 && input2 !=0){
			 if(input1 >= input2){
				 input1 = input1-input2;
			 }
			 else{
				 input2 = input2 - input1;
			 }
		}
		if(input1 == 1 && input2 == 0){
			 return true;
		}
		else if (input2 ==1 && input1 == 0){
			 return true;
		}
		else return false;
	}
	
	public int a_inverse(int a, int modulus){
		for(int i = 0; i < modulus; i++){
			if (i * a % modulus == 1)
				return i;
		}
		return 0;
	}
	
	public String KeyGenerator(int L4PN){
		int a = generateGCD(L4PN);
		int b = generateGCD(L4PN);
		int a_inverse = a_inverse(a, L4PN);
		String temp = "" + a + " " + b + " " + a_inverse;
		return temp;
	} 
	
	public ArrayList<String> ReadKey(String pathname) throws IOException{
		File filename = new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader br = new BufferedReader(reader);
		String line = "";
		ArrayList<String> temp = new ArrayList<String>();
		while(line != null){
			line = br.readLine();
			if(line != null){
				temp.add(line);
			}
		}
		return temp;
	}
	
	public String Encryption(String content, String key, int L4PN){
		String[] splitContent = key.split(" ");
		int a = Integer.parseInt(splitContent[0]);
		int b = Integer.parseInt(splitContent[1]);
		int inverse_a = Integer.parseInt(splitContent[2]);
		
		String ciphertext = strToInt(content);
		String[] ciphertextSplit = ciphertext.split(" ");
		String result = "";
		
		for (int i = 0; i < ciphertextSplit.length; i++){
			result += intToStr(a * Integer.parseInt(ciphertextSplit[i]) + b % L4PN + "");
		}
		
		return result;
	}
	
	public String Decryption(String ciphertext, String key, int L4PN){
		String[] splitContent = key.split(" ");
		int a = Integer.parseInt(splitContent[0]);
		int b = Integer.parseInt(splitContent[1]);
		int inverse_a = Integer.parseInt(splitContent[2]);
		
		String result = "";
		String[] ciphertextAsNum = strToInt(ciphertext).split(" ");
		
		for (int i = 0; i < ciphertextAsNum.length; i++){
			result += intToStr(inverse_a * (Integer.parseInt(ciphertextAsNum[i]) - b) % L4PN + "");
		}
		
		return result;
	}
	
	public int generateGCD(int L4PN){
		Random random = new Random();
		boolean isCoPrime = false;
		int rand;
		while (!isCoPrime){
			rand = random.nextInt(L4PN);
			if (GCD(rand, L4PN))
				return rand;
		}
		return -1;
	}

}
