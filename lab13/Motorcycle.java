
public class Motorcycle extends Vehicle{
  public Motorcycle (int id)
  {

    super(id);
    
  }
  
  public void doneFixing(){
    System.out.println("Motorcycle "+id+" is done being fixing");
  }

}