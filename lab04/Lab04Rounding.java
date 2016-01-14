/**
 * CS 180 - Lab 4: Rounding Practice
 * Lab author: James Wilkinson
 * Description: Perform several calculations with rounding
 * Student name: Liu, Jingye
 * Last modified: 10-Aug-2011
 **/

import java.util.*;
import java.lang.Math.*;

public class Lab04Rounding {
  
  public static void main(String[] args){
    // Declare variables
    int userIn;
    int myVar;
    double workNum;
    double adjust = 0.5;

    // Declare objects
    Scanner scan = new Scanner(System.in); // Used to get user input
    
    System.out.println("Lab 4: Rounding Practice");
    
    // Get starting parameter from user
    System.out.println("Enter an integer from 1-10: ");
    userIn = scan.nextInt();
    while (userIn < 1 || userIn > 10){
      System.out.println("Invalid input.  Please try again.");
      System.out.println("Enter an integer from 1-10: ");
      userIn = scan.nextInt();
    }
        
    // Convert number from int
    workNum = userIn;
    
    // Calculations
    
    // Divide by 10 and display
    System.out.print(workNum);
    workNum = workNum/10;
     workNum=100*workNum;
    workNum =Math.floor(workNum);
    workNum =workNum/100.0;
    System.out.println(" / 10 = " + workNum);
    
    // Subtract "adjust" and display
    System.out.print(workNum);
    workNum = workNum - adjust;
     workNum=100*workNum;
    workNum =Math.floor(workNum);
    workNum =workNum/100.0;
    System.out.println(" - " + adjust + " = " + workNum);
    
    // Multiply by 20 and display
    System.out.print(workNum);
    workNum = workNum * 20;
     workNum=100*workNum;
    workNum =Math.floor(workNum);
    workNum =workNum/100.0;
    System.out.println(" * 20 = " + workNum);
    
    // Find the remainder after dividing by 3 and display
    System.out.print(workNum);
    workNum = workNum % 3;
    workNum=100*workNum;
    workNum =Math.floor(workNum);
    workNum =workNum/100.0;
    System.out.println(" % 3 = " + workNum);
    
    // Invert and display
    System.out.print("1 / " + workNum + " = ");
    workNum = 1 / workNum;
     workNum=100*workNum;
    workNum =Math.floor(workNum);
    workNum =workNum/100.0;
    System.out.println(workNum);
    
    
    
    // Exercise 4.3a
   // System.out.print(workNum + " / 0 = ");
    //myVar = (int)workNum / 0;
    //System.out.println(myVar);
         
    // Exercise 4.3b
    //System.out.print(workNum + " / 0 = ");
   // try{
     // myVar = (int)workNum / 0;
   // }
   // catch(Exception e){
   // myVar=-1;
   // }
    //  System.out.println(myVar);
  }
}
