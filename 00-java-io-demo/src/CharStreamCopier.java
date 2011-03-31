import java.io.IOException;
import java.io.Reader;
import java.io.Writer;


public class CharStreamCopier {

	public void copy(Reader inputStream, Writer outputStream) throws IOException {
		try {
	        int c;
	        while ((c = inputStream.read()) != -1) {
	            outputStream.write(c);
	        }			
		} finally {
			outputStream.close();
			inputStream.close();
		}
	}
	
    public static void main(String[] args) throws IOException {
        CharStreamCopier streamCopier = new CharStreamCopier();
        FileCopier copier = new FileCopier(streamCopier);
        copier.copy("xanadu.txt", "output.txt");
    }

}
