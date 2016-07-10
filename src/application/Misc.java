package application;

public class Misc {
	public static void main (String[] args) {
		/*
		short number = 0b11011001100;
		System.out.println(Integer.toBinaryString(number));
		*/
		
		Code128BEncoder ce = new Code128BEncoder();
		System.out.println(ce.convertTo128B("hello"));
	}
}
