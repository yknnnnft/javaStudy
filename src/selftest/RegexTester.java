package selftest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
		dateFormatTest();
	}
	
	private void testGroup() {
//		String toTest = "aaabbb_ccc";
		String toTest = "aaabbb_cc";
		String reg = "(aaa)(bbb)(_ccc)";
		String tested = toTest.replaceFirst(reg, "$3");
		System.out.println("toTest:" + toTest + "; tested: " + tested);
	}
	private void testRegex() {
		String format = escapeFilenameRegex("DB_COPY_EXP%U.dmpdp").replaceAll("%[sU]", "(.+)");
		System.out.println(format);
		Pattern p = Pattern.compile(format);
		String toMatch = "DB_COPY_EXP01.dmpdp";
		Matcher m = p.matcher(toMatch);
		if (m.find() && m.groupCount() > 0) {
			System.out.println(m.group(1));
		} else {
			System.out.println("not found!");
		}
	}

    public static String escapeFilenameRegex(String regexString) {
        return regexString.replaceAll("(\\[.*)(-)(.*\\])", "$1\\\\$2$3")
                .replaceAll("([\\[|\\]|\\{|\\}|\\.|\\+])", "\\\\$1");
    }

	private void testReplaceAll() {
		String toReplace = "VER74110101";
		System.out.println(toReplace);
		System.out.println(toReplace.replaceAll("(VER)(?=\\d{8})", "CJK"));
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
	
	private void simpleTest() {
		String reg = "smb://.+:.+@.+/.+";
		String toTestString = "smb://cjkmain:cjkmain05@192.168.182.156/cjkdb";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(toTestString);
		if (m.find()) {
			System.out.println(m.group(m.groupCount()));
		}
	}
	
	private void dateFormatTest() {
        Pattern p = Pattern.compile("^\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
//        Pattern p = Pattern.compile("NOTEXIST");
//        String date = "2018/04/18 14:42:34[NORMAL ][write@CWSLog:238] prev thread is null";
        String date = "  <header>";
        Matcher m = p.matcher(date);
        if (m.find()) {
			System.out.println(m.start());
        } else {
        	System.out.println("NOT FOUND");
        }
	}

	private void stringFormatTest() {
		String test1 = "filename_%2d.dmp";
		String test2 = "filename_%d.dmp";
		int index = 1;
		String result1 = String.format(test1, index).replace(" ", "0");
		String result2 = String.format(test2, index);
		System.out.println(result1);
		System.out.println(result2);
	}
	
	private void matchNotFollowedBy() {
		String reg = "ORA\\-(?!(12083|00942))";
		String[] toMatches = new String[] {"ORA-00943", "ORA-00942", "ORA-12083"};
		for (String toMatch: toMatches) {
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(toMatch);
			if (m.find()) {
				System.out.println(String.format("%s : %s", toMatch, m.group()));
			} else {
				System.out.println(String.format("%s : %s", toMatch, "not matched"));
			}
		}
	}
}
