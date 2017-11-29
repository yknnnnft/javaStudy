package selftest.algo;

public abstract class AlgoExerTester {

	public void exec(){
//		System.out.println(INT_LIST);
		init();
		long startTime = System.currentTimeMillis();
		work();
		long endTime = System.currentTimeMillis();
		long usedTime = endTime - startTime;
		System.out.println(String.format("Time used: %sm%ss (%s ms)", usedTime / 1000 / 60, (usedTime / 1000) % 60, usedTime));
	};
	
	protected abstract void init();
	protected abstract void work();
}
