class Solution {
    private int getCount(int[] arr, int l, int r) {
        int rCount = 1;
        int lCount = 1;
        while (l > 0 && arr[--l] == 0)
            lCount ++;
        while (r+1 < arr.length && arr[++r] == 0)
            rCount ++;
        return lCount * rCount;
    }
    
    private int[] findFirstMatch(int[] A, int S) {
        int l = 0;
        int r = 0;
        if (A[l] == S)
            return new int[]{l, l};
        
        while (l<A.length && A[l] > S)
            r = ++l;
        
        if (l == A.length)
            return null;
        
        int sum = A[r++];
        while (r < A.length && sum < S)
            sum += A[r++];
        
        if (sum < S)
            return null;
        else
            return new int[]{l, r-1};
    }
    
    private int[] findNextMatch(int[] arr, int S, int l, int r) {
        int sum = S - 1;
        r++;
        l++;
        if (r  < arr.length) {
            sum += arr[r];
        }
        
        while (r + 1 < arr.length && sum < S) {
            sum += arr[++r];
        }
        
        if (sum < S)
            return null;
        else {
            while (arr[l] == 0)
                l++;
            return new int[]{l, r};
        }
    }
    
    public int numSubarraysWithSum(int[] A, int S) {
        int[] match = findFirstMatch(A, S);
        System.out.println("First Match: " + match[0] + "," + match[1]);
        
        if (match == null)
            return 0;
        
        int sum = getCount(A, match[0], match[1]);
        
        match = findNextMatch(A, S, match[0], match[1]);
       	if (match != null)
        	System.out.println("Next Match: " + match[0] + "," + match[1]);
        while (match != null) {
            sum += getCount(A, match[0], match[1]);
            match = findNextMatch(A, S, match[0], match[1]);
            if (match != null)
            	System.out.println("Next Match: " + match[0] + "," + match[1]);
        }
        
        return sum;
        
    }

    public static void main(String[] args) {
    	int[] A = new int[args.length-1];
    	int S = Integer.parseInt(args[args.length-1]);
    	for (int i = 0; i < A.length; i++)
    		A[i] = Integer.parseInt(args[i]);
    	Solution solution = new Solution();
    	System.out.println(solution.numSubarraysWithSum(A, S));
    }
}