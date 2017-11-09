package selftest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
		basicTest();
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
}
