package selftest;

import java.io.File;
import java.util.Arrays;

public class StreamTester {
	
	public void exec() {
		reduceTest();
	}
	
	private void reduceTest() {
    	String sep = File.separator;
    	String[] command = new String[] {".." + sep + "jre" + sep + "bin" + sep + "java",
    									"-cp",
    									"lib" + sep + "common-io-*.jar;" +
    									"lib" + sep + "httpclient-*.jar;" +
    									"lib" + sep + "json-smart-*.jar;" +
    									"lib" + sep + "updater-self-update.jar",
    									"migration.APP"};

        System.out.println("run db migration: " + 
        			Arrays.stream(command).reduce("", (s1, s2) -> s1 + " " + s2));	
	}
}
