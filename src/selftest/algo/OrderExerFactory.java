package selftest.algo;

public class OrderExerFactory {

	public enum OrderExercises {
		
		MergeOrder;
	}

	public static OrderExer getInstance(OrderExercises exer) {
		
		OrderExer oe = null;

		switch (exer) {
			case MergeOrder:
			default:
				oe = new MergeOrderExer();
		}
		
		return oe;
	}
}
