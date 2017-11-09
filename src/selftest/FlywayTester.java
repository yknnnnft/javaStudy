package selftest;

import org.flywaydb.core.Flyway;

public class FlywayTester {

	public static void main(String[] args) {
		System.out.println("flywaytester main starts");
		FlywayTester ft = new FlywayTester();
		ft.exec();
	}

	public void exec() {
		newTest();
	}
	
	private void newTest() {
		
		System.out.println("111");
		Flyway fw = new Flyway();
		System.out.println("222");
	}
}
