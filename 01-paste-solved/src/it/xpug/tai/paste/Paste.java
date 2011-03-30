package it.xpug.tai.paste;

import java.io.FileWriter;
import java.io.IOException;

public class Paste {

	private String separator = "";
	private LineDecorator decorator = new DoNothingDecorator();

	public void paste(String output, String input0, String input1) throws IOException {		
		paste(output, new String[] {input0, input1});
	}

	public void paste(String outputFileName, String[] inputFileNames) throws IOException {
		FileWriter writer = new FileWriter(outputFileName);
		ReadersList readers = new ReadersList(inputFileNames);
		pasteLines(writer, readers);
		writer.close();
	}

	private void pasteLines(FileWriter writer, ReadersList readers) throws IOException {
		LinesList lines = readers.readAll();
		while (lines.notAllNull()) {
			String resultLine = lines.concatenate(separator) + "\n";
			writer.write(decorator.decorate(resultLine));
			lines = readers.readAll();
		}
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public void setLineNumbers() {
		this.decorator = new LineNumbersDecorator();
	}	
}
