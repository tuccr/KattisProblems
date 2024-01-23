// Arup Guha
// 5/9/2014
// Solution to 2004-2005 Andrew Stankevich Contest 10 Problem: Divisors

import java.util.*;
import java.io.*;

public class d {

    // Store all solutions here.
	public static HashMap<Long,Long> map;

	// All the primes we'll ever need, plus two constants.
	final public static int[] PRIMES = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
    final public static long MAX = 10000000000000000L;
    final public static int MAXEXP = 60;

	public static void main(String[] args) throws Exception {

		// This contest used file in, file out.
		Scanner fin = new Scanner(new File("divisors.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("a.out"));

		// Create our map and store all the answers - key is num of factors, value is smallest
		// integer that has that many factors.
		map = new HashMap<Long,Long>();
		fillMap();

		// Store results ordered by number of factors here.
		pair[] arr = new pair[map.size()];

		// Copy values from Hash Map to array.
		int i=0;
		for (long x: map.keySet())
			arr[i++] = new pair(x, map.get(x));

        // Sort array by number of factors.
		Arrays.sort(arr);

        // Our one input value.
		long n = fin.nextLong();

		// Look for the first value that's less than or equal to n that has the maximum number
		// of factors up to it. This is our desired answer.
		for (i=arr.length-1; i>=0; i--) {
			if (arr[i].val <= n) {
				fout.println(arr[i].val);
				break;
			}
		}

		// Book-keeping.
		fin.close();
		fout.close();
	}

	// Wrapper function to get our recursion going. exp will store our list of exponents and
	// must be sorted from largest to smallest (repeats okay).
	public static void fillMap() {
		ArrayList<Integer> exp = new ArrayList<Integer>();
		fillRec(exp, 0);
	}

	// k is the slot we're filling out in exp. It's not really necessary but I added it for clarity.
	public static void fillRec(ArrayList<Integer> exp, int k) {

		// All answers are less than 2^60 so this is a reasonable bound.
		int max = MAXEXP;

		// If we have a previous exponent, that's our maximum bound.
		if (k > 0) max = exp.get(k-1);

		// Get both the value of this number and the number of factors it has.
		pair cur = getVal(exp);

		// Cut out of the recursion! Number is too big.
		if (cur.val > MAX) return;

		// We haven't seen this many factors before, so add to our map.
		if (!map.containsKey(cur.numF))
			map.put(cur.numF, cur.val);

        // See what's in the map and see if this number is better. If so,
        // replace our mapped value.
		else {
			long old = map.get(cur.numF);
			if (cur.val < old)
				map.put(cur.numF, cur.val);
		}

        // Add a new prime factor to our list and solve recursively.
		exp.add(1);
		for (int i=1; i<=max; i++) {
			exp.set(k,i);
			if (getVal(exp).val > MAX) break;
			fillRec(exp,k+1);
		}

		// Critical to remove this last element after our recursion finishes!
		exp.remove(k);
	}

	// Returns both the value and the number of factors the number represented by exp has.
	public static pair getVal(ArrayList<Integer> exp) {
		long val = 1;
		long numF = 1;
		for (int i=0; i<exp.size(); i++) {
			val *= myPow(PRIMES[i],exp.get(i));
			numF *= (1+exp.get(i));
		}
		return new pair(numF, val);
	}

	// Makes sure I get an exact value...
	public static long myPow(int base, int exp) {
		long ans = 1;
		for (int i=0; i<exp; i++)
			ans = ans*base;
		return ans;
	}

}

class pair implements Comparable<pair> {

	public long numF;
	public long val;

	public pair(long a, long b) {
		numF = a;
		val = b;
	}

    // Sorts by number of factors...
	public int compareTo(pair other) {
		if (this.numF < other.numF) return -1;
		else if (this.numF == other.numF) return 0;
		return 1;
	}
}

