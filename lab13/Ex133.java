import java.util.*;
class Ex133 extends Thread{
  Vehicle v;
  public Ex133(){
    Random r= new Random();
    v= new Vehicle(r.nextInt());
  }
  
  public void run(){
        v.fix();
  }
  
  synchronized public void retrieveV(){
    while (true){}

      
  
  }
  
  synchronized public void setV(){
    
     
  }
  
  public static void main(String[] args){
    Ex133 e1= new Ex133();
    Ex133 e2= new Ex133();
    Ex133 e3= new Ex133();
    Ex133 e4= new Ex133();
    Ex133 e5= new Ex133();
    
    e1.start();
    e2.start();
    e3.start();
    e4.start();
    e5.start();
    
    
    try{
            e1.join();
            e2.join();
            e3.join();
            e4.join();
            e5.join();
            
    }catch(Exception e){}


}
}