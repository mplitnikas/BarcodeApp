package application;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Code128BEncoder {
	
	private HashMap<Byte, String> barPatterns;
	private String quietZone = "0000000000";
	private String start128BCode = "11010010000";
	private String stopCode = "1100011101011"; // including termination bar "...11"
	
	public Code128BEncoder() {
		barPatterns = new HashMap<>(95);
		readEncodingData("./ascii_to_code.csv");
	}
	
	public static void main (String[] args) {
		Code128BEncoder ce = new Code128BEncoder();
		ce.go();
	}
	
	public void go() {
		String start = "PJJ123C"; //TODO this is just a test string, get user input
		String output = convertTo128B(start); 
		System.out.println(output);
	} 

	public String convertTo128B (String input) {
		/*
		 * This is the meat of this program. It takes
		 * the starting string all the way to a binary
		 * string representing the barcode's bars.
		 */
		
		ArrayList<Byte> asciiVals = encodeToASCII(input);
		byte checkValue = calculateCheckSum(asciiVals);
		asciiVals.add(checkValue);
		
		StringBuilder bitPatterns = new StringBuilder();
		bitPatterns.append(quietZone);
		bitPatterns.append(start128BCode);
		
		for (byte b : asciiVals) {
			String p = asciiValueToBitPattern(b);
			bitPatterns.append(p);
		}
		
		bitPatterns.append(stopCode);
		bitPatterns.append(quietZone);
		return bitPatterns.toString();
	}
	
	private String asciiValueToBitPattern (byte input) {
		return barPatterns.get(input);
	}
	
	private void readEncodingData (String filePath) {
		/*
		 * Ingest data for converting ASCII -> 128B bit patterns
		 */
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				String[] pair = line.split(",");
				barPatterns.put(Byte.parseByte(pair[0]), pair[1]);
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
