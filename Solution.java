import java.util.*;

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

    	if (S == 0 || S == 1) {
    		r++;
    		while (r < arr.length && arr[r] != S) 
    			r++;

    		if (r == arr.length)
    			return null;

    		return new int[]{r, r};
    	}

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
    	if (S == 0) {
    		int r = 0;
            int l = 0;
            int sum = 0;
            for (; r < A.length; r++) {
                if (A[r] == 0 && A[l] != 0) {
                    l = r;
                }
                else if (A[r] != 0 && A[l] == 0) {
                    int n = r - l;
                    sum  += n*(n+1)/2;
                    l = r;
                }
            }

            if (A[l] == 0) {
                int n = r - l;
                sum += n*(n+1)/2;
            }

            return sum;
    	}


        int l = 0;
        int r = 0;
        int lCount = 1;
        int rCount = 1;
        int sum = 0;
        int total = 0;
        for (; r < A.length; r++) {
            sum += A[r];
            if (A[r] == 1 && sum == S) {
                lCount = 1;
                rCount = 1;
                while (A[l] != 1) {
                    lCount++;
                    l++;
                }
            }
            else if (A[r] == 0 && sum == S) {
                rCount ++;
            }
            else if (A[r] == 1 && sum > S) {
                total += rCount * lCount;
                rCount = 1;
                l++;
                lCount = 1;
                sum -= A[l];
                while (sum > S) {
                    l++;
                    lCount ++;
                    sum -= A[l];
                }
            }
        }
        if (sum == S)
            total += rCount * lCount;
        return total;
    }

    public static void main(String[] args) {
        int[] a = {1,1,1,1,1};
        printResult(a, 1);
        printResult(a, 2);
        printResult(a, 3);
        printResult(a, 4);
        printResult(a, 5);
        printResult(a, 6);
        printResult(a, 7);
        printResult(a, 0);

        int[] b = {1, 1, 1, 0, 0, 1, 1, 1, 0, 0};
        printResult(b, 3);
    }

    private static void printResult(int[] a, int target) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(a) + " target:" + target + " count:" + solution.numSubarraysWithSum(a, target));
    }
}