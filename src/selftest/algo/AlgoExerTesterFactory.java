package selftest.algo;

public class AlgoExerTesterFactory {

	public static enum Exercise {
		
		MAX_INTERVAL(1),
		MERGE_ORDER(2),
		BUBBLE_ORDER(3);
		
		private int exerNo;
		
		private Exercise(int exerNo) {
			this.exerNo = exerNo;
		}
		
		public static Exercise getExercise(int exNo) {
			for (Exercise e : values()) {
				if (e.exerNo == exNo) {
					return e;
				}
			}
			return null;
		}
		
	}
	
	public static AlgoExerTester getTester(Exercise exer) {

		AlgoExerTester aet = null;
		switch (exer) {
			case MAX_INTERVAL:
				aet = new MaxIntervalCal();
			case MERGE_ORDER:
				aet = new MergeOrderExer();
			case BUBBLE_ORDER:
				aet = new BubbleOrderExer();
			default:
		}
		return aet;
	}
}
