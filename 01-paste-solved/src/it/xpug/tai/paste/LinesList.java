package it.xpug.tai.paste;

import java.util.ArrayList;
import java.util.List;

public class LinesList {

	private List<String> lines = new ArrayList<String>();
	
	public void add(String line) {
		lines.add(line);
	}
	
	public boolean notAllNull() {
		for (String string : lines) {
			if (string != null)
				return true;
		}
		return false;
	}

	public String concatenate(String separator) {
		String result = "";
		for (String line : lines) {
			if (!result.isEmpty()) {
				result += separator;
			}
			result += formatLine(line);
		}
		return result;
	}

	private String formatLine(String line) {
		return null == line ? "" : line;
	}

}
