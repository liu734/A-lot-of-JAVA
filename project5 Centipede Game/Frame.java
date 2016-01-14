import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * Creates the JFrame and its menu
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.7
 * @since   2011-11-20
 **/
public class Frame extends JFrame implements MenuListener, ActionListener{
  /** Used to add the Canvas to the frame **/
  private GameCanvas myGameCanvas;
  
  /** Used for pausing, unpausing and starting new games.  Also for adjusting difficulty  **/
  private GameLogic myGameLogic;
  
  /** Used to display high scores **/
  private HighScores myHighScores;
  
  private JMenuBar bar;
  
  /** File menu **/
  private JMenu file;
  
  /** Synchronously starts a new game **/
  private JMenuItem newGame;
  
  /** Pauses the game **/
  public JMenuItem pauseGame;
  
  /** Saves high scores and exits the game **/
  private JMenuItem exitGame;
  
  /** Options menu **/
  private JMenu options;
  
  /** Set game speed to easy **/
  private JRadioButtonMenuItem easyDifficulty;
  
  /** Set game speed to medium **/
  private JRadioButtonMenuItem mediumDifficulty;
  
  /** Set game speed to hard **/
  private JRadioButtonMenuItem hardDifficulty;
  
  /** Toggles the super laser **/
  private JMenuItem superLaser;
  
  /** Display high scores **/
  private JMenuItem highScores;
  
  /**
   * @param inGameLogic  Used for pausing, unpausing and starting new games.  Also for adjusting difficulty
   * @param inGameCanvas Used to add the Canvas to the frame
   * @param inHighScores Used to display high scores
   **/
  public Frame(GameLogic inGameLogic, GameCanvas inGameCanvas, HighScores inHighScores){
    
    
// TODO: Set myGameCanvas, myGameLogic and myHighScores to equal        
// TODO: their corresponding passed parameters.
    myGameCanvas=inGameCanvas;
    myGameLogic=inGameLogic;
    myHighScores=inHighScores;
    // TODO: Setup the file menu.
    
    file= new JMenu("File");
    newGame=new JMenuItem("New Game");
    pauseGame=new JMenuItem("Pause Game");
    exitGame=new JMenuItem("Exit Game");
    newGame.addActionListener(this); 
    pauseGame.addActionListener(this);
    exitGame.addActionListener(this);
    file.add(newGame);
    file.add(pauseGame);
    file.add(exitGame);
    
    // TODO: Setup the options menu
    easyDifficulty=new JRadioButtonMenuItem( "Easy Difficulty");
    mediumDifficulty=new JRadioButtonMenuItem ("Medium Difficulty");
    hardDifficulty=new JRadioButtonMenuItem ("Hard Difficulty");
    superLaser=new JMenuItem ("Super Laser OFF");
    options=new JMenu("Options"); 
    easyDifficulty.addActionListener(this);
    mediumDifficulty.addActionListener(this);
    hardDifficulty.addActionListener(this);
    superLaser.addActionListener(this);
    options.add(easyDifficulty);
    options.add(mediumDifficulty);
    options.add(hardDifficulty);
    options.add(superLaser);
    
    
    
    // TODO: Setup the high scores menu
    
    highScores=new JMenuItem("High Scores");
    highScores.addActionListener(this);
    // TODO: Setup the menu bar
    bar= new JMenuBar();
    bar.add(file);
    bar.add(options);
    bar.add(highScores);
    setJMenuBar(bar);
    // TODO: Setup the frame using GridBagLayout
    setLayout(new GridBagLayout());
    // Correctly close when clicking the X button
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Create the application a 100px from the top/side of the screen
    setLocation(100,100);
    // Disable resizing of the window
    setResizable(false);
    
    // TODO: Add the game canvas
    add(myGameCanvas.c);    
    // Shrink the frame to fit its contents
    pack();
    
    // TODO: Make the frame visible
    setVisible(true);
  }
  
  /**
   * Unused
   * 
   * @param e MenuEvent that occured
   **/
  public void menuCanceled(MenuEvent e){
  }
  
  /**
   * Unused
   * 
   * @param e MenuEvent that occured
   **/
  public void menuDeselected(MenuEvent e){
  }
  
  /**
   * Unused
   * 
   * @param e MenuEvent that occured
   **/
  public void menuSelected(MenuEvent e){
  }
  
  /**
   * Handles the selection of any menu item
   * 
   * @param e ActionEvent that occured
   **/
  public void actionPerformed(ActionEvent e){
    
    
// TODO: If new game selected, send a synchronous reset to the GameLogic
    Object o=e.getSource();
    if(o.equals(newGame)){
      myGameLogic.syncReset=true;
      easyDifficulty.setSelected(false);      
      mediumDifficulty.setSelected(false);
      hardDifficulty.setSelected(false);

    }
    // TODO: If high scores selected, pause the game and show the high scores.
    else if(o.equals(highScores)){
            myGameLogic.paused = true;     
            myHighScores.showHighScores("", 0);
            myGameLogic.paused = false;
    }
    // TODO: Be sure to unpause after the high scores window closes.
    else if(o.equals(pauseGame)){

      if(pauseGame.getText().equals("Pause Game")){

        myGameLogic.paused=true;
        pauseGame.setText("Unpause Game");
      }
      else{

        myGameLogic.paused=false;
        pauseGame.setText("Pause Game");
      }
    }

    
    
    
    // TODO: If pause game selected, pause/unpause the game and set the menu item text to
    // TODO: "Pause Game" if unpaused or "Unpause Game" if paused.
    
    // TODO: If exit game selected, save the high scores and then exit
    else if(o.equals(exitGame)){
                  myHighScores.writeScores();
                  System.exit(0);
      
      
    }
    // TODO: If super laser selected, toggle the super laser and the menu item's display
    else if(o.equals(superLaser)){
      Settings.superLaser=!Settings.superLaser;
      if (Settings.superLaser)
        superLaser.setText("Super Laser ON");
      else  superLaser.setText("Super Laser OFF");
     
      
    }
    // TODO: If easy difficulty selected, make sure its radio button is selected, turn off the other difficulties' radio buttons,
    else if(o.equals(easyDifficulty)){
      myGameLogic.difficulty=Settings.centDelayEasy;
      mediumDifficulty.setSelected(false);
      hardDifficulty.setSelected(false);
    }
    // TODO: set the game difficulty and adjust the centipede delay accordingly.
    else if(o.equals(mediumDifficulty)){
      myGameLogic.difficulty=Settings.centDelayMedium;
      easyDifficulty.setSelected(false);
      hardDifficulty.setSelected(false);
    }
    // TODO: If medium difficulty selected, make sure its radio button is selected, turn off the other difficulties' radio buttons,
    // TODO: set the game difficulty and adjust the centipede delay accordingly.
    else if(o.equals(hardDifficulty)){
      myGameLogic.difficulty=Settings.centDelayHard;
      easyDifficulty.setSelected(false);
      mediumDifficulty.setSelected(false);
      
    }
    // TODO: If hard difficulty selected, make sure its radio button is selected, turn off the other difficulties' radio buttons,
    // TODO: set the game difficulty and adjust the centipede delay accordingly.
  }
}