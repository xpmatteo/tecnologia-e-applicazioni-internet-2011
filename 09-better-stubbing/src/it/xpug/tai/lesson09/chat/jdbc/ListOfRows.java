package it.xpug.tai.lesson09.chat.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfRows {
	private List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
	private HashMap<String, Object> currentRow;

	public void newRow() {
		currentRow = new HashMap<String, Object>();
		rows.add(currentRow);
	}

	public void put(String columnName, Object object) {
		currentRow.put(columnName.toLowerCase(), object);
	}

	public int size() {
		return rows.size();
	}

	public Map<String, Object> get(int rowIndex) {
		return rows.get(rowIndex);
	}
	
}
