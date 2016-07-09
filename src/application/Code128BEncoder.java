package application;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Code128BEncoder {
	
	private HashMap<Integer, String> barPatterns;
	
	public Code128BEncoder() {
		barPatterns = new HashMap<>(95);
		addCSVCodes("./128B.txt");
	}
	
	public static void main (String[] args) {
		Code128BEncoder ce = new Code128BEncoder();
		ce.go();
	}
	
	public void go() {
		String start = "PJJ123C";
		ArrayList<Byte> codes = encodeToASCII(start);
		byte checkValue = calculateCheckSum(codes);
		codes.add(checkValue);
		// TODO convert to bit patterns
		// TODO add guard patterns, quiet zones
	} 

	private void addCSVCodes (String filePath) {
		/*
		 * Adds the 128B ascii -> barcode
		 * info to barPatterns
		 */
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				String[] pair = line.split(",");
				barPatterns.put(Integer.parseInt(pair[0]), pair[1]);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	
	private ArrayList<Byte> encodeToASCII (String input) {
		ArrayList<Byte> codes = new ArrayList<>();
		byte[] byteArr = input.getBytes(StandardCharsets.US_ASCII);
		for (byte b : byteArr) {
			codes.add(b);
		}
		return codes;
	}
	
	private byte calculateCheckSum (ArrayList<Byte> input) {
		int sum = 0;
		for (int i = 0; i < input.size(); i++) {
			int add = ((input.get(i) - 32) * (i + 1));
			sum += add;
		}
		return (byte)((sum % 103) + 32);
	}
}
