package selftest.algo;

import java.util.Random;

public abstract class OrderExer extends AlgoExerTester {

	protected Integer[] RNDLIST;
	protected final int ARRAY_SIZE = 30;
	protected final int BOUND = 100;
	protected Random RND = new Random(47);
	
	{
		init();
	}

	public OrderExer() { }
	
	@Override
	protected void init() {
		RNDLIST = new Integer[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++) {
			RNDLIST[i] = RND.nextInt(BOUND);
		}
		print(RNDLIST);
	}
	
	protected void print(Integer[] arr) {
		System.out.println(arrayToString(arr));
	}
	
	protected void print(String s, Integer[] arr) {
		System.out.println(s + arrayToString(arr));
	}
	
	private String arrayToString(Integer[] arr) {
		StringBuilder sb = new StringBuilder("[ ");
		for (Integer i: arr) {
			sb.append(i).append(" ");
		}
		sb.append(" ]");
		return sb.toString();
	}
}