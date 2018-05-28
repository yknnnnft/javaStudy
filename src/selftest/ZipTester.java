package selftest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipTester {

	private final String zipFile = "/home/nrl/eclipse-workspace-selftest/selftest/WorkDir/cws30.war";

	public void exec() {
		Path zip = Paths.get(zipFile);
		try {
			org.apache.commons.compress.archivers.zip.ZipFile zipFile = new org.apache.commons.compress.archivers.zip.ZipFile(zip.toFile(), "UTF8");
			System.out.println("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
