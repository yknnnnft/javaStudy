package selftest;

public class InheritTester {

	static {
		System.out.println("InheritTester Static Method");
	}
	
	public InheritTester() {
		System.out.println("InheritTester Constructed");
	}
	
	public void exec() {
		msg();
	}
	
	public void msg() {
		System.out.println("bang!");
	}
}
