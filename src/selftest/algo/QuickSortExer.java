package selftest.algo;


public class QuickSortExer extends SortExer {

	@Override
	protected void work() {
		sort(result, 0, result.length - 1);
		print(result, false);
	}
	
	private void sort(Integer[] arr, int startIndex, int endIndex) {
		if (startIndex == endIndex)
			return;
		int pivot = arr[endIndex];
		int indexForward = startIndex, indexBackward = endIndex;
		int currValForward = arr[indexForward];
		int currValBackward = arr[indexBackward];
		while (indexForward < indexBackward) {
			currValForward = arr[indexForward];
			if (currValForward <= pivot) {
				indexForward++;
				continue;
			}
			while (indexForward < indexBackward) {
				currValBackward = arr[indexBackward];
				if (currValBackward <= pivot) {
					break;
				}
				indexBackward--;
			}
			if (indexForward == indexBackward)
				break;
			exchange(arr, indexForward, indexBackward);
		}
		if (indexBackward != endIndex)
			exchange(arr, indexBackward, endIndex);
		sort(arr, startIndex, indexForward - 1);
		sort(arr, indexBackward, endIndex);
	}
}
