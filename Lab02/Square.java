package Lab02;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Square
{

   public static void main(final String[] args)
   {
   // Instantiating the Finch object
      Finch myFinch = new Finch();

      myFinch.saySomething("Let me show you a square!");
      System.out.println("Let me show you a square!");
      // Set LED green, and move forward full speed for one second then turn right
      myFinch.setLED(0, 255, 0);
      myFinch.setWheelVelocities(255,255,3000);
      myFinch.setWheelVelocities(-255,-255,3000);

      myFinch.quit();
      System.exit(0);
    }
}
