package Lab05;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.*;
import java.awt.*;

/**
 * Created by: James Wilkinson
 * Date: 16-Jul-2011
 * CS 180/Lab 5 - Robot Lab 1: Primitive Types and Concurrency
 * Author:
 * Yiyang pan
 * Liu, Jingye
 */

public class Lab05{
 // Declare class variables
 public static boolean override;
 public static String dance;
 public static boolean dancing;
 private static Finch myFinch;
 private static double basetemp;
 
 public static void main(final String[] args){
  myFinch= new Finch();  // Create objects and initialize variables
  Thread myDance = new Thread(new Dance());
  Thread myBackup= new Thread(new Backup());
  
  dancing =true;
  int moveTime;
  boolean override=false;
   

 
 
  // Get base temperature
 myFinch.setLED(255,255,255);
  // Set LED to white
 myDance.start();
 myBackup.start();
  // Start threads
 System.out.print("Enter a dance (1-3), s to stop or q to quit: ");
 
 Scanner source=new Scanner(System.in);
 do
 {
    dance= source.nextLine();
 }
 while( !dance.equals("q"));
 

 
 myFinch.quit();
 

 
  // Get keyboard input from user until 'q' is entered
 
  // Wait for classes to join and then quit

 }
 // Returns true if temp is 1C hotter than the base temp
 public static boolean isHot(){
   return myFinch.isTemperature(basetemp+1);
 }

 // Used to control the Finch's movements
 public static void move(String action, int moveTime){
   if (!override){
     if (action.equals("turnLeft"))
     {
       myFinch.setLED(0,0,255);
       myFinch.setWheelVelocities(-255,255);
       myFinch.sleep(moveTime);
     }
     else if(action.equals("turnRight")){
       myFinch.setLED(0,255,0);
       myFinch.setWheelVelocities(255,-255);
       myFinch.sleep(moveTime);
     }
     else if(action.equals("forward"))
     {
       myFinch.setLED(255,0,0);
       myFinch.setWheelVelocities(255,255);
       myFinch.sleep(moveTime);
     }
     else if(action.equals("backward"))
     {
       myFinch.setLED(255,255,0);
       myFinch.setWheelVelocities(-255,-255);
       myFinch.sleep(moveTime);
     }
     else{
     myFinch.setLED(255,255,255);
     myFinch.stopWheels();
     myFinch.sleep(moveTime);
     }
   }
 }

 // Emergency reverse for moveTime seconds
 public static void eReverse(int moveTime){
   myFinch.setLED(255,0,0);
   override = true;
   myFinch.setWheelVelocities(-255,-255);
   myFinch.sleep(moveTime);
   override = false;
   return;
   

 }
}

// Runnable class that contains dance patterns
class Dance implements Runnable{
 public void run(){
    while(Lab05.dancing){
            if( Lab05.dance.equals("1") ){
                Lab05.move("turnLeft", 400);
                Lab05.move("turnRight", 800);
                Lab05.move("turnLeft", 400);
            }else if ( Lab05.dance.equals("2") ){
                Lab05.move("turnLeft", 200);
                Lab05.move("forward", 400);
                Lab05.move("backward", 400);
                Lab05.move("turnRight", 400);
                Lab05.move("forward", 400);
                Lab05.move("backward", 400);
                Lab05.move("turnLeft", 200);
            }else if ( Lab05.dance.equals("3") ){
                Lab05.move("forward", 400);
                Lab05.move("turnRight", 800);
                Lab05.move("backward", 400);
                Lab05.move("forward", 800);
            }else if (Lab05.dance.equals("q")){
                Lab05.dancing=false;
            }else{
                Lab05.move("stop", 100);
            }
  }
 }
}

// Runnable class that backs up Finch if it gets hot
class Backup implements Runnable{
 public void run(){
    while(Lab05.dancing==true)
 {
   if(Lab05.isHot())
   {Lab05.eReverse(400);}
   try{Thread.sleep(100);}
   catch (Exception e){};

   
 }

 }
}
