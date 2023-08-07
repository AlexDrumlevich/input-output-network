package telran.io.TextCommentSeparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Path;

import javax.sound.sampled.Line;



public class TextCommentSeparator {
	public static void main(String[] args) {
		
		RecordRequiredArgumentsTextCommentSeparator recordArgumentsTextCommentSeparator = RecordRequiredArgumentsTextCommentSeparator.getRecordRequiredArgumentsTextCommentSeparator(args);
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
		
			bufferedReader.lines().forEach(line -> {
				if(line.matches(".*//.*")) {
					if(line.matches("(\s*\t*)*//.*") ) {
					} else {
						String[] lines = line.split("//", 2);
						textWriter.println(lines[0]);
						commentsWriter.println("//" + (lines.length > 1 ? lines[1] : ""));
					}
				} else {
					textWriter.println(line);
				}	
			});
			
			/*
			Map<String, List<String>> map = reader.lines()
					.collect(Collectors
							.groupingBy(s -> s.trim().startsWith("//") ?
									COMMENTS : TEXT));
			map.getOrDefault(COMMENTS, Collections.emptyList())
			.forEach(comments::println);
			map.getOrDefault(TEXT, Collections.emptyList())
			.forEach(text::println);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
