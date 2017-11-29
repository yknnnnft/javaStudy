package selftest.algo;

import java.util.Random;

public abstract class SortExer extends AlgoExerTester {

	protected Integer[] RNDLIST;
	protected final int ARRAY_SIZE = 10000000;
	protected final int BOUND = Integer.MAX_VALUE;
	protected Random RND = new Random(47);
	
	public SortExer() { }
	
	@Override
	protected void init() {
		RNDLIST = new Integer[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			RNDLIST[i] = RND.nextInt(BOUND);
		}
		print(RNDLIST);
	}
	
	protected void print(Integer[] arr) {
		print(arr, false);
	}
	
	protected void print(String s, Integer[] arr) {
		System.out.println(s + arrayToString(arr, false));
	}
	
	protected void print(Integer[] arr, boolean useFull) {
		System.out.println(arrayToString(arr, useFull));
	}
	
	protected void print(String s, Integer[] arr, boolean useFull) {
		System.out.println(s + arrayToString(arr, useFull));
	}

	private String arrayToString(Integer[] arr, boolean useFull) {
		StringBuilder sb = new StringBuilder("[ ");
		int i = 0;
		while ((i < 30 || useFull) && i < arr.length) {
			sb.append(arr[i++]).append(" ");
		}
		if (i < arr.length) {
			sb.append("...");
		}
		sb.append(" ]");
		return sb.toString();
	}

}
