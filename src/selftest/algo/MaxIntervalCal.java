package selftest.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaxIntervalCal extends AlgoExerTester {

	private List<Integer> INT_LIST;
	private final int LIST_SIZE = 10000000;
	private final int BOUND = 100;
	private static Random rnd = new Random(47);
	private int startIndex = 0;
	private int endIndex = 0;
	private int sameValue = 0;
	
	public MaxIntervalCal() {
		INT_LIST = new ArrayList<Integer>();
	}
	@Override
	public void init() {
		INT_LIST = listGenerate();
	}
	/*
	 * workInRegular(): usedTime(1736ms)@LIST_SIZE(10000000)
	 */
	@Override
	public void work() {
		int interval = workInRegular();
		System.out.println("Max Interval: " + interval);
		System.out.println("start at: " + startIndex + "; end at: " + endIndex);
		System.out.println("Test: start value: " + INT_LIST.get(startIndex) + "; end value: " + INT_LIST.get(endIndex) + "; sameValue: " + sameValue);
	}
	
	private Integer[] separateList(Integer[] oldList) {
		Integer[] newList = new Integer[oldList.length / 2];
		
		return newList;
	}

	private int workInRegular() {
		int interval = 0;
		outer: for (int i = 0; i < LIST_SIZE; i++) {
			int st = INT_LIST.get(i);
			for (int j = i + 1; j < LIST_SIZE; j++) {
				if (st == INT_LIST.get(j)) {
					if (interval < j - i) {
						sameValue = st;
						interval = j - i;
						startIndex = i;
						endIndex = j;
					}
					continue outer;
				}
			}
		}
		return interval;
	}

	private List<Integer> listGenerate() {
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < LIST_SIZE; i++) {
			l.add(rnd.nextInt(BOUND));
		}
		return l;
	}

}
