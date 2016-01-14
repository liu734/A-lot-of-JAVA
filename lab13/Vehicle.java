import java.util.*;
public class Vehicle{
  
  protected int fix_time;
  protected int id;
  
  public Vehicle(int id){
    fix_time=500;
    this.id=id;
  }
  
  public void fix(){
    try{Thread.sleep(fix_time);}catch(Exception e){}
    doneFixing();
  }
  
  public void fix(int sleep ){
    try{Thread.sleep(sleep);}catch(Exception e){}
    doneFixing();
  }
  
  public void doneFixing(){
    System.out.println("Vehicle "+id+" is done being fixing");
  }
  
  public static void main(String[] args){
  Random r= new Random();
  Car c= new Car(r.nextInt(1000));
  Motorcycle m= new Motorcycle(r.nextInt(1000));
  Truck t= new Truck(r.nextInt(1000));
  Vehicle v = new Vehicle(r.nextInt(1000));
  c.fix();
  m.fix();
  t.fix();
  v.fix();
  
}  
  
}