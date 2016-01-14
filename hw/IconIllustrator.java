import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.applet.*;
import java.net.URL;

public class IconIllustrator implements MouseListener {
  JFrame iconFrame = new JFrame("Icon Illustrator");
  JButton sableCollie;
  URL sableCollieURL = new URL("file:bark.au");
  AudioClip barkClip = Applet.newAudioClip(sableCollieURL);

  public IconIllustrator() throws Exception{
    ImageIcon sableCollieIcon = new ImageIcon("sableCollie.jpg");
    sableCollie = new JButton(sableCollieIcon);
    iconFrame.add(sableCollie);
    iconFrame.setSize(175, 150);
    iconFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    iconFrame.setVisible(true);
    
    sableCollie.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        barkClip.play();
      }
    });
    
    sableCollie.addMouseListener(this);                                
  }

  public void mouseEntered(MouseEvent e) {
    barkClip.play();
  }
  
  public void mouseClicked(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {} 
  
  public static void main(String[] args) throws Exception{
    new IconIllustrator();
  }
}