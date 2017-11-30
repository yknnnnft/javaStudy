package selftest.algo;

public class BubbleSortExer extends SortExer {

	@Override
	protected void work() {
		int listLen = result.length;
		for (int i = 0; i < listLen; i++) {
			for (int j = 0; j < listLen - i - 1; j++) {
				if (result[j] > result[j + 1])
					exchange(result, j, j + 1);
			}
		}
		print(result);
	}
}
