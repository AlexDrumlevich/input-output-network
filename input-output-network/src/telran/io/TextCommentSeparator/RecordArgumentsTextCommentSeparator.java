package telran.io.TextCommentSeparator;


record RecordRequiredArgumentsTextCommentSeparator(String soucePathString, String textDestinationPathString, String commentsDestinationPathString) {
		
	static RecordRequiredArgumentsTextCommentSeparator getRecordRequiredArgumentsTextCommentSeparator(String[] arguStrings) {
			
		if(arguStrings.length != RecordRequiredArgumentsTextCommentSeparator.class.getDeclaredFields().length) {
			throw new IllegalArgumentException(String.format(" Count of arguments must be %d", RecordRequiredArgumentsTextCommentSeparator.class.getDeclaredFields().length));
		}
		return new RecordRequiredArgumentsTextCommentSeparator(arguStrings[0], arguStrings[1], arguStrings[2]);
	}
	

	
}
