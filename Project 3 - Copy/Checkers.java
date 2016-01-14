import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.applet.*;

public class Checkers extends JApplet implements ActionListener, MouseListener{
 
  Board b; 
  JFrame f;
  JPanel p;
  JButton button; 
  boolean turn;
  int startX=-1;
  int startY=-1;
  int startC=-1;

 public Checkers(){
   newGame();
 }

 public void createButton(){
   button = new JButton("New Game");
   button.addActionListener(this); 
 }
 
 
 public void movePiece(int x,int y,int c,boolean turn1){
   if (c==Board.HIGHLIGHT && (startX-x)/2==0 && (startY-y)/2==0){
 
     removeHighlights();
     b.setState(x,y,startC);
     b.setState(startX,startY,b.EMPTY);
     turn=!turn1;
 }


 }
 public void jump(int x,int y,int c,boolean turn1){
   if (c==Board.HIGHLIGHT && (startX-x)/2!=0 && (startY-y)/2!=0){
     b.setState(x,y,startC);
     b.setState(startX,startY,b.EMPTY);
     b.setState((startX+x)/2,(startY+y)/2,b.EMPTY);
     turn=!turn1;
   }
 }

 public void highlightLeft(int x,int y,int c,boolean turn){

   if (c==b.BLACK && x-1>=0 && y+1<=7 && b.getState(x-1,y+1)==b.EMPTY && turn==false){
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
 
 public void hightlightRight(int x,int y,int c,boolean turn){
   if(c==b.BLACK && x+1<=7 && y+1<=7 && b.getState(x+1,y+1)==b.EMPTY && turn==false){
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
 }  b.addMouseListener(this); 
 

 public void removeHighlights(){
   for (int i=0;i<=7;i++)
     for (int j=0;j<=7;j++) {
       if(b.getState(i,j)==b.HIGHLIGHT)
         b.setState(i,j,0);   
   }
 }
 
 public void newGame(){
   f= new JFrame ();
   p=new JPanel(new BorderLayout());
   b= new Board();
   turn=true;

   f.setSize(500,500);

 
   for(int i = 0; i <= 2; i++) {
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
    
   for(int l = 5; l <= 7; l++) {
     for(int k = 0; k <= 7; ) {
       if(l == 6) {
         b.setState(k + 1, l, Board.RED);
         k = k + 2;
         continue;
       }
       b.setState(k, l, Board.RED);
       k = k + 2;
     }
   }
   createButton();
  
   

   b.addMouseListener(this); 
   p.add(b,BorderLayout.CENTER);

   p.add(button, BorderLayout.SOUTH);
   
   f.add(p);
   f.setVisible(true);
 }

 public void mousePressed(MouseEvent e){
   int x=e.getX()/50;
   int y=e.getY()/50;
   int c=b.getState(x,y);
   removeHighlights();
 
   if (c==Board.BLACK||c==Board.RED){
     highlightLeft(x,y,c,turn); 
     hightlightRight(x,y,c,turn);
     startX=x;
     startY=y;
     startC=c;
   }

   movePiece(x,y,c,turn);
   jump(x,y,c,turn);
 
   b.repaint();
 }

 public void actionPerformed(ActionEvent e){
  f.dispose();
  newGame();
  }	
  
 public void mouseEntered(MouseEvent e){}
 public void mouseExited(MouseEvent e){}
 public void mouseReleased(MouseEvent e){}
 public void mouseClicked(MouseEvent e){}

 public static void main(String args[]){
  new Checkers();

 }
}