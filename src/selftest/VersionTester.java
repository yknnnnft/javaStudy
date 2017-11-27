package selftest;

public class VersionTester {

	private Class<?> mainClass = TestExecutor.class;
	
	public void exec() {
		getVersion();
	}
	
	private void getVersion() {
		System.out.println(mainClass.getPackage().getImplementationVersion());
	}
}
