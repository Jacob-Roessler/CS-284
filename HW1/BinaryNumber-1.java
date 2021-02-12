// Name : Jacob Roessler
// Pledge : I pledge my honor that I have abided by the Stevens Honor System.


public class BinaryNumber 
{
	private int data[];
	private int length;
	
	/**
	 * constructs a BinaryNumber object using an integer length comprised of all 0s
	 * errors if length is < 0
	 * @param length of the binary number
	 */
	public BinaryNumber(int length) {
		if(length < 0)
			throw new IllegalArgumentException();
		this.length = length;
		this.data = new int[length];
	}
	
	/**
	 * constructs a BinaryNumber using a string representation of a binary number
	 * @param str binary number containing only alphanumeric characters: 0-1
	 */
	public BinaryNumber(String str) {
		if(!str.matches("[0-1]+")) {
			throw new IllegalArgumentException();
		}
		this.length = str.length();
		this.data = new int[length];
		for(int i = 0; i < length; i++) {
			data[i] = Character.getNumericValue(str.charAt(i));
		}
		
	}

	/**	
	 * @return the length of the binary number
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * @param index of the specified digit
	 * @return element at given index
	 */
	public int getDigit(int index) {
		if(index >= length || index < 0) {
			System.out.println("Out of bounds!");
			return -1;
		}
		else {
			return data[index];
		}
		
	}
	
	/**
	 * @return the array representation of the binary number
	 */
	public int[] getInnerArray() {
		return data;
		
	}
	
	/**
	 * computes the bitwise or of two binary numbers with equal length without altering the originals
	 * @param bn1 first binary number
	 * @param bn2 second binary number
	 * @return the bitwise or of the two binary numbers
	 */
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		if (bn1.getLength() != bn2.getLength()) {
			throw new IllegalArgumentException();
		}
		else {
			int bwor[] = new int[bn1.getLength()];
			for(int i = 0; i < bn1.getLength(); i++) {
				boolean dig1 = bn1.getInnerArray()[i] == 1 ? true : false ;
				boolean dig2 = bn2.getInnerArray()[i] == 1 ? true : false ;
				bwor[i] =  dig1 || dig2 ? 1 : 0;
			}
			return bwor;
		}
		
		
	}
	
	/**
	 * computes the bitwise and of two binary numbers with equal length without altering the originals
	 * @param bn1 first binary number
	 * @param bn2 second binary number
	 * @return the bitwise and of the two binary numbers
	 */
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		if (bn1.getLength() != bn2.getLength()) {
			throw new IllegalArgumentException();
		}
		else {
			int bwand[] = new int[bn1.getLength()];
			for(int i = 0; i < bn1.getLength(); i++) {
				boolean dig1 = bn1.getInnerArray()[i] == 1 ? true : false ;
				boolean dig2 = bn2.getInnerArray()[i] == 1 ? true : false ;
				bwand[i] =  dig1 && dig2 ? 1 : 0;
			}
			return bwand;
		}
		
	}
	
	/**
	 * Shifts this BinaryNumber in the specified direction and amount
	 * @param direction positive 1 for right and negative 1 for left
	 * @param amount to shift > 0
	 */
	public void bitShift(int direction, int amount) {
		if(amount < 0 ) {
			throw new IllegalArgumentException();
		}
		
		if(direction == -1) {
			length += amount;
			int[] old = data;
			data = new int[length];
			
			for(int i = 0; i < old.length; i++){
				data[i] = old[i];
			}
		}
		else if (direction == 1) {
			length -= amount;
			int[] old = data;
			data = new int[length];
			
			for(int i = 0; i < length; i++) {
				data[i] = old[i];
			}
			
		}
		else
			throw new IllegalArgumentException();
		
		
	}
	
	
	/**
	 * Adds this BinaryNumber and aBinaryNumber.  If the numbers are different lengths 0s are prepended.
	 * @param aBinaryNumber to be added to this BinaryNumber
	 */
	public void add(BinaryNumber aBinaryNumber) {
		int[] b = aBinaryNumber.getInnerArray();

		if(this.getLength() < aBinaryNumber.getLength()) 
		{
			int amount = aBinaryNumber.getLength()-this.getLength();
			length += amount;
			int old[] = data;
			data = new int[length];
			
			for(int i = amount; i < length; i++) {
				data[i] = old[i-amount];
			}
			
		}
		else
		{
			int amount = this.getLength()-aBinaryNumber.getLength();
			
			int length = aBinaryNumber.getLength() + amount;
			int old[] = aBinaryNumber.getInnerArray();
			b = new int[length];
			
			for(int i = amount; i < length; i++) {
				b[i] = old[i-amount];
			}
		}
		
		int[] result = new int[this.getLength()];
		
		int carry = 0;
		int sum = 0;
		for(int i = this.getLength()-1; i >= 0; i--) {
			sum = data[i] + b[i] + carry;
			if(sum > 2 ) {
				result[i] = 1;
				carry = 1;
			}
			else if (sum == 2) {
				result[i] = 0;
				carry = 1;
				
			}
			else if(sum == 1 ) {
				result[i] = 1;
				carry = 0;
			}
			else {
				result[i] = 0;
				carry = 0;
			}
			
		}

		if (carry == 1) {
			int[] old = result ;
			data = new int[old.length+1];
			data[0] = 1;
			for(int i = 0; i < old.length; i++) {
				data[i+1] = old[i];
			}
		}
		else
			data = result;

		
	}
	
	/**
	 * overrides toString to provide a String representation of a BinaryNubmer
	 */
	public String toString() {
		String num = "";
		for(int e: data) {
			num += e;
		}
		return num;
	}
	
	/**
	 * @return a decimal representation of a BinaryNumber
	 */
	public int toDecimal() {
		int sum = 0;
		
		for(int i = 0; i < length; i++) {
			sum += data[i] * Math.pow(2, length-i-1);
		}
		
		return sum;
	}
	
}
