/*Jingye Liu
 * liu734
 * 
*/

import java.io.*;
import java.util.*;

public class MedicalDatabase{

    // We will store the information found in the file here
    String[][] patients;

    // Constructor - reads a csv file into the patients array
    public MedicalDatabase(String filename){
        File mdFile = new File(filename);
        Scanner sc = null;

        // Attempt to open the file
        try {
            sc = new Scanner(mdFile);
        } catch (IOException e) {
            System.err.println("Encountered an I/O error");
            System.exit(1);
        }

        // We know before hand (by looking at the file) that there are 1000 entries)
        patients = new String[1000][];

        // Iterate through each line in the file
        for (int i = 0; sc.hasNextLine(); i++) {
            patients[i] = sc.nextLine().split(",");
        }

        sc.close();
    }

    // Print out the contents of the file
    public void print(){
        for (int i = 0; i < patients.length; i++) {
            for (int j = 0; j < patients[i].length-1; j++) {
                System.out.print(patients[i][j] + ",");
            }
            System.out.println(patients[i][patients[i].length-1]);
        }
    }

    // Print the contents of the file in an easy to read format
    public void printFormat(){
        for (int i = 0; i < patients.length; i++) {
            String gender = "Mr."; // Assume male until proven otherwise
            if (patients[i][3].equals("F")) {
                gender = "Mrs.";
            }
            // row gen.last \troom \tward
            System.out.println((i+1) + ". " + gender + patients[i][1]
                    + " \t" + patients[i][5] + " \t" + patients[i][4]);
        }

    }

    // Search the patients array for an entry that matches
    // the given last name and department
    public void search(String lname, String department){
      int count=1;
      for (int i = 0; i < patients.length; i++) {
        if (lname.equals(patients[i][1]) && department.equals(patients[i][4])){
                String gender = "Mr."; 
                if (patients[i][3].equals("F")) {
                  gender = "Mrs.";
                }
                System.out.println(count + ". " + gender + patients[i][1]
                    + " \t" + patients[i][5] + " \t" + patients[i][4]);
                count++;
        }  
      } 

    }

    // Sort the patients array by department
    public void sortByDepartment(){
      int count= patients .length;
      String t[];
      while (count>=0){
      for (int i = 0; i < patients.length-1; i++) {
        if (patients[i][4].compareTo(patients[i+1][4])>0){
        t=patients[i];
        patients[i]=patients[i+1];
        patients[i+1]=t;

        }
      }
       count--;
    }
    }
    // Sort the patients array by department, and by last name within department
    public void sortByDepartmentAndName(){
      int count= patients .length;
      String t[];
      while (count>=0){
      for (int i = 0; i < patients.length-1; i++) {
        if (patients[i][4].compareTo(patients[i+1][4])>0)
        {
        t=patients[i];
        patients[i]=patients[i+1];
        patients[i+1]=t;

        }
        else if(patients[i][4].compareTo(patients[i+1][4])==0 && patients[i][1].compareTo(patients[i+1][1])>0)
        {

          t=patients[i];
          patients[i]=patients[i+1];
          patients[i+1]=t;

        }
        
      }
      count--;
    }

    }

    // Main method - called when the program is first run
    public static void main(String[] args){
        // Create a new MedicalDatabase on the contents of a file
        MedicalDatabase d = new MedicalDatabase("medical_database.txt");

        // Check that there is a command line argument
        if(args.length > 0) {
            // Take action according to command line arguments
            if(args[0].equals("1")) {
                d.print();

            } else if(args[0].equals("2")) {
                d.printFormat();

            } else if(args[0].equals("3")) {
                d.search("Davis","Urgent Care");

            } else if(args[0].equals("4")) {
                d.sortByDepartment();
                d.printFormat();

            } else if(args[0].equals("5")) {
                d.sortByDepartmentAndName();
                d.printFormat();
            }
        }


    }


}
