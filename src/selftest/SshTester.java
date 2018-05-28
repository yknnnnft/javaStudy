package selftest;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SshTester {

	private Session session;
	private ChannelExec channel;

	public SshTester() {
		
	}

	public void exec(){
		JSch jsch = new JSch();
		try {
			session = jsch.getSession("cjkmain", "192.168.182.156", 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword("cjkmain05");
			session.connect();
			
			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand("cmd /c dir");
			channel.connect();
		
			BufferedInputStream bin = new BufferedInputStream(channel.getInputStream());
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			
			byte[] buf = new byte[1024];
			int len;
			while ((len = bin.read(buf)) > 0) {
				bout.write(buf, 0, len);
			}
			
			System.out.println(new String(bout.toByteArray()));

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
