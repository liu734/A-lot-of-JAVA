package cs.purdue;

public class Lab11 extends Thread{
 
 static int x,y,width,height;
 
 //used for grading to make sure current application is by students demoing the application
 static String user_name = "Jingye Liu & Pan, Yiyang";
 
 public void run(){
   int tx, ty;
   
  

   while (true){
   //x=x+2;
   //y=y+2;
   
   tx=x;
   ty=y;
   
   tx+= (int )  Accelerometer.x;
   ty+= (int ) Accelerometer.y;
   
   if (tx>=width)
     tx=0;
   if (ty>=height)
     ty=0;
   if (tx<=0)
     tx=width;
   if (ty<=0)
     ty=height;
   
   x=tx;
   y=ty;

   
   try {sleep(2);}
   catch(Exception e){}
   
   }
   
  
 }
 
 public static void main(String[] args){
   Lab11 l= new Lab11();
   l.start();

 }

}

