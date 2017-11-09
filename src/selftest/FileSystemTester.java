package selftest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemTester {
	
	public void exec() {
//		getFileSystemInfo();
//		subFolderTest();
		resolveTest();
	}
	
	private void getFileSystemInfo() {
		File fs = new File("/home/");
		System.out.println(fs.getTotalSpace() / 1024 / 1024 / 1024);
		System.out.println(fs.getUsableSpace() / 1024 / 1024 / 1024);
		System.out.println(fs.getFreeSpace() / 1024 / 1024 / 1024);

	}
	
	private void subFolderTest() {
		File fs = new File("/home/nrl", "Downloads");
		System.out.println(fs.exists());
	}
	
	private void resolveTest() {
		Path root = Paths.get("/home");
		Path par = Paths.get("/home", "nrl");
		Path sub = Paths.get("/home", "nrl", "Downloads");
		String subString = "Downloads";
		System.out.println(par.resolve(subString));
	}

}
