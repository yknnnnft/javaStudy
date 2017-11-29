package selftest.algo;

public class BubbleOrderExer extends OrderExer {

	@Override
	protected void work() {
		print(RNDLIST);
		int listLen = RNDLIST.length;
		for (int i = 1; i < listLen; i++) {
			for (int j = listLen - 1; j >= i; j--) {
				int pre = RNDLIST[j - 1], post = RNDLIST[j];
				if (pre > post) {
					RNDLIST[j - 1] = post;
					RNDLIST[j] = pre;
				}
			}
		}
		print(RNDLIST);
	}

}
