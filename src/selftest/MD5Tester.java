package selftest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class MD5Tester {

	private Path testFile = Paths.get("/home/nrl/Documents/test/test.txt");
	
	public void exec() {
		System.out.println(getMD5Checksum());
	}
	
	private String getMD5Checksum() {
		
		byte[] b = readFile();
		StringBuilder s = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		
		System.out.println("length:" + b.length);
		for (int i = 0; i < b.length; i++) {
			s.append(b[i]);
			String ss = Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
			System.out.println(ss);
			sb.append(ss);
		}
		System.out.println(s.toString());
		return sb.toString();
	}
	
	private byte[] readFile() {
		
		InputStream is = null;
		MessageDigest complete = null;
		try {

			is = new FileInputStream(testFile.toFile());
			
			byte[] buf = new byte[16 * 1024 * 1024];
			int len = 0;
			
			complete = MessageDigest.getInstance("MD5");
			
			while ((len = is.read(buf)) > 0) {
				complete.update(buf, 0, len);
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (is != null) {
					is.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		
		return complete.digest();
		
	}
}
