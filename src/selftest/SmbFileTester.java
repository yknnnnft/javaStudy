package selftest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jcifs.Config;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

public class SmbFileTester {

	private static final String SMB_FILE = "smb://pe:pe@nas177/share/hrsolap780/CompanyUpdater/test/";
	private static final String SMB_DIRECTORY = "smb://pe:pe@nas177/share/hrsolap780/CompanyUpdater/CIU12200/";
	
	private static final String OPSTECH = "smb://zuo_y:zuo_y@opstech/installer/COMPANY UPDATER/1.2.2.00-SNAPSHOT/CIU12200/";
	
	private static final String zipDest = "/home/nrl/Documents/test.zip";
	private static final String remoteZipDest = "smb://pe:pe@nas177/share/hrsolap780/CompanyUpdater/test/test.zip";
	
	private ArrayList<SmbFile> fileList = new ArrayList<SmbFile>();

	public void exec() {
//		setAccountProperties();
//		writeFile();
//		readRemoteDirectory();
		try {
			setRemoteFileList(new SmbFile(SMB_FILE));
			readFile();
			System.out.println(fileList);
		} catch (SmbException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeFile() {
		String testString = readFile();
		System.out.println(testString);
	}
	
	private List<String> getFileList(String node) {
		List<String> files = new ArrayList<String>();
		try {
			SmbFile sf = new SmbFile(node);
			if (sf.isDirectory()) {
				for (SmbFile s : sf.listFiles()) {
					System.out.println(s.getName());
				}
				
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return files;
		
	}
	
	
	private String readFile() {
		StringBuilder sb = new StringBuilder();
		byte[] buf;
		int len;
		ZipOutputStream zos = null;
		ZipEntry ze = null;
		InputStream ifs = null;
		OutputStream ofs = null;
		SmbFile root;
		SmbFile toFolder;
		try {
			root = new SmbFile(SMB_FILE);
			toFolder = new SmbFile(remoteZipDest);
			ofs = toFolder.getOutputStream();
			zos = new ZipOutputStream(ofs);
			buf = new byte[16 * 1024 * 1024];
			for (SmbFile sf : this.fileList) {
				ifs = sf.getInputStream();
				String relativePath = new URI(root.getParent()).relativize(sf.getURL().toURI()).toString();
				System.out.println(relativePath);
				ze = new ZipEntry(relativePath);
				zos.putNextEntry(ze);
				while ((len = ifs.read(buf)) > 0) {
					zos.write(buf, 0, len);
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ifs != null) {
					ifs.close();
				}
				if (zos != null) {
					zos.close();
				}
				if (ofs != null) {
					ofs.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	private void setRemoteFileList(SmbFile node) throws SmbException {
		
		if (node.isDirectory()) {
			for (SmbFile sf : node.listFiles()) {
				setRemoteFileList(sf);
			}
		}
		else {
			this.fileList.add(node);
		}

	}
	
	private void readRemoteDirectory() {
		try {
//			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("WORKGROUP", null, null);
//			SmbFile sf = new SmbFile(SMB_DIRECTORY, auth);
			SmbFile sf = new SmbFile(OPSTECH);
			if (sf.isDirectory()) {
				for (SmbFile f : sf.listFiles()) {
					System.out.println(f.isDirectory());
					System.out.println(f.getPath());
				}
			}
		} catch (SmbException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void setAccountProperties() {
		Properties prop = new Properties();
		prop.setProperty("jcifs.smb.client.username", "zuo_y");
		prop.setProperty("jcifs.smb.client.password", "zuo_y");
		Config.setProperties(prop);
	}

}
