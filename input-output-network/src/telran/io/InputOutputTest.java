package telran.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputOutputTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void pathTests() {
		Path pathParent = Path.of("../..");
		System.out.println(pathParent.toAbsolutePath().normalize()
				.getName(0));
		System.out.println(pathParent.toAbsolutePath());
		System.out.println(pathParent.toAbsolutePath().normalize());
		
		
	}
	@Test
	void displayDirContentTest() {
		displayDirContent(Path.of("../.."), 3);
	}

	private void displayDirContent(Path dirPath, int maxDepth) {
		// TODO Auto-generated method stub
		
		Path startPath = dirPath.toAbsolutePath().normalize();
		int startPathDepth = startPath.getNameCount();
		
		try {
			Files
				.walk(startPath, maxDepth, new FileVisitOption[0])
				.forEach(path -> System.out.printf(
					"%s %s - %s\n",
					"    ".repeat(path.getNameCount() - startPathDepth), 
					path.getName(path.getNameCount() - 1), 
					Files.isDirectory(path, new LinkOption[0]) ? "dir" : "file")
				);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Display content directory by using method walk of the class Files
//		simple directory name>
//       <simple file name> - file
//       <subdirectory name> - dir
//          <simple file name> - file
//          <subdirectory name> - dir
//                  
//             <simple file name> - file

		
	}

}