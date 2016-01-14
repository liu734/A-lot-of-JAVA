import java.awt.*;
import java.util.*;
import java.math.*;

/**
 * Handles most game logic
 * 
 * @author  James Wilkinson <jhwilkin@purdue.edu>
 * @version 1.7
 * @since   2011-11-20
 **/
public class GameLogic extends Thread{
  /** Set to true to synchronously reset the game **/
  public boolean syncReset;
  
  /** True if the game is over **/
  public boolean gameOverFlag;
  
  /** Current game level **/
  public int level;
  
  public int lives;
  
  /** Current game difficulty expressed as centipede delay **/
  public int difficulty;
  
  /** True if the game is paused **/
  public boolean paused;
  
  /** Current game score **/
  public int score;
  
  /** Used to change the Pause Game text in the frame **/
  public Frame myFrame;
  
  /** Keeps track of all projectiles **/
  private Point myProjectiles[];
  
  /** Used for the placement of Mushrooms during game initialization **/
  private Random generator;
  
  private GameSounds myGameSounds;
  private Centipede myCentipedes[];
  private Mushroom myMushrooms[][];
  private GameCanvas myGameCanvas;
  private Ship myShip;
  private HighScores myHighScores;    
  
  /**
   * @param inGameSounds  Called to play sound effects
   * @param inGameCanvas  Controls display of game objects
   * @param inHighScores  Called to add to high scores
   * @param inMushrooms   Array of mushrooms
   * @param inCentipedes  Array of centipedes
   * @param inShip        Player's ship
   * @param inProjectiles Array of projectiles
   **/
  public GameLogic(GameSounds inGameSounds, GameCanvas inGameCanvas, HighScores inHighScores, Mushroom inMushrooms[][], Centipede inCentipedes[], Ship inShip, Point inProjectiles[]){
    
    
    myGameCanvas=inGameCanvas;
    // TODO: Initialize the following with the passed parameters:
    // TODO: myGameSounds, myHighScores, myMushrooms, myCentipedes
    myGameSounds=inGameSounds;
    myHighScores=inHighScores; 
    myMushrooms=inMushrooms;
    myCentipedes=inCentipedes;
    // TODO: myProjectiles, myShip
    myProjectiles=inProjectiles;
    myShip=inShip;
    
    // TODO: Create a random generator object named generator
    generator= new Random();
    syncReset=false;
    // TODO: Initialize syncReset to false
    difficulty=Settings.startDifficulty;
    // TODO: Initialize the difficulty to Settings.startDifficulty
  }
  
  /**
   * Starts the GameLogic thread
   **/
  public void run(){
    
    // This sets up double buffering to smooth out the display
    long timepoint= System.currentTimeMillis();
    long timepoint2=System.currentTimeMillis();
    myGameCanvas.c.createBufferStrategy(2);
    
    
    
    // The game logic will loop as long as the program is running
    while(true){
      if(syncReset==true){
        initGame();
        syncReset=false;
      }          
      // TODO: This should update the positions and status of Centipedes, projectiles,
      
      
      
      // TODO: mushrooms and the ship.  The loop should allow for synchronous resetting
      
      
      
      
      moveShip();
      if(myShip.invulnerableTime != 0) {  //invulnerableTime
        myShip.invulnerableTime --;
      }
      
      if(myShip.firing){
      
      if ((System.currentTimeMillis() - timepoint2>= Settings.superLaserDelay)){ //switch to the delay of lazer and fire
        timepoint2= System.currentTimeMillis();
        myShip.fire(); //fire the laser
      }     
      }
      
      moveProjectiles();
      
      // TODO: triggered through a Boolean.  It should also not process any game logic
      if(!paused){
        if (System.currentTimeMillis() - timepoint>= difficulty){
          timepoint = System.currentTimeMillis();
          moveCentipedes();
        }
      }

      
      // TODO: while the game is paused.  Game over and level up conditions will be
      // TODO: determined in this loop.
      
      //Update the canvas
      myGameCanvas.drawFrame();
    }
  }
  
  /**
   * Initialize a new game
   **/
  public void initGame(){        
    // TODO: Initialize the difficulty, level and lives to match the corresponding
    difficulty=Settings.startDifficulty;
    level=Settings.startLevel;
    myShip.lives=Settings.startLives ;
    
    
    // TODO: values in Settings.
    
    // TODO: Initialize myShip.invulnerableTime and score to 0.
    myShip.invulnerableTime=0;
    score=0;
    
    // TODO: Remove any leftover centipedes
    for(int i=0; i<Settings.centipedeStartSize;i++){
      myCentipedes[i]=null;
    }
    
    
    // TODO: Remove any leftover projectiles
    for(int i=0; i<Settings.maxProjectiles;i++){
      myProjectiles[i]=null;
    }
    
    
    // TODO: Create a single centipede of Settings.centipedeStartSize segments heading
    
    myCentipedes[0] = new Centipede(Settings.centipedeStartSize,Settings.RIGHT,Settings.DOWN);
    myCentipedes[0].segments=new Point[Settings.centipedeStartSize];
    for(int i=0;i<Settings.centipedeStartSize;i++){ 
      myCentipedes[0].segments[i]=new Point(0,0);
    }
    
    
    
    // TODO: right and down.  Be sure to initialize all the segments of the Centipede.
    
    // TODO: Create array of mushrooms and randomly place them on the game grid.
    for(int i=0;i<Settings.width;i++)
      for(int j=0;j<Settings.height;j++){
      myMushrooms[i][j]=null;
    }
    
    int count=0;
    while(count<20){
      int x=generator.nextInt(Settings.width);
      int y=generator.nextInt(Settings.height-2);
      
      myMushrooms[x][y]=new Mushroom(new Point(x,y),Settings.shroomStartHealth);
      count++;
    }
    // TODO: Make sure that mushrooms do not overlap and keep them off of the 
    // TODO: bottom row.
    gameOverFlag=false;        
    // TODO: Set the gameOverFlag to false and play the newGame sound
    myGameSounds.newGame();
  }
  
  /**
   * Returns sum corresponding to overlapped objects
   * 
   * @param loc Unscaled grid coordinate
   * @return Each potentially overlapping object has a value that is a power of two
   * These values are summed up and then returned to the calling method.
   * The calling method can then run a modulus to find exactly what was impacted.
   * Refer to Settings.java for the return values.
   **/
  private int overlap(Point loc){
    int result = Settings.NONE;
    Point pixelToGridShip = null; //converted grid coordinates of the ship 
    Point pixelToGridProjectiles = null; //converted grid coordinates of the projectiles 
    
    // TODO: Check all segments of all centipedes to see if they contain the Point loc.
    for(int i = 0; i < myCentipedes.length; i++) { //loop to check all the segments of a particular centipede
      if(myCentipedes[i] != null //make sure that the particular centipede exists 
           && myCentipedes[i].length != 0) { //make sure the particular centipede has at least a segment
        if(myCentipedes[i].contains(loc) != -1) { //make sure that the particular centipede contains the Point loc.
          result += Settings.CENT;
          break; //terminate the loop; note: we use nested if loop so that the inner loop can be broken without the affecting the outer loop 
        }
      }
    }
    
    // TODO: Check all mushrooms to see if they contain the Point loc.
    if (((loc.x < myMushrooms.length) && (loc.x >= 0)) && //first we test whether x-coordinate of the Point loc is within the "boundary" of a mushroom 
        //(a mushroom (of grid coordinate) is greater than a Point loc (of pixel coordinate))
        // boundary is from (inclusive) 0 to (exclusive) dimension of the mushroom with respect to x-axis
        ((loc.y < myMushrooms[loc.x].length) && (loc.y >= 0))) { //then we test the y-coordinate of the Point loc
      if(myMushrooms[loc.x][loc.y] != null){ //if a mushroom exists in the location of the Point loc, the result will be added
        result += Settings.SHROOM;
      }
    }
    
    // TODO: Check to see if the ship occupies the Point loc.
    int pixelToGridShipX = (int) Math.floor(myShip.loc.x / 20.0); //x-coordinate of the converted grid coordinate of a ship; 
    //parsing and usage of Math.floor are essential as we want an accurate integer coordinate
    int pixelToGridShipY = (int) Math.floor(myShip.loc.y / 20.0); //y-coordinate of the converted grid coordinate of a ship
    
    pixelToGridShip = new Point(pixelToGridShipX, pixelToGridShipY);
    
    if(loc.equals(pixelToGridShip)) { //if the Point loc has the same location with the converted grid coordinate of the ship, the result will be added
      result += Settings.SHIP;
    }
    
    
    // TODO: Check all projectiles to see if they contain the Point loc.
    int pixelToGridProjectilesX = 0; //x-coordinate of the converted grid coordinate of a projectile
    int pixelToGridProjectilesY = 0; //y-coordinate of the converted grid coordinate of a projectile
    
    for (int i = 0; i < myProjectiles.length; i++) { //number of loops is based on number of projectiles 
      if(myProjectiles[i] != null) { 
        pixelToGridProjectilesX = (int) Math.floor(myProjectiles[i].x / 20.0); //parsing and Math.floor() are essential as we want an accurate integer coordinate
        pixelToGridProjectilesY = (int) Math.floor(myProjectiles[i].y / 20.0);
        pixelToGridProjectiles = new Point(pixelToGridProjectilesX, pixelToGridProjectilesY);
      }
      
      if(loc.equals(pixelToGridProjectiles)) { //if the Point loc has the same location with the converted grid coordinate of the projectile, the result will be added
        result += Settings.PROJECTILE;
        break; //the loop is stop when we found the Point loc
      }
    }
    
    // TODO: Check to see if the point has impacted or crossed beyond a wall.
    if(loc.x < 0 || loc.y < 0 || loc.x > 29 || loc.y > 29) { //the point will impact the wall when its loc.x and loc.y are lesser than 0 or greater than 29 (both valid for 0 to 29 only)
      result += Settings.WALL;
    }
    
    return result;
    
  }
  
  /**
   * Moves the centipedes to follow the segment ahead of it
   * 
   * Centipedes will try to move its head to the position directly in front of it.
   * It will then have all following segments change its position to that of the segment
   * that was ahead of it.  If the head cannot move to its desired position, then it will
   * move vertically and reverse its horizontal direction.  If at the top/bottom of the screen
   * then reverse the vertical direction as well.  A centipede can share space with a mushroom
   * but that mimics the behavior of the original game and is intended.
   **/
  private void moveCentipedes(){
    // TODO: Loop through all centipedes and have them move according to the
    // TODO: project specifications.
    
    
    
    for(int i = 0; i < myCentipedes.length; i++){    
      
      if (myCentipedes[i] != null){
        
        Point  head = new Point(myCentipedes[i].segments[0].x, myCentipedes[i].segments[0].y); //current head 
        Point  next =  new Point(myCentipedes[i].segments[0].x + myCentipedes[i].horizontal, myCentipedes[i].segments[0].y); //next step the centi will reach

        
        int lap = overlap(next);// test if next step is blocked
        
        
        if ((lap%Settings.CENT==Settings.SHROOM)||(lap%Settings.PROJECTILE==Settings.WALL)){
          
          
          if (head.y == 0){ //if block then trun down or up
            myCentipedes[i].vertical = Settings.DOWN;
          }
          
          if (head.y == Settings.height - 1){
            myCentipedes[i].vertical = Settings.UP;
          } 
          next.x = head.x; //then move one grid
          next.y = head.y + myCentipedes[i].vertical; 
          
          myCentipedes[i].horizontal = -1*myCentipedes[i].horizontal; //change to counter direction
        }
        Point temp[]=new Point[myCentipedes[i].length]; 
        
        
        
        for(int j=0;j<myCentipedes[i].length-1;j++){ //store the position of all segments 
          temp[j]=myCentipedes[i].segments[j];
        }
        
        
        
        for(int j=0;j<myCentipedes[i].length-1;j++){  //store the position of all segments follow the last  segments
          myCentipedes[i].segments[j+1]=temp[j];                  
        }
        myCentipedes[i].segments[0]=next;
        
      }
    }
  }
  
  /**
   * Tries to move the ship according to its tryLoc provided by the GameCanvas
   * 
   * If the ship tries to move into a mushroom, its location will be set to a distance away
   * from the mushroom equal to the game's scaling factor (Settings.scale).  A ship can slip
   * between two diagonal mushrooms because of this.  If the ship tries to move into a centipede
   * then it will lose a life, blow up and become invulnerable for Settings.invulnerableTime ms.
   * If the ship loses its last life, then the game is over.
   **/
  private void moveShip(){
    //declarations and initializations
    double CenterShipX = 0; //pixel x-coordinate of center of ship 
    double CenterShipY = 0; //pixel y-coordinate of center of ship
    double tryLocX = myShip.tryLoc.x;
    double tryLocY = myShip.tryLoc.y;
    double CenterCentSegmentX = 0; //pixel x-coordinate of center of centipede's segment 
    double CenterCentSegmentY = 0; //pixel y-coordinate of center of centipede's segment
    double distanceX = 0; //x-coordinate distance between center of ship and center of mushrooms
    double distanceY = 0; //y-coordinate distance between center of ship and center of mushrooms
    double distance = 0; //exact distance between center of ship and center of mushrooms
    
    int pixelToGridShipX = 0; //a variable for converting the ship's pixel x-coordinate to grid x-coordinate
    int pixelToGridShipY = 0; //a variable for converting the ship's pixel y-coordinate to grid y-coordinate
    Point pixelToGridShip = null; //exact conversion of pixel coordinate to grid coordinate
    
    int posX = 0; //x-coordinate position of the ship with respect to the position of the mushroom
    int posY = 0; //y-coordinate position of the ship with respect to the position of the mushroom
    double CenterMushX = 0; //pixel x-coordinate of center of mushroom
    double CenterMushY = 0; //pixel y-coorindate of center of mushroom
    
    double GridToPixelCentSegmentX = 0; //convert the grid x-coordinate of center of segment of centipede to pixel x-coordinate
    double GridToPixelCentSegmentY = 0; //convert the grid y-coordinate of center of segment of centipede to pixel y-coordinate
    
    double GridToPixelMushroomX = 0; //convert the grid x-coordinate of center of mushroom to pixel x-coordinate
    double GridToPixelMushroomY = 0; //convert the grid y-coordinate of center of mushroom to pixel y-coordinate
    
    CenterShipX = tryLocX + 10.0; //plus half of the length of each grid (i.e. 10.0 out of 20.0) to tryLocX to find the x-coordinate of the pixel of the ship's center
    CenterShipY = tryLocY + 10.0; //plus half of the length of each grid (i.e. 10.0 out of 20.0) to tryLocX to find the y-coordinate of the pixel of the ship's center
    
    
    for(int i = 0; i < myCentipedes.length; i++) { //check if the ship collides with a centipede
      if(myCentipedes[i] != null) { //check if the particular centipede exists or not
        for(int j = 0; j < myCentipedes[i].length; j++) { //check if the ship collides with each centipede
          
          //find the center of each segment of the centipede
          GridToPixelCentSegmentX = myCentipedes[i].segments[j].x * 20.0;
          CenterCentSegmentX = GridToPixelCentSegmentX + 10.0; //plus half of the length of each grid to GridToPixelCentSegmentX to find the x-coordinate of the pixel of the center of the centipede's segment 
          GridToPixelCentSegmentY = myCentipedes[i].segments[j].y * 20.0;
          CenterCentSegmentY = GridToPixelCentSegmentY + 10.0; //plus half of the length of each grid to GridToPixelCentSegmentY to find the y-coordinate of the pixel of the center of the centipede's segment 
          
          //calculate the distance between center of the ship and center of the segments of the centipedes
          distanceX = (CenterShipX - CenterCentSegmentX) * (CenterShipX - CenterCentSegmentX);
          distanceY = (CenterShipY - CenterCentSegmentY) * (CenterShipY - CenterCentSegmentY);
          distance = Math.sqrt(distanceX + distanceY);
          
          // TODO: Try moving the ship based on the mouse position.  First test to see if the ship has impacted any
          // TODO: Centipedes.  If it has, play the explosion sound, decrement the number
          // TODO: of lives and set the ship's invulnerableTime to Settings.invulnerableTime.
          if(distance < 20.0 && 
             myShip.invulnerableTime == 0) { //to make sure that the ship is vulnerable at that moment
            myGameSounds.shipExplode();
            myShip.lives -= 1;
            myShip.invulnerableTime = Settings.invulnerableTime;
            
            // TOOD: If the ship has run out of lives, run the gameOver() method.
            if(myShip.lives < 0) {
              gameOver();
            }
          }
        }
      }
    }
    //convert the pixel coordinates of the ship to the grid coordinates
    pixelToGridShipX = (int) Math.floor(CenterShipX / 20.0); //parsing and math.floor() are necessary to obtain accurate grid coordinates
    pixelToGridShipY = (int) Math.floor(CenterShipY / 20.0);
    pixelToGridShip = new Point(pixelToGridShipX,  pixelToGridShipY);
    
    // TODO: Check if the ship is trying to move into a mushroom.  The ship should not
    // TODO: lose a life from this, but it should be blocked from moving.
    
    int k = 0; //counter
    int l = 0; //another counter
    
    //resetting the position of the ship with respect to the position of the mushroom
    while(k <= 2) {
      while(l <= 2) {
        posX = pixelToGridShip.x + k; 
        posY = pixelToGridShip.y + l;
        k++; //increase k as loop goes to get another new position for x-coordinate
        l++; //increase l as loop goes to get another new position for y-coordinate
        
        if((posX >= 0 && posX < myMushrooms.length) && //make sure that the new position is within the "boundary" of x-coordinate dimension of the mushrooms
           (posY >= 0 && posY < myMushrooms[posX].length) && //make sure that the new position is within the "boundary" of y-coordinate dimension of the mushrooms
           !(myMushrooms[posX][posY] == null) && //make sure that the mushroom exists
           myMushrooms[posX][posY].loc.y > 24) { //24 is obtained by subtracting Settings.shipVerticalRange(4) and 2 from Settings.height(30);
          //the position of the mushrooms must be greater than the amount
          
          //find the center of each mushroom
          GridToPixelMushroomX = myMushrooms[posX][posY].loc.x * 20.0;
          CenterMushX =  GridToPixelMushroomX + 10.0; //plus half of the length of each grid to CenterMushX to find the x-coordinate of the pixel of the mushroom's center
          GridToPixelMushroomY = myMushrooms[posX][posY].loc.y * 20.0;
          CenterMushY =  GridToPixelMushroomY + 10.0; //plus half of the length of each grid to CenterMushY to find the y-coordinate of the pixel of the mushroom's center 
          
          //calculate the distance between center of the ship and center of the mushrooms
          distanceX = CenterShipX - CenterMushX;
          distanceY = CenterShipY - CenterMushY;
          distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
          
          //make sure that the distance between center of the ship and center of the mushroom to be 20.0 all the time
          if(distance == 0) { //if distance is 0, simply add 20.0 to the center of the mushroom
            CenterShipX = CenterMushX + 20.0;
            CenterShipY = CenterMushY + 20.0;
          }
          else if(distance < 20.0) { //make sure that the center of the ship is adjusted accordingly to the distance between the center of the ship and the center of the mushroom
            CenterShipX = CenterMushX + (distanceX / distance) * 20.0;
            CenterShipY = CenterMushY + (distanceY / distance) * 20.0;
          }
        }
      }
    }
    
    // TODO: Set the ship's new location based on the results of the tests above.
    myShip.loc.x = (int) Math.round(CenterShipX - 10.0); //parsing and round off are necessary as we want the accurate location for the ship
    myShip.loc.y = (int) Math.round(CenterShipY - 10.0);
  }
  /**
   * Moves the projectiles and checks for collision
   * 
   * Tries to move the projectiles vertically by one pixel.  If the destination overlaps something else,
   * then remove the projectile and react appropriately to the impacted object.
   **/
  private void moveProjectiles(){
    // TODO: Create a loop to move all projectiles.
    
    for(int i=0; i< Settings.maxProjectiles;i++ ){  
      if(myProjectiles[i] != null) {  
        Point next=new Point((int) (myProjectiles[i].x/Settings.scale),((int) (myProjectiles[i].y/Settings.scale))-1); // the next region the projectiles will reach
        int lap=overlap(next); // check if this region is blocked
        if(lap%(Settings.CENT*2) >= Settings.CENT){ //if centipedes
          
          myProjectiles[i]=null; //null the projectile
          
          if (Settings.superLaser){ //score depends on the superlaser on or off, difficulty and level
            
            score = score+ (int) (0.3*2*((125-difficulty)/3+ 10*Settings.levelFactor*level));
          }else{
            
            score =  score + (int) (2*(125-difficulty)/3+ 10*Settings.levelFactor*level);
          }
                    
          for(int j = 0; j < myCentipedes.length; j++){ //check hit the which centipede
            if (myCentipedes[j] !=null){ //if ths Centi is not null 
              if (myCentipedes[j].length != 0){ // is not length =0
      
              if((myCentipedes[j].contains(next)!=-1) && (myCentipedes[j].contains(next) < myCentipedes[j].length - 1)){ //check in which centipede
                
                
                for (int a = 0; a< myCentipedes.length; a++){ // which part of the centipede has been hitted
                  
                  
                  if (myCentipedes[a] == null){//check where array still have space to store new head and segment
                    
                   Point head = new Point(myCentipedes[j].segments[myCentipedes[j].contains(next)+1].x, myCentipedes[j].segments[myCentipedes[j].contains(next)+1].y); // get new head
          
                    myCentipedes[a] = new Centipede(myCentipedes[j].length - myCentipedes[j].contains(next) - 1, myCentipedes[j].horizontal,  myCentipedes[j].vertical); //store it in array
                    
                    for (int b = 0; b <myCentipedes[j].length - myCentipedes[j].contains(next) - 1; b++){ //split the orignal centi to two new centis
                      myCentipedes[a].segments[b] = new Point(myCentipedes[j].segments[myCentipedes[j].contains(next)+1+b].x, myCentipedes[j].segments[myCentipedes[j].contains(next)+b+1].y);
                    }
                    
                    
                    
                    break; //break the loop when get new one
                  }
                }
                
                
              }
              
              if((myCentipedes[j].contains(next)!=-1)){ //mushroom part
                
                Point shroom = new Point(myCentipedes[j].segments[myCentipedes[j].contains(next)].x, myCentipedes[j].segments[myCentipedes[j].contains(next)].y);//the position where centi get hit
                
                myMushrooms[shroom.x][shroom.y] = new Mushroom(shroom, 3); // place new mushroom here                
                myCentipedes[j].length = myCentipedes[j].contains(next); //length depends on contains
                
                
                myGameSounds.centDie();//sound
                
                int c; //check if level up part
                for (c=0; c<Settings.centipedeStartSize;c++){ //checak all Centipedes
                  if (myCentipedes[c]!=null&&(myCentipedes[c].length != 0)){ //if still have one, then break
                    
                    break;
                  }
                  
                  if (c==Settings.centipedeStartSize-1){ //if user killed all Centi
                    
                    level++;      //level up
                    myGameSounds.nextLevel();//sound
                    for(int f=0;f<Settings.centipedeStartSize;f++){ //clear the array
                      myCentipedes[f]=null;
                    }
                    
                    
                    myCentipedes[0] = new Centipede(Settings.centipedeStartSize,Settings.RIGHT,Settings.DOWN); //create new centiped again
                    myCentipedes[0].segments=new Point[Settings.centipedeStartSize];                  
                    for(int e=0;e<Settings.centipedeStartSize;e++){ 
                      myCentipedes[0].segments[e]=new Point(0,0);
                    }
                    
                    break; //break when level up
                  }  
                }
              }
            }
          }      
          }
        }
        
        
        else if ((lap%(Settings.SHROOM*2) == Settings.SHROOM)){ //if hit a mashroom
          myProjectiles[i]=null;
          if(myMushrooms[next.x][next.y].getHealth()>1){
            
            myMushrooms[next.x][next.y]=new Mushroom(next,myMushrooms[next.x][next.y].getHealth()-1); //health-1
            
          }
          
          else {
            myMushrooms[next.x][next.y]=null; //if the heal  of the mushroom=0 then remove if
          }
        }
        
        else if (lap%(Settings.WALL*2) == Settings.WALL){ //hit a wall , remove the projetiles too
          myProjectiles[i]=null;
        }
        
        
        else {myProjectiles[i].y--;}//move the projectile once a time if hit nothing
        
      }
      
      
      
    }
    // TODO: If a projectile impacts a centipede, mushroom or wall, the projectile should be removed.
    
    // TODO: If the projectile impacted a Centipede, increase the score proportionately
    // TODO: to the difficulty and whether the user is using a superLaser or not.
    // TODO: The impacted Centipede segment will need to be removed and depending on
    // TODO: where the Centipede was impacted, it may need to split into two Centipedes.
    // TODO: Play the centDie sound.
    // TODO: Place a new Mushroom at the location where the Centipede was hit.
    
    // TODO: If the projectile impacted a Mushroom, decrement the Mushroom's health.
    // TODO: If the Mushroom's health is 0, remove the Mushroom.
    // TODO: Play the shroomHit sound.
    // TODO: Create a loop to move all projectiles.
    
    
    
  }
  
  /**
   * Flips gameOverFlag to true, plays the game over sound and tries to add a high score
   **/
  public void gameOver(){
    // TODO: Set gameOverFlag to true, stop the ship from firing, play the gameOver sound
    // TODO: and try adding the score to the high scores.
    
    gameOverFlag = true;
    myShip.firing = false;
    myGameSounds.gameOver();
    myHighScores.addHighScore(score);
  }
}