import java.util.*;

public class finalP {
	int [][] S;		        // stores minimum number of multiplications at each step
	int [][] kTable;        // stores the k values (how the multiplications are partitioned)
	int [] p;		        // contains the dimensions of matrices
	static String [] A ;	// keeps track of Matrix Labels

	public finalP(int i, String [] index) { //constructor to store list of matrices and tables
		S = new int[i+1][i+1];
		kTable = new int[i+1][i+1];
		p = new int[i+1];
		A = new String[i+1];
		String a = "M"; 

		for(int j=0;j<index.length;j++){
			p[j]=Integer.parseInt(index[j]);
        }

		for(int k=1;k<=i;k++){ //List of matrices in string form for printing
			A[k]= a+Integer.toString(k);
		}
	}

	public static String[] readInput(){ //Function for reading matrix dimensions
        Scanner sc = new Scanner(System.in);
        ArrayList<String> nodeArray = new ArrayList<String>();
		
        System.out.println("Please input Matrix dimensions");
        String input = sc.nextLine();
		int c = 0;
        while(!input.contains("#")){
			if (c == 0){ //we assume matrices are of compatible dimensions, so we skip duplicates
				String[] s = input.split("\\*");
				nodeArray.add(s[0]);
				nodeArray.add(s[1]);
			}
			else{
				String[] s = input.split("\\*");
				nodeArray.add(s[1]);
			}
			c++;
            input = sc.nextLine();
        }
		String[] temp = new String[nodeArray.size()];
		for (int i = 0; i<nodeArray.size(); i++){
			temp[i] = nodeArray.get(i);
		}
        return temp;
    }
	
	public void optimizeMult(int [] A){
		int R=0;
		for(int x=1;x<A.length; x++){ //Set diagonal = to 0
			S[x][x]=0;
		}

		for(int i=1; i<A.length-1; i++){
			for(int L=1; L<A.length-i; L++){
				R= L+i;
				S[L][R]=Integer.MAX_VALUE; 

				for(int j=L;j<R;j++){
					int temp= S[L][j]+S[j+1][R]+(p[L-1]*p[j]*p[R]); //Calculates # of multiplications by dividing into subproblems;
                                                                    // previously calculated minimums from the table as well as the cost of multiplying the subproblems
                                                                    // are summed together in order to find the total operations required
					if( S[L][R]>temp){ //takes minimum of all possible multiplication orders
						S[L][R]=temp;
						kTable[L][R]=j; //saves k value so we can retraceSteps to print the optimal order
					}
				}
			}
		}
		
		System.out.println(S[1][A.length-1]+" Multiplications");
	}

	public String traceSteps(int i, int j){ //Recursive function to trace the optimal multiplication order
		if(i==j){
			return A[i];
		}

		else{
			int k= kTable[i][j];
			String X=traceSteps(i,k);
			String Y=traceSteps(k+1,j);			
            String openP = "(";
            String closeP = ")";
            String ast = "*";

            String result = openP+ X + ast + Y + closeP;
            
            return result;

		}
	}

	public static void main(String[] args) {

		String [] index = readInput();
		int n = (index.length-1);

		finalP m1 = new finalP(n,index);
		m1.optimizeMult(m1.p);
        String result = m1.traceSteps(1, n);

		System.out.println("Optimal chain multiplication order is: "+result);				

	}
}
