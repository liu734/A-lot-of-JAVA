/*
 * Exam 2 Practice Problem Set Solutions Problem 9.
 * Nov 9, 2011
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Problem9 implements ActionListener, MouseListener{
  
    JFrame f;
    JPanel center;
    JButton print;
    JButton[] b;
    JTextField t;
    int numButtons; // Number of buttons with numerals.
    String startText="Numerals typed are displayed here."; 
    String text=""; // To be displayed in the text field.
    
    String buttonLabel[]; // Labels for buttons
    
    
    // Constructor starts here.
  public Problem9(int n, String [] l){
      createGui(n, l);  
  }
  // Create GUI
  public void createGui(int n, String[]l){
      
      buttonLabel=l; // Save labels for access by the listeners.
      // Create components, set layouts, and add listeners..
      f=new JFrame();
      f.setLayout(new BorderLayout());
      numButtons=n*n; // Set number of buttons.
      center=new JPanel(new GridLayout(n,n));
      
      b=new JButton[n*n];
      print=new JButton("Print");
      print.addActionListener(this);
      print.addMouseListener(this);
      t=new JTextField(startText);
      f.add(t, BorderLayout.NORTH);
      for(int i=0; i<numButtons; i++){
          b[i]=new JButton(l[i]);
          center.add(b[i]);
          b[i].addActionListener(this);
          b[i].addMouseListener(this);
      }
      f.add(center, BorderLayout.CENTER);
      f.add(print, BorderLayout.SOUTH);
      f.setSize(400,400);
      f.setVisible(true);
      f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }// End of createGui()
  
  public void actionPerformed(ActionEvent e){
    
      Object o=e.getSource(); // Get the source of this action.
      
      //Check if it is one of the numeral buttons.
     boolean buttonFound=false;
     int i=0;
     while(i<numButtons&& !buttonFound){
          if(o.equals(b[i])){
              text=text+buttonLabel[i];
              t.setText(text);
              buttonFound=true;
          }
          i++;
      }// End of button check.
     
     // if none of the numeric buttons is clicked then it must be Print.
     if(!buttonFound){
         System.out.println(text);// Print text on the console.
         text="";// and reset it to empty string
         t.setText(startText);// and display on console.
     }// End of processing Print button click
         
  }// End of actionPerformed()
  

  
  public void mouseEntered(MouseEvent m){
     Object o=m.getSource(); // Get the source of this action.
      
      //Check if it is one of the numeral buttons.
     boolean buttonFound=false;
     int i=0;
     while(i<numButtons&& !buttonFound){
          if(o.equals(b[i])){
              System.out.println("Mouse enters button "+buttonLabel[i]);
              buttonFound=true;
          }
          i++;
      }// End of button check.
     
     // if none of the numeric buttons is clicked then it must be Print.
     if(!buttonFound){
          System.out.println("Mouse enters the Print button ");
     }
  }// End of mouseEntered()
  public void mouseClicked(MouseEvent m){
    
  }
  public void mousePressed(MouseEvent m){
    
  }
  public void mouseReleased(MouseEvent m){
    
  }
  public void mouseExited(MouseEvent m){
     Object o=m.getSource(); // Get the source of this action.
      
      //Check if it is one of the numeral buttons.
     boolean buttonFound=false;
     int i=0;
     while(i<numButtons&& !buttonFound){
          if(o.equals(b[i])){
              System.out.println("Mouse exits button "+buttonLabel[i]);
              buttonFound=true;
          }
          i++;
      }// End of button check.
     
     // if none of the numeric buttons is clicked then it must be Print.
     if(!buttonFound){
          System.out.println("Mouse exits the Print button ");
     }
  }
  
  public static void main(String[] arg){
     
      // Note that n and label are local to main() but 
      // are being passed to the constructor as parameters.
      int n=2; // There will be n*n buttons
      String label[]={"A", "B", "C", "D"};// Labels for buttons. There must be n*n labels.
      Problem9 p=new Problem9(n, label);
  }
  
}