/**
 * 
 */
package edu.ilstu;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * 
 */
public class Driver 
{

	/**
	 * Main method
	 * 
	 * @param args
	 */
	
	static Scanner keys;
	static String message = "";
	static MorseTree tree;
	
	public static void main(String[] args) 
	{
		keys = new Scanner(System.in);		
		tree = new MorseTree();
		
		System.out.println("Welcome To The Decoder!\nUse \".\" Or \"-\" For Your Message!");
		
		while(!(message.toLowerCase().equals("stop")))
		{
			if(input() != false)
			{
				System.out.println(decodeMessage());
			}
		}
	}

	/**
	 * Asks the user to input a message
	 * 
	 * @return boolean (if message is valid or not)
	 */
	public static boolean input()
	{
		try
		{			
			System.out.print("\nPlease enter encoded message or stop to exit: ");
			message = keys.nextLine();
			
			/**
			 * Checking if message is valid or not
			 */
			if(message.toLowerCase().equals("stop"))
			{
				System.out.println("Thank you for using our decoder!");
				return false;
			}
			
			for(int i = 0; i < message.length(); i++)
			{	
				if(!(message.substring(i,i+1).equals(".")) && !(message.substring(i,i+1).equals("-")) && !(message.substring(i,i+1).equals(" ")))
				{
					System.out.println("\nPlease Enter A Valid Morse Code!\n");
					i = message.length();
					return false;
				}
			}
			return true;
		}
		catch(InputMismatchException ime)
		{
			return false;
		}
	}
	
	/**
	 * Decodes each individual letter of the string
	 * 
	 * @return the decoded message
	 */
	public static String decodeMessage()
	{
		/**
		 * Split the message 
		 * by space
		 * 
		 * Decode each individual split
		 * and combine it 
		 * all together
		 */
				
		String[] split = message.split(" ");
		String msg = "";
		
		for(int i = 0; i < split.length; i++)
		{
			msg += tree.decode(split[i]);
		}
		
		
		if(msg.indexOf("0") != -1)
		{
			return "\nPlease Enter A Valid Morse Code!\n";
		}
		
		
		return "\nThe Decoded Message Is: " + msg;
	}
	
}
