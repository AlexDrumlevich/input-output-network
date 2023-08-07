package telran.io.TextCommentSeparator;


record RecordRequiredArgumentsTextCommentSeparator(String soucePathString, String textDestinationPathString, String commentsDestinationPathString) {

	
	static RecordRequiredArgumentsTextCommentSeparator getRecordRequiredArgumentsTextCommentSeparator(String[] arguStrings) {

		int countOfDeclaredFields = RecordRequiredArgumentsTextCommentSeparator.class.getDeclaredFields().length;
		
		if(arguStrings.length < countOfDeclaredFields) {
			throw new IllegalArgumentException(String.format("Count of arguments must be %d", RecordRequiredArgumentsTextCommentSeparator.class.getDeclaredFields().length));
		}
		
		try {
			return (RecordRequiredArgumentsTextCommentSeparator) RecordRequiredArgumentsTextCommentSeparator
						.class
						.getDeclaredConstructors()[0]
						.newInstance((Object[])arguStrings);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
