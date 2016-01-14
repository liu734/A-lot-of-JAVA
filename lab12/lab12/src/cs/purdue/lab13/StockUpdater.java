package cs.purdue.lab13;


public class StockUpdater {


 public static void main(String[] args) {
   for(;;){
    
      int l= Stock.stocks_array.length;  
     try {
       Thread.sleep(40);
       
     }
     catch(Exception e){}
     

       
     
 
     updateList();
     
     if (l!=Stock.stocks_array.length)
      
       
       
     { 
       
       Update u=new Update(l);
       
       l= Stock.stocks_array.length;
     
     u.start();

     
     }
     
   }
 }
 
 public static void updateList(){
  Stock.update();
 }
}

 
