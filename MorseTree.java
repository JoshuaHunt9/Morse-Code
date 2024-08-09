/**
 * 
 */
package edu.ilstu;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 */
public class MorseTree 
{
	/**
	 * Custom Node Class
	 */
	private static class Node
	{
		private String data;
		private Node left;
		private Node right;
		
		public Node()
		{
			this.data = "";
		}
		
		/**
		 * Adds a node to the left side of current node
		 * 
		 * @param node
		 */
		public void addLNode(Node node)
		{
			left = node;
		}
		
		/**
		 * Adds a node to the right of current node
		 * 
		 * @param node
		 */
		public void addRNode(Node node)
		{
			right = node;
		}
	}
	
	private Node root;
	private String decodeMsg;
	
	private String[] letters;
	private String[] morse;
	
	private Scanner text;
			
	public MorseTree()
	{
		root = new Node();
		decodeMsg = "";
		letters = new String[26];
		morse = new String[letters.length];
				
		readFile("morse.txt");
		addToNodes();
		
	}
	
	/**
	 * Reads the file of the letters and morse code
	 * 
	 * @param fileName
	 */
	public void readFile(String fileName)
	{
		try
		{
			int i = 0;
			text = new Scanner(new File(fileName));
			
			while(text.hasNextLine() && i != 26)
			{
				String line = text.nextLine();
				
				letters[i] = line.substring(0,1);
				morse[i] = line.substring(5);
								
				i++;
			}
		}
		catch(IOException ie)
		{
			System.out.println("\nFile Was Unable To Be Found\n");
		}
	}
	
	/**
	 * Adds the specific morse code to it's correct node
	 * 
	 * @param data
	 */
	public void add(String data)
	{
		if(root.data == null)
		{
			root.data = "";
		}
		else
		{
			/**
			 * The letters should be ordered by tree level. 
			 * To find the position for a letter in the tree, 
			 * scan the code and branch left for a dot and branch right for a dash. 
			 * Your program should read an encoded message and decode it using the Morse code tree.
			 */
			
			Node temp = root;
			int loop = -1;
			
			while(temp != null && loop < 0)
			{
				/**
				 * traverses to get to the
				 * correct node location
				 */
				for(int i = 0; i < data.length(); i++)
				{
					Node node = new Node();
					
					if(data.substring(i,i+1).equals("."))
					{
						if(temp.left == null)
						{
							temp.addLNode(node);
							temp = temp.left;
						}
						else
						{
							temp = temp.left;
						}
					}
					
					if(data.substring(i,i+1).equals("-"))
					{
						if(temp.right == null)
						{
							temp.addRNode(node);
							temp = temp.right;
						}
						else
						{
							temp = temp.right;
						}
					}
				}
				
				
				/**
				 * determine the correct letter
				 * from the morse code
				 * and places the letter
				 * into the current node
				 */
				for(int q = 0; q < morse.length; q++)
				{
					if(data.equals(morse[q]))
					{
						temp.data = letters[q];
						q = morse.length;
						loop++;
					}
				}
				
				
			}
		}
	}
	
	/**
	 * Uses the add method to add the entire alphabet to their correct locations within the tree
	 */
	public void addToNodes()
	{			
		for(int i = 0; i < letters.length; i++)
		{
			add(morse[i]);
		}
	}
	
	/**
	 * Decodes the message
	 * 
	 * @param msg
	 * @return decoded string
	 */
	public String decode(String msg)
	{
		
		/**
		 * Decodes the
		 * string message
		 */
		
		Node node = root;
		int i = 0;
		
		while(node != null && i < msg.length())
		{
			if(msg.substring(i,i+1).equals("."))
			{
				node = node.left;
			}
			if(msg.substring(i,i+1).equals("-"))
			{
				node = node.right;
			}
			i++;
		}
		
		try
		{
			decodeMsg += node.data;
		}
		catch(NullPointerException npe)
		{
			return "0";
		}
		
	
		
		
		String temp = decodeMsg;
		decodeMsg = "";
		return temp;
	}
	
	
}
