package application;

import java.io.*;

public class Misc {
	public static void main (String[] args) {
		String filepath = "/home/matt/java/workspace/BarcodeApp/src/application/test.txt";
		filepath = "./test.txt";
		BufferedReader br = null;
		String line = "";
		
		try {
			br = new BufferedReader(new FileReader(filepath));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
