package selftest.algo;

import java.util.Arrays;

public class MergeOrderExer extends OrderExer {

	@Override
	public void work() {
		Integer[] result = execList(RNDLIST);
		print("result: ", result);
	}
	
	private Integer[] execList(Integer[] arr) {
		int maxPoint = arr.length;
		Integer[] result = new Integer[maxPoint];
		int halfPoint = maxPoint / 2;
		Integer[] pre = Arrays.copyOfRange(arr, 0, halfPoint);
		Integer[] post = Arrays.copyOfRange(arr, halfPoint, maxPoint);
//		print("pre in execList: ", pre);
//		print("post in execList: ", post);
		result = merge(pre, post);
		return result;
	}
	
	private Integer[] merge(Integer[] pre, Integer[] post) {
		Integer[] result = new Integer[pre.length + post.length];
		Integer[] afterPre = pre;
		Integer[] afterPost = post;
		if (pre.length > 1) {
			afterPre = execList(pre);
//			print("afterPre in merge: ", afterPre);
		}
		if (post.length > 1) {
			afterPost = execList(post);
//			print("afterPost in merge: ", afterPost);
		}
//		print("length > 1, pre: ", afterPre);
//		print("length > 1, post: ", afterPost);
		int indexInPre = 0, indexInPost = 0, indexInResult = 0;
		while (indexInPre < afterPre.length || indexInPost < afterPost.length) {
//			System.out.println("indexInPre: " + indexInPre);
//			System.out.println("indexInPost: " + indexInPost);
//			System.out.println("indexInResult: " + indexInResult);
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
