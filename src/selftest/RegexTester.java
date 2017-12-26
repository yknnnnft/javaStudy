package selftest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class RegexTester {
	
	private final String contents = "msg to test";
	private final String msg_reg = "(msg to test)";
	private static final String pound = "#";
	private static final String nullString = "";

	public void exec() {
//		testReplaceAll();
//		testRegex();
//		testTimeStamp();
//		testSplitByDot();
//		testSplitJDBCUrl();
		testMaskCommand();
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
		String toReplace = "123456ABcde_^";
		System.out.println(toReplace);
		System.out.println(toReplace.replaceAll(".", "*"));
	}
	
	private String modify(String msg, String before, String after) {
		return msg.replaceAll(before + msg_reg, after + "$1");
	}
	
	private void testSplitByDot() {
		String oracleVersion = "12.2.0.1.0";
		System.out.println(oracleVersion.split("\\.")[0]);
	}
	private void testTimeStamp() {
		Path srcHttpdConf = Paths.get("/home/ibm", "conf", "httpd.conf");
	    String HTTPD_CONF_BK_SUFFIX = "'_CU_Bk_'yyyymmdd_HHmmss";
    	String suffix = new SimpleDateFormat(HTTPD_CONF_BK_SUFFIX).format(Calendar.getInstance().getTime());
    	String sHttpdConf = srcHttpdConf.toString().replaceFirst("\\.conf$", suffix + ".conf");
    	System.out.println(sHttpdConf);
	}
	
	private void testSplitJDBCUrl() {
		String url = "jdbc:oracle:thin:@192.168.182.201:1521:cp06";
		System.out.println(Arrays.asList(url.split(":")));
	}
	
	private void testMaskCommand() {
		String cmd = "expdp.exe JINJI/abcde@192.168.182.201:1521/cp06 DUMPFILE=cp06_JINJI__CU20171212_192644_%u.dmpdp LOGFILE=cp06_JINJI__CU20171212_192644.log COMPRESSION=NONE FILESIZE=4294967295 SCHEMAS=JINJI EXCLUDE=STATISTICS,USER DIRECTORY=COMPANYWKDIR JOB_NAME=cp06_JINJI_CU20171212_192644";
		System.out.println(cmd.replaceFirst("/(.*)@", "/********@"));
	}
}
