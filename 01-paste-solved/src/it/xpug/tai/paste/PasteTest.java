package it.xpug.tai.paste;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;


import static org.junit.Assert.*;

public class PasteTest {

	Paste paste = new Paste();

	@Test
	public void empty_file_plus_empty_file_is_empty() throws Exception {
		assertResultIs("", "", "");
	}
	
	@Test
	public void one_line_plus_one_line_is_the_concatenation_of_the_two() throws Exception {
		assertResultIs("aabb\n", "aa", "bb");
	}

	@Test
	public void concatenates_three_lines_from_each_file() throws Exception {
		assertResultIs("aaxx\nbbyy\ncczz\n", "aa\nbb\ncc", "xx\nyy\nzz");
	}

	@Test
	public void concatenates_files_of_unequal_length() throws Exception {
		assertResultIs("aaxx\nbb\ncc\n", "aa\nbb\ncc", "xx");
		assertResultIs("xxaa\nbb\ncc\n", "xx", "aa\nbb\ncc");
	}

	@Test
	public void concatenates_more_than_two_files() throws Exception {
		deleteFile("output.txt");
		createFileWithContents("file0", "A\nB");
		createFileWithContents("file1", "C\nD");
		createFileWithContents("file2", "E\nF");
		
		paste.paste("output.txt", new String[] {"file0", "file1", "file2"});
		
		assertEquals("ACE\nBDF\n", contentsOfFile("output.txt"));
	}
	
	@Test
	public void optionally_numbers_lines() throws Exception {
		paste.setLineNumbers();
		assertResultIs("1 aaxx\n2 bbyy\n", "aa\nbb", "xx\nyy");		
	}
	
	@Test
	public void optionally_adds_separator() throws Exception {
		paste.setSeparator(", ");
		assertResultIs("aa, xx\nbb, yy\n", "aa\nbb", "xx\nyy");		
	}
	
	private void assertResultIs(String expected, String contentsOfFirstFile, String contentsOfSecondFile) throws IOException {
		deleteFile("output.txt");
		createFileWithContents("file0", contentsOfFirstFile);
		createFileWithContents("file1", contentsOfSecondFile);
		
		paste.paste("output.txt", "file0", "file1");
		
		assertEquals(expected, contentsOfFile("output.txt"));
	}
	
	private void createFileWithContents(String fileName, String contents) throws IOException {
		FileWriter writer = new FileWriter(fileName);
		writer.write(contents);
		writer.close();		
	}

	private void deleteFile(String fileName) {
		new File(fileName).delete();
	}

	private String contentsOfFile(String fileName) throws IOException {
		String result = "";
		FileReader reader = new FileReader(fileName);
		int c;
		while (-1 != (c = reader.read())) {
			result += (char) c;
		}
		return result;
	}
}
