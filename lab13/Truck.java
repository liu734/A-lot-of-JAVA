public class Truck extends Vehicle
{
  public Truck(int id){
    super (id);
    
  }
  
  public void doneFixing(){
    System.out.println("Truck "+id+" is done being fixing");
  }
  

}