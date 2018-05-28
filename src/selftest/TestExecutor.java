package selftest;

import java.io.IOException;

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

	private static void oddExec() throws IOException {
		double progress = 15.123;
		System.out.println(String.format("%.2f%%", progress));
	}
	
	private static void zipTesterExec() {
		ZipTester zt = new ZipTester();
		zt.exec();
	}
	
	private static void pr() {
		try {
			throw new Exception("test a exception");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.toString());
			System.out.println("aaa");
			for (StackTraceElement se : e.getStackTrace()) {
				System.out.println("at " + se.toString());
			}
		}
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
	
	private static void bitCalTest() {
		BitCalTester bct = new BitCalTester();
		bct.exec();
	}
	
	private static void UtilTest() {
		UtilTester ut = new UtilTester();
		ut.exec();
	}
	
	private static void SSHTest() {
		SshTester st = new SshTester();
		st.exec();
	}

	private static void classInitializingTest() {
		System.out.println("start initialization");
		System.out.println("Initializing instance: sub 1");
		ClassInitializingTester.Sub sub1 = new ClassInitializingTester.Sub();
		System.out.println("Initializing instance: parent 1");
		ClassInitializingTester.Parent par1 = new ClassInitializingTester.Parent();
		System.out.println("Initializing instance: sub 2");
		ClassInitializingTester.Sub sub2 = new ClassInitializingTester.Sub();
		System.out.println("initialization completed");
	}

	private static void orderTester() {
		AlgoExerTester aet = null;
//		aet = AlgoExerTesterFactory.getTester(AlgoExerTesterFactory.Exercise.INSERT_SORT);
//		aet.exec();
//		aet = AlgoExerTesterFactory.getTester(AlgoExerTesterFactory.Exercise.BUBBLE_SORT);
//		aet.exec();
//		aet = AlgoExerTesterFactory.getTester(AlgoExerTesterFactory.Exercise.MERGE_SORT);
//		aet.exec();
		aet = AlgoExerTesterFactory.getTester(AlgoExerTesterFactory.Exercise.QUICK_SORT);
		aet.exec();
//		aet = AlgoExerTesterFactory.getTester(AlgoExerTesterFactory.Exercise.SHELL_SORT);
//		aet.exec();
	}

	public static void main(String[] args) throws IOException {
		System.out.println("start up from executor");
		classInitializingTest();
	}

}
