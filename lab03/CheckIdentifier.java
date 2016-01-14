/*
 *name: Liu, Jingye
 *cs login: liu734
 *section:003
 *date:Tue Sep 6 2011
 */
import java.util.*;

public class CheckIdentifier{
  
 
    public static void main(String[] args)
    {
        //TODO
      Scanner source = new Scanner (System.in);
      
      String Date, From, To;
      int Amount; 
      
      String enterD ="Data:";
      String enterF ="From:";
      String enterT ="To:";
      String enterA ="Amount:";
      
      System.out.print(enterD);
      Date=source.nextLine();
      
      System.out.print(enterF);
      From=source.nextLine();
      
      System.out.print(enterT);
      To=source.nextLine();
      
      System.out.print(enterA);
      Amount=source.nextInt();
      
      System.out.println(Date+" pay to "+To+" $"+Amount+" from "+From);
      System.out.println("Check accepted!");
    }
}