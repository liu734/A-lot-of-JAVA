package Lab02;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;


public class AvoidObstacles {
   public static void main(final String[] args)
   {
   // Instantiating the Finch object
      int count = 0;
      Finch myFinch = new Finch();

      // The robot will stop until it finds three obstacles
      while(count < 3) {
          // If there's an obstacle, turn LED red, back up for 750 ms
          // and turn for 500 ms
        if(myFinch.isObstacle()) {
            myFinch.setLED(255,0,0);
            myFinch.setWheelVelocities(-255,-255,750);
            myFinch.setWheelVelocities(255,-255, 500);
            myFinch.buzz(440, 500);
            count++;
        }
        // Else, robot goes straight, LED is green
        else {
            myFinch.setWheelVelocities(255,255);
            myFinch.setLED(0,255,0);
        }
       }
           // Always end your program with finch.quit()
      myFinch.quit();
      System.exit(0);
    }

}
