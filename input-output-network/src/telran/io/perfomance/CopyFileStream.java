package telran.io.perfomance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyFileStream implements CopyFile {
	int bufferLength;

	CopyFileStream(int bufferLength) {
		this.bufferLength = bufferLength;
	}

	@Override
	public void copy(String pathToSource, String pathToDestination) {

		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(pathToSource);
			outputStream = new FileOutputStream(pathToDestination);
			//create buffer
			byte[] byteBuffer = new byte[bufferLength];

			int stopPoint = 0;
			long timeStart = System.currentTimeMillis();
			while ((stopPoint = inputStream.read(byteBuffer)) != -1) {
				// with out stop point it will write all content of buffer even if current read operation used not all buffer size
				outputStream.write(byteBuffer, 0, stopPoint);
			}
			long timeStop = System.currentTimeMillis();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
				if(outputStream != null) {
					outputStream.close();
				}
			} catch (IOException ioException) {
				ioException.toString();
			}
			
		}	

		//	System.out.printf("file size: %d bytes, buffer: %d bytes, copy time: %d ms.\n", Files.size(Path.of("copyTo").toAbsolutePath()), bufferByteSize,  timeStop - timeStart);

	}


}
