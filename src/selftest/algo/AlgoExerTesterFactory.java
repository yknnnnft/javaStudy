package selftest.algo;

public class AlgoExerTesterFactory {

	public static enum Exercise {
		
		MAX_INTERVAL,
		MERGE_SORT,
		BUBBLE_SORT,
		INSERT_SORT,
		QUICK_SORT,
		SHELL_SORT;
		
	}
	
	public static AlgoExerTester getTester(Exercise exer) {

		AlgoExerTester aet = null;

		switch (exer) {
			case MAX_INTERVAL:
				System.out.println("Test to execute: " + Exercise.MAX_INTERVAL);
				aet = new MaxIntervalCal();
				break;
			case MERGE_SORT:
				System.out.println("Test to execute: " + Exercise.MERGE_SORT);
				aet = new MergeSortExer();
				break;
			case BUBBLE_SORT:
				System.out.println("Test to execute: " + Exercise.BUBBLE_SORT);
				aet = new BubbleSortExer();
				break;
			case INSERT_SORT:
				System.out.println("Test to execute: " + Exercise.INSERT_SORT);
				aet = new InsertSortExer();
				break;
			case QUICK_SORT:
				System.out.println("Test to execute: " + Exercise.QUICK_SORT);
				aet = new QuickSortExer();
				break;
			case SHELL_SORT:
				System.out.println("Test to execute: " + Exercise.SHELL_SORT);
				aet = new ShellSortExer();
				break;
			default:
		}

		return aet;
	}
}
