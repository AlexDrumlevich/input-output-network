package telran.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InputOutputTest {

	private static final Path FILE_PATH = Path.of("/Users/alexeydrumlevich/JAVA/abc.txt");
	private static final String NOT_EXIST = "doesn't exist";
	private static final String NO_DIRECTORY = "no directory";

	@BeforeEach
	void setUp() throws Exception {
		Files.createFile(FILE_PATH);
	}
	@AfterEach
	void tearDown() throws Exception {
		Files.delete(FILE_PATH);
	}

	@Test
	void pathTests() {
		System.out.println("s/pathTests");
		Path pathParent = Path.of("../..");
		System.out.println(pathParent.toAbsolutePath().normalize()
				.getName(0));
		System.out.println(pathParent.toAbsolutePath());
		System.out.println(pathParent.toAbsolutePath().normalize());
		System.out.println(pathParent.normalize());
		System.out.println(pathParent);
		System.out.println("-----");

		Path pathParent3 = Path.of("../test/test");
		System.out.println(pathParent3.toAbsolutePath().normalize()
				.getName(0));
		System.out.println(pathParent3.toAbsolutePath());
		System.out.println(pathParent3.toAbsolutePath().normalize());
		System.out.println(pathParent3.normalize());
		System.out.println(pathParent3);
		System.out.println("-----");


		Path pathParent2 = Path.of("/Users/alexeydrumlevich/JAVA");
		System.out.println(pathParent2.toAbsolutePath().normalize()
				.getName(0));
		System.out.println(pathParent2.toAbsolutePath());
		System.out.println(pathParent2.toAbsolutePath().normalize());
		System.out.println(pathParent2.normalize());
		System.out.println(pathParent2);
		System.out.println(pathParent2.normalize());
		System.out.println("f/pathTests");

	}
	@Disabled
	@Test
	void displayDirContentTest() throws IOException {
		//displayDirContent(Path.of("../.."), 3);
		displayDirContent(Path.of("/Users/alexeydrumlevich"), 3);

		displayDirContent(Path.of("../.."), 3);
		assertThrowsExactly(IllegalArgumentException.class,
				()->displayDirContent(Path.of("/Users/User/Doc"), 1), NOT_EXIST);

		assertThrowsExactly(IllegalArgumentException.class,
				()->displayDirContent(FILE_PATH, 1), NO_DIRECTORY);


	}

	private void displayDirContent(Path dirPath, int maxDepth) throws IOException {

		dirPath = dirPath.toAbsolutePath().normalize();
		if(!Files.exists(dirPath)) {
			throw new IllegalArgumentException("doesn't exist");
		}
		if (!Files.isDirectory(dirPath)) {
			throw new IllegalArgumentException("no directory");
		}
		int count = dirPath.getNameCount();
		Files.walk(dirPath, maxDepth)
		.forEach(p -> System.out.printf("%s%s - %s\n", " ".repeat(p.getNameCount() - count),
				p.getName(p.getNameCount() - 1), Files.isDirectory(p) ? "dir" : "file"));


		//Display content directory by using method walk of the class Files
		//		simple directory name>
		//       <simple file name> - file
		//       <subdirectory name> - dir
		//          <simple file name> - file
		//          <subdirectory name> - dir
		//                  
		//             <simple file name> - file


	}


	@Test
	void perfomanceBufferTest() {
		int[] fileByteSizes = {1_000, 1_500, 1_000_000, 1_000_000_000};
		int[] bufferByteSizes = {1_000, 1_000_000, 1_000_000_000};
		String someTextString = "1234567890";

		try {
			
			//for different sizes of file
			for(int fileByteSize : fileByteSizes) {
				//preparing for test (create file and write data into file) 
				FileOutputStream fileCopyFromOutput = new FileOutputStream("copyFrom");	
				for(int i = 0; i < 10; i++) {
					StringBuilder stringBuilder = new StringBuilder();
					for(int j = 0; j < fileByteSize / 10 / someTextString.getBytes().length; j++) {
						stringBuilder.append(someTextString);
					}
					fileCopyFromOutput.write(stringBuilder.toString().getBytes());	
				}
				fileCopyFromOutput.close();

				for(int bufferByteSize : bufferByteSizes) {
					//create streams
					FileInputStream fileCopyFromInput = new FileInputStream("copyFrom");	
					FileOutputStream fileCopyTo = new FileOutputStream("copyTo");
					//create buffer
					byte[] byteBuffer = new byte[bufferByteSize];

					int stopPoint = 0;
					long timeStart = System.currentTimeMillis();
					while ((stopPoint = fileCopyFromInput.read(byteBuffer)) != -1) {
						// with out stop point it will write all content of buffer even if current read operation used not all buffer size
						fileCopyTo.write(byteBuffer, 0, stopPoint);
					}
					long timeStop = System.currentTimeMillis();
					fileCopyFromInput.close();
					fileCopyTo.close();

					System.out.printf("file size: %d bytes, buffer: %d bytes, copy time: %d ms.\n", Files.size(Path.of("copyTo").toAbsolutePath()), bufferByteSize,  timeStop - timeStart);
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}



	}
}