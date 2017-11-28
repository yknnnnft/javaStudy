package selftest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdExecTester {

	private final String cmd_1 = "echo $PWD";
	private final String cmd_2 = "cmd /c echo $PWD";

	public void exec() {
		testClassPath();
	}

	private String getCmd() {
		return this.cmd_1;
	}
	
	private void testFlyway() {
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "selftest.jar", "selftest.FlywayTester");
		pb.redirectErrorStream(true);
		Process p = null;
		InputStream is = null;
		
		
		BufferedReader br = null;
		try {
			System.out.println("test1");
			p = pb.start();
			System.out.println("test2");
			p.waitFor();
			int ret = p.exitValue();
			System.out.println("ret: " + ret);
			is = p.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			
			while(true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (IOException ie) {
			ie.printStackTrace();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void testClassPath() {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("startup from tester");
		CmdExecTester cet = new CmdExecTester();
		cet.exec();
	}

}
