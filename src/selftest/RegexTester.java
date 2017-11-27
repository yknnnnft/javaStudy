package selftest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegexTester {
	
	private final String contents = "msg to test";
	private final String msg_reg = "(msg to test)";
	private static final String pound = "#";
	private static final String nullString = "";

	public void exec() {
//		testReplaceAll();
//		testRegex();
		testTimeStamp();
	}
	
	private void testRegex() {
		String reg = "(\\s|,)";
		String strToTest = "1,2 3,4";
		String[] strArray = strToTest.split(reg);
		for (String s : strArray) {
			System.out.println(s);
		}
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
	
	private void testTimeStamp() {
		Path srcHttpdConf = Paths.get("/home/ibm", "conf", "httpd.conf");
	    String HTTPD_CONF_BK_SUFFIX = "'_CU_Bk_'yyyymmdd_HHmmss";
    	String suffix = new SimpleDateFormat(HTTPD_CONF_BK_SUFFIX).format(Calendar.getInstance().getTime());
    	String sHttpdConf = srcHttpdConf.toString().replaceFirst("\\.conf$", suffix + ".conf");
    	System.out.println(sHttpdConf);
	}
}
