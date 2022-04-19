class f1 {
	static double findMean(int A[], int N)
    {
        System.out.println("Now: " +N);
        if (N == 1)
            return (double)A[N-1];
        else
            return ((double)(findMean(A, N-1)*(N-1) + A[N-1]) / N);
    }
      
    // main Function
    public static void main (String[] args) 
    {
        int A[] = {1, 2, 3, 4, 5, 6};
        int N = A.length;
        System.out.println(findMean(A, N));
    }
}