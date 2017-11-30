package selftest.algo;

import java.util.ArrayList;
import java.util.List;

public class ShellSortExer extends SortExer {

	private List<Integer> stepArray; 

	@Override
	protected void work() {
		print("STEP: ", stepArray, true);
		for (int j = stepArray.size() - 1; j >= 0; j--) {
//			System.out.println("step: " + stepArray.get(j));
			process(stepArray.get(j));
		}
		print(result);
	}
	
	private void process(int step) {
		int l = result.length;
		for (int i = 0; i < step; i++) {
			for (int j = i + step; j < l; j += step) {
				int currVal = result[j];
				int k = j;
				for (; k > i; k -= step) {
					if (currVal < result[k - step]) {
						result[k] = result[k - step];
					}
					else {
						break;
					}
				}
				result[k] = currVal;
			}
		}
	}
	
	@Override
	protected void init() {
		super.init();
		initStep();
	}

	private void initStep() {
		this.stepArray = getStepArraySed();
	}
	
	/*
	 * Step array by Sedgewick
	 */
	private List<Integer> getStepArraySed() {
		List<Integer> first = getStepArraySedFirst();
		List<Integer> second = getStepArraySedSecond();
		List<Integer> stepList = new ArrayList<Integer>();
		int i = 0, j = 0;
		while (i < first.size()) {
			int firstVal = first.get(i++);
			int secondVal;
			while (j < second.size() && ((secondVal = second.get(j)) < firstVal)) {
				stepList.add(secondVal);
				j++;
			}
			stepList.add(firstVal);
		}
		return stepList;
	}
	
	private List<Integer> getStepArraySedFirst() {
		List<Integer> stepArray = new ArrayList<Integer>();
		int stepLimit = RNDLIST.length / 3;
		int idx = 0;
		int currStep = 0;
		while ((currStep = getStepSedFirst(idx++)) < stepLimit) {
			stepArray.add(currStep);
		}
		return stepArray;
 	}
	
	private int getStepSedFirst(int idx) {
		return (int) (9 * Math.pow(4, idx) - 9 * Math.pow(2, idx) + 1);
	}

	private List<Integer> getStepArraySedSecond() {
		List<Integer> stepArray = new ArrayList<Integer>();
		int stepLimit = RNDLIST.length / 3;
		int idx = 0;
		int currStep = 0;
		while ((currStep = getStepSedSecond(idx++)) < stepLimit) {
			stepArray.add(currStep);
		}
		return stepArray;	
 	}
	
	private int getStepSedSecond(int idx) {
		return (int) (Math.pow(2, idx + 2) * (Math.pow(2, idx + 2) - 3) + 1);
	}
}
