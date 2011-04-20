package it.xpug.tai.lesson05.gallery.jdbc;


import it.xpug.tai.lesson05.gallery.jdbc.Database;
import it.xpug.tai.lesson05.gallery.jdbc.ListOfRows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {
	
	private Database database;
	private Connection connection;
	
	@Before
	public void setUp() throws Exception {
		connection = getConnection();
		database = new Database(connection);
		drop_temp_table();
		create_test_table();
	}
	
	@After
	public void tearDown() throws Exception {
		connection.close();
	}
	
	@Test
	public void insertThenSelect() throws Exception {
		database.execute("insert into prova (name) values (?)", "pippo");

		ListOfRows rows = database.select("select * from prova");
		assertEquals(1, rows.size());
		assertEquals("pippo", rows.get(0).get("name"));
	}

	private void drop_temp_table() {
		try {
			database.execute("drop table prova");
		} catch (Exception ignored) {}
	}

	private void create_test_table() throws SQLException, ClassNotFoundException {
		String sql = "create table prova (id integer identity, name varchar(255), primary key(id))";
		database.execute(sql);
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");
		return DriverManager.getConnection("jdbc:hsqldb:bin/test", "sa", "");
	}
}
