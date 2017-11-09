package selftest;

public class AnonymousClassTester {

	public void exec() {
		InheritTester it = new InheritTester() {
			
			@Override
			public void msg() {
				System.out.println("bang!bang!");
			}
			
			public InheritTester newMsg() {
				System.out.println("new bang!");
				return this;
			}
		}.newMsg();
		it.msg();
	}
}
