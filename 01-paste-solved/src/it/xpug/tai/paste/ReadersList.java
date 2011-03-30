package it.xpug.tai.paste;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadersList {

	private List<BufferedReader> readers;

	public ReadersList(String[] fileNames) throws FileNotFoundException {
		readers = new ArrayList<BufferedReader>();
		for (int i = 0; i < fileNames.length; i++) {
			readers.add(new BufferedReader(new FileReader(fileNames[i])));
		}
	}
	
	public LinesList readAll() throws IOException {
		LinesList result = new LinesList();
		for (BufferedReader reader : readers) {
			result.add(reader.readLine());
		}
		return result;
	}

}
