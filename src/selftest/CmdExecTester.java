package selftest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

public class CmdExecTester {

	private final String cmd_1 = "echo $PWD";
	private final String cmd_2 = "cmd /c echo $PWD";

	public void exec() {
		apacheExecutorTest();
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
			is = p.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			
			while(p.isAlive()) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
			p.waitFor();
			int ret = p.exitValue();
			System.out.println("ret: " + ret);
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
//		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "selftest.jar", "selftest.CmdExecTester");
		ProcessBuilder pb = new ProcessBuilder("ping", "192.168.182.213");
		Process p = null;
		InputStream is = null;
		BufferedReader br = null;
		
		pb.redirectErrorStream(true);
		try {
			p = pb.start();
			is = p.getInputStream();
			
			br = new BufferedReader(new InputStreamReader(is));
			int i = 0;
			while (true) {
				String line = br.readLine();
				if (line == null && !p.isAlive()) {
					break;
				}
				System.out.println(++i + ": " + line);
			}
			p.waitFor();
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

	public void apacheExecutorTest() {
//		CommandLine cl = new CommandLine("cmd.exe");
//		cl.addArgument("dir");
//		cl.addArgument("c:\\");
		CommandLine cl = new CommandLine("ping");
		cl.addArgument("192.168.182.213");
		try {
			Executor e = new DefaultExecutor();
			PipedOutputStream os = new PipedOutputStream();
			PipedInputStream is = new PipedInputStream(os);
			PumpStreamHandler psh = new PumpStreamHandler(os);
			e.setStreamHandler(psh);
			int ret;
			Thread t = new Thread() {
				@Override
				public void run() {
					System.out.println("Reading log...");
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					while (true) {
						try {
							String line = br.readLine();
							if (line == null)
								break;
							System.out.println(line);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					this.interrupt();
				}
			};
			t.start();
			ret = e.execute(cl);
			while (t.isAlive()) { }
			System.out.println(ret);
		} catch (ExecuteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("startup from tester");
		CmdExecTester cet = new CmdExecTester();
		cet.exec();
	}

}
