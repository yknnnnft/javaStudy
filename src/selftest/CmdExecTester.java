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
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.ShutdownHookProcessDestroyer;
import org.apache.commons.exec.environment.EnvironmentUtils;

public class CmdExecTester {

	private final String cmd_1 = "echo $PWD";
	private final String cmd_2 = "cmd /c echo $PWD";

	public void exec() {
		try {
			apacheExec();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Thread setLogRetrievingThread(Executor executor) {
		
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream();
		PumpStreamHandler psh = new PumpStreamHandler(pos);
		executor.setStreamHandler(psh);
		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("Reading log...");
				BufferedReader br = null;
				try {
					pis.connect(pos);
					br = new BufferedReader(new InputStreamReader(pis));
					System.out.println("starting reading line");
					executor.getWatchdog().destroyProcess();
					while (true) {
						String line = br.readLine();
						System.out.println("★★★★★★★★★★★★★");
						if (line == null) {
							System.out.println("break");
							break;
						}
						System.out.println("line: " + line);
					}
					
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				finally {
					if (br != null) {
						try {
							System.out.println("to close br...");
							br.close();
							this.interrupt();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				System.out.println("is to interrupt");
				this.interrupt();
			}
		};
		t.start();
		return t;
	}

	private void apacheExec() throws IOException {
//		CommandLine cl = new CommandLine("cmd.exe");
//		cl.addArgument("dir");
//		cl.addArgument("c:\\");
//		System.setProperty("nls_lang", "utf-8");
		@SuppressWarnings("rawtypes")
		Map env = EnvironmentUtils.getProcEnvironment();
		EnvironmentUtils.addVariableToEnvironment(env, "LC_NAME=ja_JP.sjis");
//		CommandLine cl = new CommandLine("env");
		CommandLine cl = new CommandLine("sleep");
		cl.addArgument("2s");
//		cl.addArguments(new String[] {"$HOME"});
		try {
			Executor e = new DefaultExecutor();
			e.setWatchdog(new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT));
			Thread t = setLogRetrievingThread(e);
			System.out.println("starting thread...");
			int ret;
			System.out.println("executing command...");
			ret = e.execute(cl, env);
			while (t.isAlive()) {};
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
		try {
			cet.apacheExec();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
