import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileCopier {

	private final CharStreamCopier streamCopier;

	public FileCopier(CharStreamCopier streamCopier) {
		this.streamCopier = streamCopier;
	}

	public void copy(String inputFileName, String outputFileName) throws IOException {
		FileReader reader = new FileReader(inputFileName);
		FileWriter writer = new FileWriter(outputFileName);
		streamCopier.copy(reader, writer);
	}

}
