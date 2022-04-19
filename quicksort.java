import java.util.*;


public class quicksort {
    public static ArrayList<Integer> readInput(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> intArray = new ArrayList<Integer>();
        System.out.println("Please input list to be sorted");

        String input = sc.nextLine();

        String s = Character.toString(input.charAt(0));
        int count = 0;
        //parse input to populate array
        while (!s.contains("*")){
            if (s.contains(",")){
                count++;
                s = Character.toString(input.charAt(count));
                continue;
            }
            else {
                int i = Integer.parseInt(s);
                intArray.add(i);
                count++;
                s = Character.toString(input.charAt(count));
            }
        
        }
        return intArray;
    }


    public static ArrayList<Integer> qSort(ArrayList<Integer> intArray){
        int len = intArray.size();
        return null;
    }


    public static void main(String[] args) {
        ArrayList<Integer> intArray = readInput();
        System.out.println(intArray);
        System.out.println(intArray.size());
        intArray = qSort(intArray);
        //System.out.println("Sorted list: "+ intArray);
    }
    
}
