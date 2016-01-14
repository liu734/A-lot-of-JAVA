import java.io.*;
import java.util.*;

public class MedicalDatabase1{
  
  String[][] patients;
  Scanner s=null;
  File f=new File("medical_database.txt");
  
  public MedicalDatabase(){
    

    
  }
  
  public void print(){
    
    
    
    try {
      s=new Scanner(f);
      while (s.hasNext()) {System.out.println(s.nextLine());} 
    }
    catch (Exception e ){System.exit(0);} 

  }
  
  public void printFormat(){
    String line;
    int row =1;
    try {
      
      s=new Scanner(f);
      while (s.hasNext()) {
       line = s.nextLine();
       String temp[] = new String[6];
       temp = line.split(",");
       if (temp[3].equals("F"))
       {System.out.println("%d. Mr.%s\t%s\t%s\n",row, temp[1],temp[5],temp[4]);}
       
       else{System.out.printf("%d. Mr.%s\t%s\t%s\n",row, temp[1],temp[5],temp[4]);}
       

        row++;
       }
    }
    catch (Exception e ){System.exit(0);} 
  }
  
  public void search(String name,String department){
        String line;
    int row =1;
    while (s.hasNext()) {
       line = s.nextLine();
       String temp[] = new String[6];
       temp = line.split(",");
         if (name==temp[1]&&department== temp[4]){
         
         if (temp[3].equals("F"))
         {System.out.println(row+". Mr."+temp[1]+"\t"+temp[5]+"\t"+temp[4]);}
         else{System.out.printf(row+". Mr."+temp[1]+"\t"+temp[5]+"\t"+temp[4]);}
       }
        row++;
       }

  }
  
  public void sortByDepartment(){
    
    
  }
  
  public void sortByDepartmentAndName(){

    
  }
  
  public static void main(String[] args){
        
    MedicalDatabase d = new MedicalDatabase();
    
    
    //d.print();
    //d.printFormat();
    
    if(args.length>0){
      if(args[0].equals("1")){
        d.print();
      }else if(args[0].equals("2")){
        d.printFormat();
      }else if(args[0].equals("3")){
        d.search("Davis","Urgent Care");
        //d.printFormat();
      }else if(args[0].equals("4")){
        d.sortByDepartment();
        d.printFormat();
      }else if(args[0].equals("5")){
        d.sortByDepartmentAndName();
        d.printFormat();
      }
    }

    
  }
  
  
  }
