public class bs{
  
static boolean binarySearch(int[] array, int value, int left, int right) {

      if (left > right)

            return false;

      int middle = (left + right) / 2;

      if (array[middle] == value)

            return true;

      else if (array[middle] > value)

            return binarySearch(array, value, left, middle - 1);

      else

            return binarySearch(array, value, middle + 1, right);           

}

public static void main(String args[]){

  int [] a={12, 15, 22, 45, 19};
  
    System.out.println(binarySearch(a,12,0,4));
  
  
}


}