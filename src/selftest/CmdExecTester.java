package selftest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdExecTester {

	private final String cmd_1 = "echo $PWD";
	private final String cmd_2 = "cmd /c echo $PWD";

	private String getCmd() {
		return this.cmd_1;
	}
	
	public void exec() {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("startup from tester");
		CmdExecTester cet = new CmdExecTester();
		cet.exec();
	}

}
