package selftest.algo;

public class InsertSortExer extends SortExer {

	@Override
	protected void work() {
		execListInPlace();
		print(result);
	}
	
	private void execListInPlace() {
		for (int i = 1; i < result.length; i++) {
			int temp = result[i];
			int j = i;
			for (; j > 0; j--) {
				if (temp < result[j - 1]) {
					result[j] = result[j - 1];
				}
				else {
					break;
				}
			}
			result[j] = temp;
		}
	}

	@SuppressWarnings("unused")
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
