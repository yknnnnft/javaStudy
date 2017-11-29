package selftest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import selftest.algo.AlgoExerTester;
import selftest.algo.AlgoExerTesterFactory;

public class TestExecutor {
	
	private static void httpTesterExec() {
		System.out.println("test 1: test case when not using proxy");
		HttpTester ht_noneproxy = new HttpTester();
		ht_noneproxy.exec(false);
	}
	
	private static void inheritTestExec() {
		new InheritTester2();
		
	}
	
	private static void inheritTestExec2() {
		new InheritTester2().exec();
		InheritTester it = new InheritTester2();
		it.exec();
	}
	
	private static void anonymousClassTestExec() {
		AnonymousClassTester act = new AnonymousClassTester();
		act.exec();
	}
	
	private static void fileSystemTestExec() {
		FileSystemTester fst = new FileSystemTester();
		fst.exec();
	}

	private static void regexTestExec() {
		RegexTester rt = new RegexTester();
		rt.exec();
	}
	
	private static void smbFileTestExec() {
		SmbFileTester sft = new SmbFileTester();
		sft.exec();
	}
	
	private static void uriTestExec() {
		URITester ut = new URITester();
		ut.exec();
	}
	
	private static void MD5TesterExec() {
		MD5Tester mt = new MD5Tester();
		mt.exec();
	}
	
	private static void flyWayTestExec() {
		FlywayTester ft = new FlywayTester();
		ft.exec();
	}
	
	private static void cmdExecTester() {
		CmdExecTester cet = new CmdExecTester();
		cet.exec();
	}

	private static void oddExec() {
		
	}
	
	private static void pr(String s) {
		System.out.println(s);
	}
	
	private static void streamTesterExec() {
		StreamTester st = new StreamTester();
		st.exec();
	}
	
	private static void multiThreadTesterExec() {
		MultiThreadTester mtt = new MultiThreadTester();
		mtt.exec();
	}

	private static void versionTesterExec() {
		VersionTester vt = new VersionTester();
		vt.exec();
	}
	
	private static void timeTester() {
		TimeTester tt = new TimeTester();
		tt.exec();
	}
	
	private static void algoTester() {
		AlgoExerTester aet = AlgoExerTesterFactory.getTester(AlgoExerTesterFactory.Exercise.MAX_INTERVAL);
		aet.exec();
	}
	
	private static void orderTester() {
		AlgoExerTester aet = AlgoExerTesterFactory.getTester(AlgoExerTesterFactory.Exercise.BUBBLE_ORDER);
		aet.exec();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start up from executor");
		orderTester();
	}

}
