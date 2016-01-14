import java.io.*;
import javax.swing.*;
import java.util.*;
/**
 * Displays and updates high scores
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.7
 * @since   2011-11-21
 **/
public class HighScores{
    /** Used to display high scores and name prompt on the main frame **/
    public Frame myFrame;
    
    private String names[];    
    private int scores[];
   
    public HighScores(){
        // TODO: Check if the file named in Settings.highScoresFileName exists.
        // TODO: If it does not, create it with Settings.numScores entires of
        // TODO: name = nobody and score = 0.  If it does exist, load its contents
        // TODO: into the names and scores arrays.
        File file = new File(Settings.highScoresFileName);
        Scanner scan = null;
        if(file.exists()){
            //exception handling
            try{
                scan = new Scanner(file);
            }
            catch(Exception e){}
            System.out.println(Settings.highScoresFileName+" is now opened!");
            int count=0;
            //load contents to names and scores arrays
            names = new String[Settings.numScores];
            scores = new int[Settings.numScores];
            while(scan.hasNext()){
                names[count] = scan.next();               
                scores[count] = scan.nextInt();
                count++;
            }  
        }
        else{
            //create Settings.numScores entries
            names = new String[Settings.numScores];
            scores = new int[Settings.numScores];
            //loop to assign names to "nobody" and scores to 0
            for(int i=0 ; i<Settings.numScores ; i++){
                names[i] = "nobody";
                scores[i] = 0;
            } 
        }
    }
    
    /**
     * Display high scores
     * 
     * @param name  Name of player to highlight
     * @param score Score of player to highlight
     **/
    public void showHighScores(String name, int score){
        // TODO: Display high scores and if an entry has the same name and
        // TODO: score as the passed parameters, highilght it with >>> <<<
      //a String which holds the high scores in order to display
      String list = "";
      //to locate the high score of the current user
      for(int i=0 ; i<Settings.numScores ; i++){
        if(names[i].compareTo(name)== 0 && scores[i] == score){
          list += ">>>"+names[i]+ "-" + scores[i] + "<<<\n";//assign to list if the current user is having a high score
        }else{
          list += names[i]+ "-" + scores[i] + "\n";//assign to list if the scores and name not equals to the current user
        }
      }
      JOptionPane.showMessageDialog(myFrame,list,"High Scores",JOptionPane.PLAIN_MESSAGE);//Display the high scores
      
    }
    
    /**
     * Add score to high scores if greater than lowest high score
     * 
     * @param score Score to try to add
     **/
    public void addHighScore(int score){
        // TODO: If the provided score is greater than the lowest high score,
        // TODO: then replace the lowest score with the new score and then
        // TODO: call the sortScores() method.
      
        //compare with the last score in the file
        if(score > scores[Settings.numScores-1]){
          //prompt for current user high score name
          String name = JOptionPane.showInputDialog(myFrame,
                 "Congratulations!\nYou are in the high score list!\nEnter name: ",null
                     );
          if(name != null){
          
          
          
          //replace the last high score
          names[Settings.numScores-1] = name;
          scores[Settings.numScores-1] = score;
          sortScores();
          showHighScores(name,score);
          }
          
        
        }else{
          System.out.println("Not in high score list!");
        }      
    }
    /**
     * Sort bottom score up to correct position in high scores table
     **/
    public void sortScores(){
        // TODO: Use a single round of bubble sort to bubble the last entry
        // TODO: in the high scores up to the correct location.
      //temporary file for bubble sort
      String temp1;
      int temp2;
      //bubble sort 
      for(int i=Settings.numScores-1 ; i>0 ; i--){
          if(scores[i] > scores[i-1]){
            temp1 =names[i];
            temp2 = scores[i];
            names[i] = names[i-1];
            scores[i] = scores[i-1];
            names[i-1] = temp1;
            scores[i-1] = temp2;
          }
      }
    }
    
    /**
     * Write scores to high scores file
     **/
    public void writeScores(){
        // TODO: Write the high scores data to the file name indicated
        // TODO: by Settings.highScoresFileName.
      //Create a virtual file
      File file = new File(Settings.highScoresFileName);
      //Create PrintWritter object
      PrintWriter p = null;
      //try-catch block
      try{
        p = new PrintWriter(file);
      }catch(Exception e){
        System.out.println(e+"\nA new highScores.txt is now created!");
      }
      //Print data into highScores.txt
      for(int i=0 ; i<Settings.numScores ; i++){
        p.println(names[i]+" "+scores[i]);
      }
      p.close();
    }
}
