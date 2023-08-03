package telran.io.TextCommentSeparator;

import java.nio.file.Path;

record RecordArgumentsTextCommentSeparator(String soucePathString, String textDestinationPathString, String commentsDestinationPathString) {
		
	static RecordArgumentsTextCommentSeparator getRecordArgumentsTextCommentSeparator(String[] arguStrings) {
	
		if(arguStrings.length != RecordArgumentsTextCommentSeparator.class.getDeclaredFields().length) {
			throw new IllegalArgumentException(String.format(" Count of arguments must be %d", RecordArgumentsTextCommentSeparator.class.getFields().length));
		}
		return new RecordArgumentsTextCommentSeparator(arguStrings[0], arguStrings[1], arguStrings[2]);
	}
	

	
}
