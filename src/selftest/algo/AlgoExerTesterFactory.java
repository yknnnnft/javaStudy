package selftest.algo;

public class AlgoExerTesterFactory {

	public static enum Exercise {
		
		MAX_INTERVAL(1),
		MERGE_ORDER(2);
		
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
	
	public static AlgoExerTester getTester(int exerNo) {
		
		AlgoExerTester aet = null;
		Exercise e = Exercise.getExercise(exerNo);
		switch (e) {
			case MAX_INTERVAL:
				aet = new MaxIntervalCal();
			case MERGE_ORDER:
				aet = new MergeOrderExer();
			default:
		}
		return aet;
	}
}