import java.util.*;
import java.io.*;
public class exam2{

  public static void main(String[] args) {
    System.out.println(dumpFile("1.txt"));
    
  }
  public static int[][] setArray(int n){
    int [][] diagnonal= new int [n][n];
    
    for (int i =0; i< n; i++)
      for (int j=0; j< n; j++)
      if (i==j) diagnonal[i][j]=i;
   else diagnonal [i][j]=0;
    
    return diagnonal;
  
  }
  
  public static double dumpFile( String name){
    File f=new File(name);
    Scanner s= null;
    int sum=0;
    int count =0;
    try {s=new Scanner(f);}
    catch (Exception e) {}
    
    while (s.hasNext())
    {
      if (s.hasNextInt())
      {int temp=s.nextInt();
        sum=sum+temp;
      count ++;
      System.out.println(temp);
      }
      else System.out.print(s.next());
      
         
    }
    
    
    return (double) sum/count;
  
  } 
  
  
  readData(String name){
    File f= new File(name);
    
    Scanner s=null;
    try {s=new Scanner (f);}
    catch (Exception e){
  while (s.hasNext())
  {
    
  }
    
    }
  
    
  
  }


   


}