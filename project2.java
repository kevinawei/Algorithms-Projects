import java.util.*;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;

class Job
{
    int id;
    int start;
    int end;

    public Job (int i, int s, int e){
        id = i; 
        start = s; 
        end = e;
    }
}

class mergesort { //Mergesort program from previous homework modified to sort Job objects by end time
                  // This is used to sort jobs by their ending time
    public static void mSort(Job[] Arr, int start, int end){
        if(start < end) { //recursive mergesort that splits array into 1 element arrays before merging everything back with merge function
            int mid = (start + end) / 2;
            mSort(Arr, start, mid);
            mSort(Arr, mid+1, end);
            merge(Arr, start, mid, end);
        }
    }
    public static void merge(Job[] Arr, int start, int mid, int end){
        // temp array
        Job temp[] = new Job[end - start + 1];
 
        // counters/pointers to keep track of multiple elements of array as we traverse through
        int i = start; 
        int j = mid+1;
        // counter for position in temp array
        int k = 0;
 
        while(i <= mid && j <= end) {
 
            if(Arr[i].end <= Arr[j].end) { //merge function selects Job with earlier end time to be inserted into temp array first
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
}

public class project2 {
    public static Job[] readFile() throws FileNotFoundException {
        File file = new File("input.txt"); 
        Scanner sc = new Scanner(file); 
        int size = sc.nextInt();
        sc.nextLine();
        Job list[] = new Job [size];
        int i = 0;
        Job j;

        while (sc.hasNextLine()) {
            String [] s = sc.nextLine().split(",");
            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);
            j = new Job(i,start,end);
            list[i] = j;
            i++;
        } 
            sc.close();
            ArrayList<Job> l = new ArrayList<Job>();
            return list;
    }

    public static void printJobs(Job[] list){
        System.out.println("Final Schedule:");
        for (int i = 0; i<list.length; i++){
            Job j = list[i];
            if (j!= null){
                System.out.print(j.id);
                if (i<list.length-2){
                    System.out.print(", ");
                }
            }
            else{
                break;
            }
        }
    }
    public static Job[] schedule(Job[] list){
        Job[] schedule = new Job[list.length];
        // First job is always included
        schedule[0] = list[0];
        Job last = list[0];
        Job next;
        int j = 1;
        for (int i =1 ; i<list.length; i++){    
            next = list[i];      
           // System.out.println("Next jobs starts at : "+next.start+" Current job ends at: "+ last.end);             
            if (last.end <= next.start){
                //System.out.println("Adding job at index: " +j);
                schedule[j] = next; 
                last = next;
                j++;
            }
        }
        return schedule;
    }                                   
           
    public static void main(String[] args)   throws FileNotFoundException{
        Job list[] = readFile();
        mergesort ms = new mergesort();
        ms.mSort(list, 0, list.length-1);
        list = schedule(list);
        printJobs(list);

    }
}
