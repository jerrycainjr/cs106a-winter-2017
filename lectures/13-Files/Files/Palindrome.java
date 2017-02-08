
import acm.graphics.*;
import acm.program.*;

public class Palindrome extends ConsoleProgram {


	public void run() {
		setFont("Courier-24");
		while(true) {
			runPalindromeCheck();
		}
	}

	private void runPalindromeCheck() {
		String original = readLine("Enter text: ");
		println(original.charAt(0));
		if(isPalindrome(original)) {
			println("Is a palindrome!");
		} else {
			println("Is not a palindrome");
		}
	}

	private boolean isPalindrome(String original) {
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private String reverse(String original) {
		String toReturn = "";
		for(int i = 0; i < original.length(); i++) {
			toReturn = original.charAt(i) + toReturn;
		}
		return toReturn;
	}

}
