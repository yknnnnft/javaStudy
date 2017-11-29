package selftest.algo;

public class AlgoExerTesterFactory {

	public static enum Exercise {
		
		MAX_INTERVAL,
		MERGE_ORDER,
		BUBBLE_ORDER,
		INSERT_ORDER;
		
	}
	
	public static AlgoExerTester getTester(Exercise exer) {

		AlgoExerTester aet = null;

		switch (exer) {
			case MAX_INTERVAL:
				System.out.println("Test to execute: " + Exercise.MAX_INTERVAL);
				aet = new MaxIntervalCal();
				break;
			case MERGE_ORDER:
				System.out.println("Test to execute: " + Exercise.MERGE_ORDER);
				aet = new MergeOrderExer();
				break;
			case BUBBLE_ORDER:
				System.out.println("Test to execute: " + Exercise.BUBBLE_ORDER);
				aet = new BubbleOrderExer();
				break;
			case INSERT_ORDER:
				System.out.println("Test to execute: " + Exercise.INSERT_ORDER);
				aet = new InsertOrderExer();
				break;
			default:
		}
		return aet;
	}
}
