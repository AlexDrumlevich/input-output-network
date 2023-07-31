package telran.io.perfomance;

public class CopyPerfomanceTest extends PerfomanceTest {

	private String pathToSource;
	private String pathToDestination;
	private CopyFile copyFile;
	
	public CopyPerfomanceTest(String testName, int nTimes, String pathToSource, String pathToDestination, CopyFile copyFile) {
		super(testName, nTimes);
		this.pathToSource = pathToSource;
		this.pathToDestination = pathToDestination;
		this.copyFile = copyFile;
	}
	
	@Override
	protected void runTest() {
		copyFile.copy(pathToSource, pathToDestination);
	}

}
