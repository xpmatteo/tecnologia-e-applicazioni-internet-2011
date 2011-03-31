import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class FileCopierTest {

	@Test
	public void opensInputStream() throws Exception {
		CharStreamCopier streamCopier = new CharStreamCopier();
		FileCopier copier = new FileCopier(streamCopier);
		
		createFileWithContents("inputfile.txt", "abcde");
		
		copier.copy("inputfile.txt", "outputfile.txt");
		
		assertEquals("abcde", fileContents("outputfile.txt"));
	}

	private void createFileWithContents(String fileName, String contents) throws IOException {
		FileWriter writer = new FileWriter(fileName);
		writer.write(contents);
		writer.close();
	}

	private String fileContents(String fileName) throws IOException {
		String result = "";
		FileReader reader = new FileReader(fileName);
		int c;
		while (-1 != (c = reader.read())) {
			result += (char) c;
		}
		return result;
	}
}
