package application;

public class Barcode {
	private String rawInput;
	private String code;
	
	public Barcode() {
		this("test");
	}
	
	public Barcode(String input) {
		setRawInput(input);
		setCode(convertToBarcode(input));
	}
	
	private String encode(String input) {
		return "encoded string";
		// TODO implement encoding for string (into bits?)
	}
	
	private String addCheckSum(String input) {
		return input;
		// TODO implement checksum function on input
	}
	
	/*
	 * Converts given string to a barcode sequence
	 * (currently using UPC-A only)
	 */
	private String convertToBarcode(String input) {
		return addGuardPatterns(
				addCheckSum(
				encode(input)));
	}
	/*
	 * Adds start, middle, and end guards.
	 */
	private String addGuardPatterns(String input) {
		StringBuilder sb = new StringBuilder();
		sb.append("start seq ");
		sb.append(input);
		sb.append(" end seq");
		return sb.toString();
	}
	
	public String getRawInput() {
		return rawInput;
	}
	public void setRawInput(String rawInput) {
		this.rawInput = rawInput;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
