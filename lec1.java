import java.util.*;
class Algo{
   public static void bubbleSort(int[] arr){
   
      //out of the loop variables
      int n = arr.length;
      boolean flag;
      
         //loops to sort
         for(int i = 0; i < n -1; i++){
            
         flag = false;
         
            for(int j = 0; j < n -i-1; j++){
               if(arr[j]>arr[j+1]){
                  int temp = arr[j];
                  arr[j] = arr[j+1];
                  arr[j+1]=temp;
                  flag = true;
               }
            }
         if (!flag) {
         break;
      }
         }
      }


public static void main (String[] args){
int[] num = {5,3,8,4,2};

System.out.println("Before running the algorthm: " + Arrays.toString(num));
bubbleSort(num);
System.out.println("After running the algorthm: " + Arrays.toString(num));
}
}