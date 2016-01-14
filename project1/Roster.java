/**
 *Project 1 -- Roster
 *This program inputs the the lab section number, the TA's name, names and CS Login of the four students. 
 *The program generate the random grades and computes the average grade.
 *
 *@author Liu, Jing Ye
 *
 *@recitation 03 Tyler Holzer
 *
 *@date September 19,2011
 *
 */

import java.util.*;

public class Roster{
  
  public Roster(){    
    String enterN="Please enter the section number: "; //prompt user to enter section number 
    String enterT="Please enter the TA's name: "; //prompt user to enter TA's name 
    String enterSN="Please enter the student's name: "; //prompt user to enter student's name
    String enterSL="Please enter the student's cslogin: "; //prompt user to enter student's cslogin
    
    Random r=new Random(); //create a random object
    
    Scanner source= new Scanner(System.in); // create a scanner object
    
    System.out.print(enterN);  //print out the input dialog
    String ta=source.nextLine(); //scan the input to a string ta
    
    System.out.print(enterT);
    String section=source.nextLine(); //scan the section number 
    
    String name[]= new String[4]; //create an array to store the student's name
    String login[]=new String[4]; //create an array to store the student's login
    int grade[]=new int[4]; // an array to store grade
    
    int sum=0; //initiate sum of four grades =0
    
    for (int i=0 ; i<4; i++){ //a 4 times loop to record the data form user
      
      System.out.print(enterSN); //prompt user to enter student's name
      name[i]= source.nextLine(); //store the student's in array
      
      System.out.print(enterSL); //enter dialog
      login[i]=source.nextLine(); //store the login
      
      grade[i]=70+r.nextInt(31); //generate the random grade [70, 100] and store in array
      sum=sum+grade[i]; //plus t7he grade on sume
    }
    
    double average = sum/4.0;// average grade
    
    System.out.println("CS 180 Problem Solving and Object Orientetd Programming,Fall 2011");
    System.out.println("\t\tStudent Roster");
    System.out.println("Section: "+section+" TA name: "+ta+" Number of students: 4\n"); //output TA's names
    
    System.out.println("Name\t\t\tCSLogin\t\t\tLab01 Grade"); 
    System.out.println("----\t\t\t-------\t\t\t-----------"); 
    for (int i=0;i<4;i++)
    {
      System.out.println(name[i]+"\t\t\t"+login[i]+"\t\t\t"+grade[i]); //output name and login from array of four students
    }
    System.out.format("%50s %.2f\n","Average Grade: ",average);  //output the average grade in a 2 decimals format
    
    
  }
  public static void main(String[] args) //main method 
  {
    new Roster();
  }
  
}