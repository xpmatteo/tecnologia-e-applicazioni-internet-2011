import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import static org.junit.Assert.*;



public class CharStreamCopierTest {
	
	public class ThrowingReader extends Reader {
		public int read(char[] cbuf, int off, int len) throws IOException {
			throw new IOException("anything");
		}
		public void close() throws IOException {
			inputWasClosed = true;
		}
	}

	private final class EmptyReader extends Reader {
		public int read(char[] cbuf, int off, int len) throws IOException {
			return -1;
		}

		public void close() throws IOException {
			inputWasClosed = true;
		}
	}

	private final class DoNothingWriter extends Writer {
		public void write(char[] cbuf, int off, int len) throws IOException {
		}

		public void flush() throws IOException {
		}

		public void close() throws IOException {
			outputWasClosed = true;
		}
	}

	private boolean outputWasClosed = false;
	private boolean inputWasClosed;

	@Test
	public void doesNothingOnEmptyStream() throws Exception {
		assertCopiesCorrectlyThisString("");
	}

	@Test
	public void copiesAThreeCharactersStream() throws Exception {
		assertCopiesCorrectlyThisString("ABC");
	}

	@Test
	public void closesBothStreams() throws Exception {
		CharStreamCopier copier = new CharStreamCopier();
		Writer outputStream = new DoNothingWriter();
		Reader inputStream = new EmptyReader();
		
		copier.copy(inputStream, outputStream);
		
		assertTrue("output closed", outputWasClosed);
		assertTrue("input closed", inputWasClosed);
	}

	@Test
	public void closesBothStreamsWhenReaderThrows() throws Exception {
		CharStreamCopier copier = new CharStreamCopier();
		Writer outputStream = new DoNothingWriter();
		Reader inputStream = new ThrowingReader();
		
		try {
			copier.copy(inputStream, outputStream);
		} catch (IOException expected) {
		}
		
		assertTrue("output closed", outputWasClosed);
		assertTrue("input closed", inputWasClosed);
	}

	private void assertCopiesCorrectlyThisString(String string) throws IOException {
		CharStreamCopier copier = new CharStreamCopier();
		StringWriter outputStream = new StringWriter();
		StringReader inputStream = new StringReader(string);
		
		copier.copy(inputStream, outputStream);
		
		assertEquals(string, outputStream.toString());
	}
	
}
