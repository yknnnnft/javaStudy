package selftest;

import java.util.HashMap;
import java.util.Map;

public class UtilTester {

	String KEY1 = "KEY1";
	String KEY2 = "KEY2";
	String VAL1 = "VAL1";
	String VAL2 = "VAL2";
	public void exec() {
		testNullOrEmpty();
	}
	
	private void testNullOrEmpty() {
		Map<String, String> m = new HashMap<String, String>();
		m.put(KEY1, VAL1);
		String v2 = m.get(KEY2);
		System.out.println(v2);
	}
}
