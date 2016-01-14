/*
 * Author: Yiyang Pan & Liu, Jingye
 * User name: pan41 & liu734
 * 
*/
package Lab06;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.awt.*;

/**
 * Created by: James Wilkinson
 * Date: 10-Jul-2011
 * Revised: 22-Sep-2011
 * CS 180 - Lab 6 - Robot Lab 2
 */

public class Lab06{
  private static String inString;
  private static HashMap<String,String> hm;
  private static Finch myFinch;
  
  public static void main(final String[] args){
    String fileInName = "Lab06/Lab06.txt";
    hm = new HashMap<String,String>();
    
  /****************************************
   * DO NOT EDIT ANY CODE ABOVE THIS LINE *
   ****************************************/
    myFinch= new Finch();
    myFinch.setLED(255,255,255);
    // TODO: Initialize the Finch and set it's beak color to white
      File in = new File(fileInName);
    
    inString =null;
   
    // TODO: Create a File that opens the file indicated by fileInName and initialize inString
  
    // TODO: Create a Scanner to get keyboard input from the user
    //String input =source.nextLine();
    // TODO: Create a String to hold the input from the user
    Scanner source2=null;
    try{
    
    source2= new Scanner(in);
    }
    catch(Exception e){ System.exit(0);};
    // TODO: Create a Scanner to get data from the File above and try to initialize it
      

      while (source2.hasNextLine())
    {
      inString=source2.nextLine();
      System.out.println(inString);
      parseLine(inString, hm);
  }
      
    // TODO: Try to create a loop that uses the file Scanner to read a line 
    //       at a time while until done reading the file.  After each successful
    //       read, pass the String to parseLine(). 
    Scanner source=new Scanner(System.in); 
    System.out.print("Enter a command: ");
    String command=source.nextLine();
    // TODO: Prompt the user to enter a command and use the Scanner to get a String from the user
    while(!command.equals("quit"))
    {
      Lab06.runLine(command,hm);
      System.out.print("Enter a command: ");
      command=source.nextLine();
    }
    System.exit(0);
    // TODO: Create a loop that sends the String entered by the user to runLine(), prompts the user to enter a command,
    //       uses a Scanner to get a String from the user and terminates when the user enters "quit"
    
    // TODO: Quit the Finch object
    
  /****************************************
   * DO NOT EDIT ANY CODE BELOW THIS LINE *
   ****************************************/
  }
  
  /* moveString controls movement of the Finch
   * Read each character duple (character)(one-digit integer)
   *
   * (character) - action
   * F - move forward
   * B - move backward
   * R - turn right
   * L - turn left
   * S - stop the wheels
   *
   * After each movement, sleep for 160*(one-digit integer) milliseconds
   */
  private static void moveString(String inString){
    int i = 0; // Loop index
    while(inString.length()>0){
      i++;
      switch (inString.charAt(0)) {
        case 'F': myFinch.setWheelVelocities(255,255); break;
        case 'B': myFinch.setWheelVelocities(-255,-255); break;
        case 'R': myFinch.setWheelVelocities(255,-255); break;
        case 'L': myFinch.setWheelVelocities(-255,255); break;
        case 'S': myFinch.stopWheels(); break;
      }
      myFinch.sleep(160*Integer.parseInt(inString.charAt(1)+""));
      inString = inString.substring(2);
      }
    myFinch.stopWheels();
  }
 
 /* parseLine takes a string and adds its contents to the HashMap if it
  * matches the input file format.
 */ 
  private static void parseLine(String inString, HashMap<String,String>hm){
    String patternStr = "([a-z])=([a-zA-Z0-9]*)";
    String key;
    String value;
    boolean matchFound; 
    
    Pattern pattern = Pattern.compile(patternStr);
    Matcher matcher = pattern.matcher(inString);
    matchFound = matcher.matches();
    if(matchFound){
      key = matcher.group(1);
      value = matcher.group(2);
      hm.put(key, value);
    }
  }
 
 /* runLine checks the HashMap to see if the given runString is a valid command.
  * If it is valid, then it sends the commands to the moveString method otherwise
  * it states that the command is not found.
  */ 
  private static void runLine(String runString, HashMap<String,String> hm){
    // Get the command to run
    String result = hm.get(runString);
    if (result != null){
      result = interpretString(result, hm);
      moveString(result);
    }
    else{
      System.out.println(runString + " not found.");
    }
  }
  
 /* interpretString repeatedly checks the inString for matches in the HashMap.
  * If a match is found, it replaces it with the string from the HashMap and then
  * checks the string again.  When a match is not found, the inString is returned.
  */
  private static String interpretString(String inString, HashMap<String,String> hm){
    String patternStr = "([A-Z0-9]*)([a-z])([a-zA-Z0-9s]*)";
    String key;
    String first;
    String second;
    boolean matchFound;
    
    Pattern pattern = Pattern.compile(patternStr);
    Matcher matcher = pattern.matcher(inString);
    matchFound = matcher.matches();
    while(matchFound){
      first = matcher.group(1);
      key = matcher.group(2);
      second = matcher.group(3);
      inString = first + hm.get(key) + second;
      matcher = pattern.matcher(inString);
      matchFound = matcher.matches();
    }
    return inString;
  }
}
