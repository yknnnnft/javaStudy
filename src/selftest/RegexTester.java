package selftest;

public class RegexTester {
	
	private final String contents = "msg to test";
	private final String msg_reg = "(msg to test)";
	private static final String pound = "#";
	private static final String nullString = "";

	public void exec() {
		testReplaceAll();
	}
	
	private void testReplaceAll() {
		System.out.println("starts");
		String unCommented = modify(contents, pound, nullString);
		System.out.println("unCommented: " + unCommented);
		String commented = modify(unCommented, nullString, pound);
		System.out.println("commented: " + commented);
		System.out.println("ended");
	}
	
	private String modify(String msg, String before, String after) {
		return msg.replaceAll(before + msg_reg, after + "$1");
	}
}
