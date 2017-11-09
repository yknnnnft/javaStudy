package selftest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestExecutor {
	
	private static void httpTesterExec() {
		System.out.println("test 1: test case when not using proxy");
		HttpTester ht_noneproxy = new HttpTester();
		System.out.println(ht_noneproxy.exec(false));
		System.out.println("test 1 ended");
		System.out.println("test 2: test case when using system properties");
		HttpTester ht_usesystem = new HttpTester();
		System.out.println(ht_usesystem.exec(true));
		System.out.println("test 2 ended");
		System.out.println("test 3: test case when using built-in proxy");
		HttpTester ht_proxy = new HttpTester();
		System.out.println(ht_proxy.exec());
		System.out.println("test 3 ended");
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
	
	private static void flyWayTestExec() {
		FlywayTester ft = new FlywayTester();
		ft.exec();
	}
	
	private static void cmdExecTester() {
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "selftest.jar", "selftest.CmdExecTester");
		Process p = null;
		InputStream is = null;
		BufferedReader br = null;
		
		pb.redirectErrorStream(true);
		try {
			p = pb.start();
			is = p.getInputStream();
			
			p.waitFor();
			
			br = new BufferedReader(new InputStreamReader(is));
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				
				if (br != null) {
					br.close();
				}
				if (is != null) {
					is.close();
				}
			}
			catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	private static void oddExec() {
		StringBuilder sbDirParameter = new StringBuilder("-D");
		sbDirParameter.append("update_package_directory")    // keep same with updater-self-update.SelfUpdater.DIR_PARAM_KEY
					  .append("=\"")
					  .append("update_pd")
					  .append("\"");	
		System.out.println(sbDirParameter.toString());
	}
	
	private static void streamTesterExec() {
		StreamTester st = new StreamTester();
		st.exec();
	}
	
	private static void multiThreadTesterExec() {
		MultiThreadTester mtt = new MultiThreadTester();
		mtt.exec();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start up from executor");
		multiThreadTesterExec();
	}

}
