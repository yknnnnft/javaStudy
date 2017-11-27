package selftest.algo;

import java.util.List;
import java.util.Random;

public abstract class OrderExer {

	protected List<Integer> RNDLIST;
	protected final int LIST_SIZE = 10000;
	protected Random RND = new Random(47);
	
	{
		init();
	}

	public OrderExer() { }
	
	public abstract void exec();

	private void init() {
		for (int i = 0; i < LIST_SIZE; i++) {
			RNDLIST.add(RND.nextInt(Integer.MAX_VALUE));
		}
	}
}
