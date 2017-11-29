package selftest.algo;

public class InsertOrderExer extends OrderExer {

	@Override
	protected void work() {
		print(RNDLIST);
		Integer[] result = execList();
		print(result);
	}
	
	private Integer[] execList() {
		Integer[] result = new Integer[RNDLIST.length];
		result[0] = RNDLIST[0];
		for (int i = 1; i < RNDLIST.length; i++) {
			int currInt = RNDLIST[i];
			boolean isInserted = false;
			for (int j = i - 1; j >= 0; j--) {
				if (currInt < result[j]) {
					result[j + 1] = result[j];
				}
				else {
					result[j + 1] = currInt;
					isInserted = true;
					break;
				}
			}
			if (!isInserted) {
				result[0] = currInt;
			}
		}
		return result;
	}

}
