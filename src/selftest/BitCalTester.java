package selftest;

import org.apache.commons.lang3.BooleanUtils;

public class BitCalTester {

	private Integer one = Integer.parseInt("01", 2);
	private Integer two = Integer.parseInt("10", 2);

	public void exec() {
		booleanTest(true, false);
	}
	
	private void booleanTest(Boolean a, Boolean b) {
		System.out.println(a);
		int i = a.compareTo(Boolean.FALSE);
		int j = b.compareTo(Boolean.FALSE);
		int k = BooleanUtils.toInteger(a);
		System.out.println(String.format("i: %s; j: %s; k: %s", i, j, k));

	}
	
	private void bitCal() {
		System.out.println(one);
		System.out.println(two);
	}
	
}
