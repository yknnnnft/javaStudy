package selftest;

public class InheritTester2 extends InheritTester {

	static {
		System.out.println("InheritTester2 Static Method");
	}
	
	public InheritTester2() {
		super();
		System.out.println("InheritTester2 Constructed");
	}
	
	public void msg() {
		System.out.println("bang! x2");
	}
}
