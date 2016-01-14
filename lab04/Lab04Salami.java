/**
 * CS 180 - Lab 4: Salami Slicing
 * Lab author: James Wilkinson
 * Description: Illustrate the use of numeric types through salami slicing
 * Student name: Liu,Jingye
 * Last modified: 06-Jun-2011
 **/

import java.util.*;
import java.lang.Math.*;
import java.text.*;

public class Lab04Salami {
  
  public static void main(String[] args){
    // Declare variables
    double principle;     // Starting principle
    double interest;      // Yearly interest rate (50% is stored as 0.50)
    double futureValue;   // Future value without rounding
    double adjustedValue; // Future value after liberal rounding
    double difference;    // Difference between future value with and without rounding
    int years;            // Years for the accounts to mature
    int periods;          // Number of compounding periods per year
    int accounts;         // Number of bank accounts

    // Declare objects
    Scanner scan = new Scanner(System.in); // Used to get user input
    NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); // Used to format doubles to US currency
    
    System.out.println("Lab 4: Salami Slicing");
    
    // Get starting parameters from user
    System.out.print("Number of accounts: ");
    accounts=scan.nextInt();// TODO
    System.out.print("Starting principle per account: ");
    principle=scan.nextDouble();// TODO
    System.out.print("Yearly interest rate (%): ");
    interest = scan.nextDouble()/100; // TODO
    System.out.print("Compounding periods per year: ");
    periods = scan.nextInt(); // TODO
    System.out.print("Years to mature: ");
    years = scan.nextInt(); // TODO
    
    // Calculate future value without rounding for each account
    futureValue =principle* (Math.pow((1+interest/periods),periods*years)); // TODO
    
    // Calcuate the total future value
    futureValue = futureValue*accounts; // TODO
    
    // Calculate future value with rounding for each account
    adjustedValue = principle; // TODO
    for (int i = 0; i < periods*years; i++){
      adjustedValue = adjustedValue*(1+interest/periods);
      adjustedValue=adjustedValue*100;
      adjustedValue=Math.floor(adjustedValue);
      adjustedValue=adjustedValue/100.0;
    
// TODO
    }
    
    
    
    // Calculate total adjusted future value
    adjustedValue = adjustedValue*accounts; // TODO
    
    // Calculate difference
    difference = futureValue-adjustedValue; // TODO
    
    // Display results
    System.out.println("The future value per account should be " + n.format(futureValue/accounts) + ".");
    System.out.println("The adjusted value per account is " + n.format(adjustedValue/accounts) + ".");
    System.out.println("The difference per account is " + n.format((futureValue - adjustedValue)/accounts) + ".");
    System.out.println("The total amount sliced after " + years + " years is " + n.format(difference) + ".");
  }
}
