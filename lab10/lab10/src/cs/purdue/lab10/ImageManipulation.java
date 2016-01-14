package cs.purdue.lab10;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ImageManipulation {

//replace with user name for grading
public static final String user_id = "Liu Jingye";
 
 public static Bitmap greyScale(Bitmap b){
  int w=b.getWidth();
  int h=b.getHeight();
  {for (int i=0; i<w;i++)
     for (int j=0;j<h;j++)
    
    b.setPixel(i,j ,b.getPixel(i,j) );}
  
  //Todo
  return b;
 }
 
 public static Bitmap leftShift(Bitmap b){
    int w=b.getWidth();
  int h=b.getHeight();

  {for (int i=0;i<w; i++)
     for (int j=0;j<h; j++)
    b.setPixel(i,j ,b.getPixel(i,j) -50);}
//Todo

  return b;
 }
 
 public static Bitmap rightShift(Bitmap b){
    int w=b.getWidth();
  int h=b.getHeight();
  Bitmap  b2=b;
  {for (int i=0; i<w;i++)
     for (int j=0;j<h; j++)
    
    b.setPixel(i,j ,b.getPixel(i,j)+50 );}
//Todo

  return b;
 }
 
 public static Bitmap invert(Bitmap b){
  int w=b.getWidth();
  int h=b.getHeight();
  int temp;
  int temp2;
  
  for (int i=0;i<w/2; i++){
     for (int j=0;j<h; j++)
  {temp= b.getPixel(w-i-1,j) ;
      temp2= b.getPixel(i,j) ;
    b.setPixel(w-i-1,j ,temp2);
    b.setPixel(i,j,temp);
  }
  }
 
//Todo

  return b;
 }

}
