public class GCDAndBinarySearchMethods{
    
    public int RGCD(int x, int y){
        if(y==0)
            return x;
        else{
            return(RGCD(y, x%y));
        }
    }
    
     public int IGCD(int x, int y){
         if(y==0|| x==0)
             return x+y;
         else{
             while(x!=y){
             if(x<y)
                 y=y-x;
             else
                 x=x-y;
         }
         return x;
         }
    }
    
     
     public  boolean bSearch(int [] a, int x){
         int length=a.length; // Number of elements in the area to be searched
         int mid=length/2;
         if(length==0)
             return false;
         if(length==1){
             if( x==a[0]){
                 return true;
             }else
                 return false;
         }
         
         if(x==a[mid])
             return true;
         else{
             if(x<a[mid]){
                 int [] b=new int [mid];
                 for(int i=0; i<mid; i++){
                     b[i]=a[i];
                 }
                 return (bSearch(b, x)); // Left portion search.
             }else{
                 int [] b=new int [mid];
                 int j=0;
                 for(int i=mid+1; i<length; i++){
                     b[j]=a[i];
                     j++;
                 }
                 return (bSearch(b, x));
                         
                         }// End of right portion search
                 
             }
             
         }// End of bSearch()
     
     public static void main(String [] args){
        GCDAndBinarySearchMethods g=new GCDAndBinarySearchMethods();
         int x=1989, y=867; // Find GCD of these two ints.
         System.out.println("RGCD of "+x+" "+y +" is "+g.RGCD(x, y)); // Recursive method
         System.out.println("(I)GCD of "+x+" "+y +" is "+g.IGCD(x, y)); // Iterative method
         
         int [] a={12, 15, 22, 45}; // Array forbinary search
         System.out.println(g.bSearch(a, 45)); // Search test
     }
     
}