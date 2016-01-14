package Lab02;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;


public class OrientationSelector {

   public static void main(final String[] args)
   {
   // Instantiating the Finch object
      Finch myFinch = new Finch();

      // If the Finch's beak is pointed up, turn the LED white for three seconds
      if(myFinch.isBeakUp()) {
          myFinch.saySomething("My beak is pointed up!");
          System.out.println("My beak is pointed up!");
          myFinch.setLED(255,255,255);
          myFinch.sleep(3000);
      }

      // If the Finch wheels are on the ground, turn the LED black
      else if(myFinch.isFinchLevel()) {
          myFinch.saySomething("My wheels are on the ground!");
          System.out.println("My wheels are on the ground!");
          myFinch.sleep(5000);
          myFinch.setLED(0, 0, 0);
       }
       // If the Finch is upside down, have it ask for help and check if someone
       // helped it out (by flipping it back over)
       else if(myFinch.isFinchUpsideDown()) {
          myFinch.saySomething("Oh no, I'm flipped, please help me!");
          System.out.println("Oh no, I'm flipped, please help me!");
          // set LED to red
          myFinch.setLED(255,0,0);
          myFinch.sleep(10000);
          if(myFinch.isBeakUp() || myFinch.isFinchLevel()) {
              myFinch.setLED(0,255,0);
              myFinch.saySomething("Thanks for helping me");
              System.out.println("Thanks for helping me");
          }
          else {
              myFinch.saySomething("No one helped me, I'm so sad");
              System.out.println("No one helped me, I'm so sad");
          }
          myFinch.sleep(5000);
        }

      // Always end your program with finch.quit()
      myFinch.quit();
      System.exit(0);
    }
}
