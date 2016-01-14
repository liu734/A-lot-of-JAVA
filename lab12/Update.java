package cs.purdue.lab13;

public class Update extends Thread{
   int position;
   public Update(int i){
     position=i;
   }
   
   
   public void run(){
   
   for(;;)
   {
     Stock s=Stock.getStockPrice(Stock.stocks_array[position].name);
      Stock.stocks_array[position].price_change_percent= (s.price-Stock.stocks_array[position].price)/Stock.stocks_array[position].price;
      Stock.stocks_array[position].price=s.price; 
     
   
   }
   
   
   }
 

 
 }