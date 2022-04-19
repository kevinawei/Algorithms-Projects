import java.util.*;
 
public class mergesort {
 
    public static ArrayList<Integer> readInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input list to be sorted");
 
        String input = sc.nextLine();
        String[] p = input.split(",");
 
        ArrayList<Integer> arr = new ArrayList<Integer>();
 
        for (int i = 0; i<p.length-1; i++){ //excludes the * that ends input
            arr.add(Integer.parseInt(p[i]));
        }
        //parsing last integer with trailing "*"
        String s = p[p.length-1];
        s = s.substring(0, s.length()-1);
        arr.add(Integer.parseInt(s));
        
        return arr;
    }
 
 
    public static void mSort(int[] Arr, int start, int end){
 
        if(start < end) { //recursive mergesort that splits array into 1 element arrays before merging everything back with merge function
            int mid = (start + end) / 2;
            mSort(Arr, start, mid);
            mSort(Arr, mid+1, end);
            merge(Arr, start, mid, end);
        }
    }
 
    public static void merge(int[] Arr, int start, int mid, int end){
        // temp array
        int temp[] = new int[end - start + 1];
 
        // counters/pointers to keep track of multiple elements of array as we traverse through
        int i = start; 
        int j = mid+1;
        // counter for position in temp array
        int k = 0;
 
        while(i <= mid && j <= end) {
 
            if(Arr[i] >= Arr[j]) { //merge function selects greater elements to be inserted into temp array first
                temp[k] = Arr[i];
                k += 1; i += 1;
            }
            else {
                temp[k] = Arr[j];
                k += 1; j += 1;
            }
        }
        while(i <= mid) {
            temp[k] = Arr[i];
            k += 1; i += 1;
        }
        while(j <= end) {
            temp[k] = Arr[j];
            k += 1; j += 1;
        }
 
        for(i = start; i <= end; i += 1) {
            Arr[i] = temp[i - start];
        }
 
    }
 
    public static int[] listToArray(ArrayList<Integer> list){
        int[] temp = new int[list.size()];
        for (int i = 0; i<list.size(); i++){
            temp[i] = list.get(i);
        }
        return temp;
    }
 
    public static void printArr(int[] arr){
        System.out.print("Sorted Array: [");
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i]);
            if (i == arr.length-1){
                continue;
            }
            else{
                System.out.print(", ");
            }
        }
        System.out.println("] ");
    }
 
    public static void main(String[] args) {
        ArrayList<Integer> array = readInput();
        System.out.println("Initial List: "+ array);
        int[] arr = listToArray(array);
 
        mSort(arr, 0, arr.length-1);
        printArr(arr);
    }
}
