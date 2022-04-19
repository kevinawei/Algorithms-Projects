
import java.util.Scanner;

public class MatrixChainParenthesize1 {

	int [][] S;		// used to store number of multiplication
	int [][] M;		// used to store the k values
	int [] p;		// contains the index
	static String [] A ;	// 2d array

	/**
	 * constructor	
	 * @param i		// num of 2d arrays
	 * @param index	// index values
	 */
	public MatrixChainParenthesize1
(int i, String [] index) {
		S = new int[i+1][i+1];
		M = new int[i+1][i+1];
		p = new int[i+1];
		A = new String[i+1];
		String a = "A";

		for(int j=0;j<index.length;j++)
			p[j]=Integer.parseInt(index[j]);


		for(int k=1;k<=i;k++){
			A[k]= a+Integer.toString(k);
		}

	}

	
	/**
	 * Calculates least number of multiplications required
	 * 
	 * @param A contains indexes
	 */
	public void matrix_chain_multi(int [] A){
		int R=0;
		for(int L=1;L<A.length; L++){
			S[L][L]=0;
		}

		for(int d=1; d<A.length-1; d++){
			for(int L=1; L<A.length-d; L++){
				R= L+d;
				S[L][R]=Integer.MAX_VALUE;
				for(int k=L;k<R;k++){
					int temp= S[L][k]+S[k+1][R]+(p[L-1]*p[k]*p[R]);
					if( S[L][R]>temp){
						S[L][R]=temp;
						M[L][R]=k;
					}
				}
			}
		}
		
		System.out.println(S[1][A.length-1]);
	}



	/**
	 * Traceing back to find the 2d array multiplications
	 *  	
	 * @param i index
	 * @param j	index
	 * @return	string 
	 */

	public String trace(int i, int j){
		if(i==j){
			return A[i];
		}

		else{
			int k= M[i][j];
			String X=trace(i,k);
			String Y=trace(k+1,j);			

			return "( ".concat(X.concat(" X ").concat(Y)).concat( " )");

		}
	}


	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String [] index = sc.nextLine().split(" ");

		MatrixChainParenthesize1 q = new MatrixChainParenthesize1(n,index);
		q.matrix_chain_multi(q.p);
		System.out.println(q.trace(1, n));				

	}
}
