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

    public static int[] sortByEnd(){
        
        return null;
    }

    public static void printJobs(Job[] list){
        System.out.println("Final Schedule:");
        for (int i = 0; i<list.length; i++){
            Job j = list[i];
            if (j!= null){
                System.out.println("Job # "+j.id+"; Hours: "+j.start+" to "+j.end);
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
            System.out.println("Next jobs starts at : "+next.start+" Current job ends at: "+ last.end);             
            if (last.end <= next.start){
                System.out.println("Adding job at index: " +j);
                schedule[j] = next; 
                last = next;
                j++;
            }
        }
        return schedule;
    }                                   
           
    public static void main(String[] args)   throws FileNotFoundException{
        Job list[] = readFile();
        list = schedule(list);
        printJobs(list);

    }
}

