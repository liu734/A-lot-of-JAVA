package cs.purdue.lab13;

import android.os.Handler;
import android.widget.BaseAdapter;

public class Stock {
	
	static BaseAdapter list;
	static Handler handler;
	
	static Stock[] stocks_array;
	
	double price;
	double price_change_percent;
	String name;
	
	public static Stock getStockPrice(String name){
		Stock newStock=null;
		int j;
		for (j = 0; j<Stock.stocks_array.length; j++){
			if(stocks_array[j].name.equals(name)){
				newStock = new Stock();
				newStock.price = (int)(Math.random()*1000);
				newStock.name = stocks_array[j].name;
				break;
			}
		}
		
		try{Thread.sleep((long) (Math.random()*2000)+3500);}catch(Exception e){}
	    return newStock;
	}
	
	public static void update(){
		Runnable updateList = new Runnable(){
		
					@Override
					public void run() {
						list.notifyDataSetChanged();
					}
				};
		handler.postDelayed(updateList, 0);
	}
	
}
