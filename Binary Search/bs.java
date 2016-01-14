public class bs{
  
static int binarySearch(int[] array, int value, int left, int right) {

      if (left > right)

            return -1;

      int middle = (left + right) / 2;

      if (array[middle] == value)

            return middle;

      else if (array[middle] > value)

            return binarySearch(array, value, left, middle - 1);

      else

            return binarySearch(array, value, middle + 1, right);           

}

public static void main(String args[]){

  int [] a={12, 15, 22, 45, 19};
  
    System.out.println(binarySearch(a,45,0,4));
  
  
}


}