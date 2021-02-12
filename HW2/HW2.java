//Jacob Roessler
//I pledge my honor that I have abided by the Stevens Honor System.

public class HW2 {
	
	public static void method1(int n){
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.println("Operation "+ counter);
				counter++;
			}
		}
	}
	
	public static void method2(int n) {
		int counter = 0;
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++)
				for(int k = 0; k < n; k++){
					System.out.println("Operation "+ counter);
					counter++;
				}
		
	}
	
	public static void method3(int n) {
		int counter = 0;
		for(int i = 1; i < n; i*=2) {
			System.out.println("Operation "+ counter);
			counter++;
		}
	}
	
	public static void method4(int n) {
		int counter = 0;
		for(int i = 0; i < n; i++)
			for(int j = 1; j < n; j*=2) {
				System.out.println("Operation "+ counter);
				counter++;
			}
	}
	
	public static void method5(int n) {
		int counter = 0;
		for(int i = 1; i < n; i*=2)
			for(int j = i; j < n; j*=2) {
				System.out.println("Operation "+ counter);
				counter++;
			}
	}
	
	public static int method6(int n) {
		//Fibonacci function using recursion
	    if (n <= 1) 
	    	return n;
	    return method6(n - 2) + method6(n - 1);
	}
	
}
