/**
*Simple Checkers Applet

*Create a simple two-player checkers applet. 
*The applet will have simple functionality. Not all standard
*rules of the game need to be implemented. The following rules are the only rules required of the applet.
*
*@author Jingye Liu, Wei Haow Tan, Jun Xiang Tee
*
*@CSlogin  liu734, tan85, jtee
*
*@Recitation RM3, RM6, R3
*
*@data 23 Oct 2011 
*
*@C_data 28 Oct 2011
*
**/

import javax.swing.*;     //import the packages we need
import java.awt.event.*;
import java.awt.*;
import java.applet.*;

public class Checkers extends JApplet implements ActionListener, MouseListener{
 
  Board b;   
  JFrame f;  
  JPanel p;
  JButton button; 
  boolean turn;    //the boolean used to detemine whose turn
  JLabel t,t2,t3;  //three Labels
  int startX=-1;   // integer x and y to store the postion of last pressed checkers
  int startY=-1;
  int startC=-1;   // interger c to store the color of last pressed checkers
  int countBlack=12;  //the number of pieces player2 have
  int countRed=12; //the number of pieces player1 have 

 public Checkers(){  //constructor 
   newGame();
 }

 public void createButton(){ //initiate the button
   button = new JButton("New Game");
   button.addActionListener(this);  // add listener
 }
 
 
 public void movePiece(int x,int y,int c,boolean turn1){ //function to move piece
   if (c==Board.HIGHLIGHT && (startX-x)/2==0 && (startY-y)/2==0){ // the condition to detemine if the posititon where piece can move 
 
     removeHighlights(); // remove the high lights on board
     b.setState(x,y,startC); // add the piece to new postition
     b.setState(startX,startY,b.EMPTY); // remove the piece in last position
     turn=!turn1; //change turn
	 if (turn1==true) //set the label show turn
	 {
	    t3.setText("Player 2's Turn"); 
	 }
	  else{t3.setText("Player 1's Turn");}
 }


 }
 public void jump(int x,int y,int c,boolean turn1){ //function to jump
   if (c==Board.HIGHLIGHT && (startX-x)/2!=0 && (startY-y)/2!=0){ // the condition to detemine if the posititon where piece can jump
     b.setState(x,y,startC);  //move to new postion
     b.setState(startX,startY,b.EMPTY); // remove piece in last postiton 
     b.setState((startX+x)/2,(startY+y)/2,b.EMPTY); // eat the piece that is jumped over
     turn=!turn1; //change turn
	if (turn){ // count the remain checkers and set turn label
	countRed=countRed-1; 
	t3.setText("Player 1's Turn"); 
	}
	else{
	countBlack=countBlack-1;
	t3.setText("Player 2's Turn");
	}
	t2.setText("Player 1 has "+countRed+" pieces. Player 2 has "+countBlack+" pieces.");
	
  }
 
 }

 public void highlightLeft(int x,int y,int c,boolean turn){ //highlight left

   if (c==b.BLACK && x-1>=0 && y+1<=7 && b.getState(x-1,y+1)==b.EMPTY && turn==false){ //determin the condition position the checher pressed can move to eg. black can only move down, diagonally  only can be moved in player 2's turn
     b.setState(x-1,y+1,Board.HIGHLIGHT); 
   }
   
   else if (c==b.BLACK && x-2>=0 && y+2<=7 && b.getState(x-1,y+1)==b.RED && b.getState(x-2,y+2)==b.EMPTY && turn==false){
     b.setState(x-2,y+2,Board.HIGHLIGHT);
   }
   
   else if (c==b.RED && x-1>=0 && y-1>=0 && b.getState(x-1,y-1)==b.EMPTY && turn==true){
     b.setState(x-1,y-1,Board.HIGHLIGHT); 
   }
   
   else if (c==b.RED && x-2>=0 && y-2>=0 && b.getState(x-1,y-1)==b.BLACK &&  b.getState(x-2,y-2)==b.EMPTY  && turn==true){
     b.setState(x-2,y-2,Board.HIGHLIGHT);
   }   
 } 
 
 public void hightlightRight(int x,int y,int c,boolean turn){//highlight right
   if(c==b.BLACK && x+1<=7 && y+1<=7 && b.getState(x+1,y+1)==b.EMPTY && turn==false){ //determin the condition position the checher pressed can move to eg. black can only move down, diagonally  only can be moved in player 2's turn
     b.setState(x+1,y+1,Board.HIGHLIGHT);
   }
    
   else if(c==b.BLACK && x+2<=7 && y+2<=7 && b.getState(x+1,y+1)==b.RED && b.getState(x+2,y+2)==b.EMPTY && turn==false){
     b.setState(x+2,y+2,Board.HIGHLIGHT);
   }
    
   else if (c==b.RED && x+1<=7 && y-1>=0 && b.getState(x+1,y-1)==b.EMPTY && turn==true){
     b.setState(x+1,y-1,Board.HIGHLIGHT);
   }
   
   else if (c==b.RED && x+2<=7 && y-2>=0 && b.getState(x+1,y-1)==b.BLACK && b.getState(x+2,y-2)==b.EMPTY && turn==true){
     b.setState(x+2,y-2,Board.HIGHLIGHT);
   }     
 }  
 

 public void removeHighlights(){ // function to clear the highlight 
   for (int i=0;i<=7;i++)  //for loop to clear whole hightlight exists
     for (int j=0;j<=7;j++) {
       if(b.getState(i,j)==b.HIGHLIGHT)
         b.setState(i,j,0);   
   }
 }
 
 public void newGame(){ // initiate a new game
   f= new JFrame (); // initiate the frame
   b= new Board();  //set up board
    p=new JPanel(new BorderLayout()); // a new panel
   turn=true; // player 1's turn 
   countRed=12; //player 1 remain 12 checkers 
   countBlack=12; //player 2 remain 12 checkers 
   f.setSize(500,600); // set the size of frame
  
 
   for(int i = 0; i <= 2; i++) { // for loop to place the black checkers
     for(int j = 1; j <= 7; ) {
       if(i == 1) {
         b.setState(j - 1, i, Board.BLACK);
         j = j + 2;
         continue;
       }
       b.setState(j, i, Board.BLACK);
       j = j + 2;
     }
   }
    
   for(int l = 5; l <= 7; l++) { // for loop to palce the red checkers
     for(int k = 0; k <= 7; ) {p=new JPanel(new BorderLayout());
       if(l == 6) {
         b.setState(k + 1, l, Board.RED);
         k = k + 2;
         continue;
       }
       b.setState(k, l, Board.RED);
       k = k + 2;
     }
   }
   createButton(); // create button
   t=new JLabel("Checkers"); //initiate checker label
   t.setFont(new Font("Courier New", Font.BOLD, 42)); // setfont
   t2=new JLabel("Player 1 has 12 pieces. Player 2 has 12 pieces."); //initiate piece label 
   t3=new JLabel("Player 1's Turn");  //initiate turn label 
   t3.setFont(new Font("Courier New", Font.BOLD, 28)); //font
   
   p.add(t3,BorderLayout.NORTH);//layout in panel
   p.add(t2,BorderLayout.SOUTH);
   p.add(b,BorderLayout.CENTER);

   f.add(button, BorderLayout.SOUTH);//layout in frame
   f.add(t,BorderLayout.NORTH);
   f.add(p,BorderLayout.CENTER);
   b.addMouseListener(this); //add mouse listener
 
   f.setVisible(true); //visible
 }

 public void mousePressed(MouseEvent e){ //mousepressed
   int x=e.getX()/50; // get the position of mouse in board
   int y=e.getY()/50;
   int c=b.getState(x,y); //get the color
   removeHighlights();
 
   if (c==Board.BLACK||c==Board.RED){ //if the position is black of red then highlight
     highlightLeft(x,y,c,turn); 
     hightlightRight(x,y,c,turn);
     startX=x; //record the position will be used later in jump or move 
     startY=y;
     startC=c;
   }

   movePiece(x,y,c,turn); // call move function
   jump(x,y,c,turn); // call jump function
 
   b.repaint(); //repaint the screen
 }

 public void actionPerformed(ActionEvent e){ //action happens will press the button
  f.dispose();  //close the frameidy up tbe chess board
  newGame(); //open a new game
 }
  
 public void mouseEntered(MouseEvent e){}
 public void mouseExited(MouseEvent e){}
 public void mouseReleased(MouseEvent e){}
 public void mouseClicked(MouseEvent e){}

 public static void main(String args[]){ //main method
  new Checkers();

 }
}