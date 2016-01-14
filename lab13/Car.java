
public class Car extends Vehicle{

  
  public Car(int id)
  {
     super(id);
    
  }
  
  public void doneFixing(){
    System.out.println("Car "+id+" is done being fixing");
  }


}