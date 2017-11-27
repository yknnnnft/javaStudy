package selftest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeTester {

	public void exec() {
		dateFormatTest();
	}
	
	private void timeStamp() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println(ts.toString());
	}
	
	private void dateFormatTest() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("'aa'yyyymmdd_HHmmss");
		System.out.println(sdf.format(c.getTime()));
	}
}
