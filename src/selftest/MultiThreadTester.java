package selftest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MultiThreadTester {
	
	private Integer total = 0;
	private List<Integer> hm = new ArrayList<Integer>();

	private class InnerThread extends Thread {
		
		private int curr;
		
		public InnerThread(int curr) {
			this.curr = curr;
		}

		@Override
		public void run() {
			
			try {
				double d = Math.random();
				long interval = Math.round(800.0 + d * 400.0);
				System.out.print("thread : " + curr + "; ");
				System.out.println("total before: " + total);
				Thread.sleep(interval);
				synchronized(hm) {
					int innerTotal = hm.get(0);
					for (int j = 0; j < 1000000; j++) { }
					innerTotal += curr;
					Thread.sleep(interval);
					hm.set(0, innerTotal);
					System.out.print("thread : " + curr + "; ");
					System.out.println("innerTotal: " + innerTotal);
					System.out.print("interval : " + interval + "; ");
					System.out.println("total after: " + hm.get(0));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void exec() {
		zipMultiTest();
	}
	
	private void basicTest(){
		
		hm.add(total);
		Queue<Thread> allThreads = new ConcurrentLinkedQueue<Thread>();
		BlockingQueue<Thread> que = new ArrayBlockingQueue<Thread>(4);
		for (int i = 0; i < 10; i++) {
			Thread t = new InnerThread(i);
			allThreads.add(t);
		}
		
		while (!allThreads.isEmpty()) {
			if (que.remainingCapacity() > 0) {
				Thread tt = allThreads.poll();
				tt.start();
				que.add(tt);
				System.out.println("thread put in: " + tt.getName());
			}
			else {
				System.out.println("que is full");
				outside: while (true) {
					for (Thread tt : que) {
						if (tt.getState() == Thread.State.TERMINATED) {
							System.out.println("thread removed: " + tt.getName());
							que.remove(tt);
							break outside;
						}
					}
				}
			}
		}
	}
	
	private void zipMultiTest() {
		Map<String, Path> paths = wkDirInit();
		zipInMulti(paths);
	}
	
	private Map<String, Path> wkDirInit() {
		Map<String, Path> srcMap = new HashMap<String, Path>(); 
		Path cwp = Paths.get(System.getProperty("user.dir"));
		srcMap.put("cwp", cwp);
		Path srcWorkDir = Paths.get(cwp.toString(), "WorkDir");
		System.out.println("WorkDir: " + srcWorkDir);
		File workDir = srcWorkDir.toFile();
		if (!workDir.exists() || !workDir.isDirectory())
			workDir.mkdir();
		srcMap.put("WorkDir", srcWorkDir);
		System.out.println("Directory initialized.");	
		
		Path srcZipFile = Paths.get(srcWorkDir.toString(), "output.zip");
		File zipFile = srcZipFile.toFile();
		if (zipFile.exists()) {
			zipFile.delete();
		}
		srcMap.put("ZipFile", srcZipFile);
		
		Path srcFolderToZip = Paths.get(srcWorkDir.toString(), "toZip");
		File folderToZip = srcFolderToZip.toFile();
		if (!folderToZip.exists() || !folderToZip.isDirectory()) {
			throw new RuntimeException("input folder error: " + srcFolderToZip);
		}
		else {
			srcMap.put("zipFolder", srcFolderToZip);
			System.out.println("files to zip:");
			for (File f : folderToZip.listFiles()) {
				System.out.println(f.getName());
			}
		}
		return srcMap;
	}
	
	private void zipInMulti(Map<String, Path> paths) {
		
		System.out.println(paths);
		File zipFile = paths.get("ZipFile").toFile();
		File folderToZip = paths.get("zipFolder").toFile();
		for (File f : folderToZip.listFiles()) {
			new Thread() {
				@Override
				public void run() {
					System.out.println("new thread starts: " + this.getName());
					FileOutputStream fos = null;
					ZipOutputStream zos = null;
					FileInputStream fis = null;
					
					byte[] buf;
					int len;
					
					ZipEntry ze = new ZipEntry(f.getName());
					try {
						fos = new FileOutputStream(zipFile);
						zos = new ZipOutputStream(fos);
						zos.putNextEntry(ze);

						fis = new FileInputStream(f);
						buf = new byte[16 * 1024 * 1024];
						while ((len = fis.read(buf)) > 0) {
							zos.write(buf, 0, len);
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							if (zos != null) {
								zos.close();
							}
							if (fos != null) {
								fos.close();
							}
							if (fis != null) {
								fis.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					System.out.println("thread exits: " + this.getName());
				}
			}.start();
		}
	}
}
