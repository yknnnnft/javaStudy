package selftest.algo;

import java.util.Arrays;

public class MergeOrderExer extends OrderExer {

	@Override
	public void work() {
		Integer[] result = execList(RNDLIST);
		print(result);
	}
	
	private Integer[] execList(Integer[] arr) {
		int maxPoint = arr.length;
		Integer[] result = new Integer[maxPoint];
		int halfPoint = maxPoint / 2;
		Integer[] pre = Arrays.copyOfRange(arr, 0, halfPoint);
		Integer[] post = Arrays.copyOfRange(arr, halfPoint, maxPoint);
		result = merge(pre, post);
		return result;
	}
	
	private Integer[] merge(Integer[] pre, Integer[] post) {
		Integer[] result = new Integer[pre.length + post.length];
		Integer[] afterPre = pre;
		Integer[] afterPost = post;
		if (pre.length > 1) {
			afterPre = execList(pre);
		}
		if (post.length > 1) {
			afterPost = execList(post);
		}
		int indexInPre = 0, indexInPost = 0, indexInResult = 0;
		while (indexInPre < afterPre.length || indexInPost < afterPost.length) {
			if (indexInPre == afterPre.length) {
				result[indexInResult++] = afterPost[indexInPost++];
				continue;
			}
				
			if (indexInPost == post.length) {
				result[indexInResult++] = afterPre[indexInPre++];
				continue;
			}

			if (afterPre[indexInPre] < afterPost[indexInPost]) {
				result[indexInResult++] = afterPre[indexInPre++];
			}
			else {
				result[indexInResult++] = afterPost[indexInPost++];
			}
		}

		return result;
	}

}
