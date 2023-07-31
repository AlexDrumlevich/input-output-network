package telran.io.perfomance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class CopyFileConpilesAppl {
	
	public static void main(String[] args) {
		
		int[] bufferByteSizes = {10_000, 100_000, 1_000_000, 100_000_000};
		String pathToSource = "/Users/alexeydrumlevich/Фото и видео/IMG_3745.MOV";
		String pathToDestination = "copy";
		
		for(int bufferSize : bufferByteSizes) {
			try {
				System.out.printf("file size: %d bytes, buffer: %d bytes", Files.size(Path.of(pathToSource).toAbsolutePath()), bufferSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
			new CopyPerfomanceTest("copy file perfomance", 1, pathToSource, pathToDestination, new CopyFileStream(bufferSize)).run();
		}
		
		
	}
	
}
