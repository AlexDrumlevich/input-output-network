package telran.io.TextCommentSeparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Path;



public class TextCommentSeparator {
	public static void main(String[] args) {
		RecordArgumentsTextCommentSeparator recordArgumentsTextCommentSeparator = RecordArgumentsTextCommentSeparator.getRecordArgumentsTextCommentSeparator(args);
		separateSourceIntoTextComments(
				getAbsoluteNormalizedPathString(recordArgumentsTextCommentSeparator.soucePathString()),
				getAbsoluteNormalizedPathString(recordArgumentsTextCommentSeparator.textDestinationPathString()),
				getAbsoluteNormalizedPathString(recordArgumentsTextCommentSeparator.commentsDestinationPathString())
		);
	}
	
	private static String getAbsoluteNormalizedPathString(String pathString) {
		return Path.of(pathString).toAbsolutePath().normalize().toString();
	}
	
	private static void separateSourceIntoTextComments(String sourcePath, String textDestinationPath, String commentsDestinationPath) {
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			PrintWriter textWriter = new PrintWriter(textDestinationPath);
			PrintWriter commentsWriter = new PrintWriter(commentsDestinationPath)) {
		
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.matches(".*//.*")) {
					if(line.matches("(\s*\t*)*//.*") ) {
						commentsWriter.println(line);
					} else {
						String[] lines = line.split("//", 2);
						textWriter.println(lines[0]);
						commentsWriter.println("//" + (lines.length > 1 ? lines[1] : ""));
					}
				} else {
					textWriter.println(line);
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
