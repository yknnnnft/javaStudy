package selftest;

public class BitCalTester {

	private Integer one = Integer.parseInt("01", 2);
	private Integer two = Integer.parseInt("10", 2);

	public void exec() {
		bitCal();
	}
	
	private void booleanTest(Boolean a, Boolean b) {
		System.out.println(a);
		int i = a.compareTo(Boolean.FALSE);
		System.out.println(i);
	}
	
	private void bitCal() {
		System.out.println(one);
		System.out.println(two);
	}
}
