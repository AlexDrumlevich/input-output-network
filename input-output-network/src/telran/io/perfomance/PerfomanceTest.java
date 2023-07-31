package telran.io.perfomance;

public abstract class PerfomanceTest {

	private	String testName;
	private int nRuns;

	public PerfomanceTest(String testName, int nRuns) {
		this.testName = testName;
		this.nRuns = nRuns;
	}

	abstract protected void runTest();


	public void run() {
		long timeStart = System.currentTimeMillis();
		for(int i = 0; i < nRuns; i++) {
			runTest();
		}
		long timeStop = System.currentTimeMillis();
		System.out.printf("\ntest %s; Number of the runs: %d; Running time: %dMs\n",
				testName, nRuns, timeStop - timeStart);
	}
}
