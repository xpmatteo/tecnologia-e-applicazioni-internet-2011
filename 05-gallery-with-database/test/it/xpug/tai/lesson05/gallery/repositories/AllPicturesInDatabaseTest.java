package it.xpug.tai.lesson05.gallery.repositories;

import it.xpug.tai.lesson05.gallery.jdbc.Database;
import it.xpug.tai.lesson05.gallery.model.Picture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AllPicturesInDatabaseTest {

	private final static String URL = "jdbc:hsqldb:file:./db/databases/gallery_test;shutdown=true";
	private final static String USERNAME = "sa";
	private final static String PASSWORD = "";
	
	private AllPicturesInDatabase allPictures;
	private Database database;
	private Connection connection;
	
	@Before
	public void setUp() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		database = new Database(connection);
		allPictures = new AllPicturesInDatabase(database);
		allPictures.deleteAll();
	}
	
	@After
	public void tearDown() throws Exception {
		connection.commit();
		database.execute("shutdown");
		connection.close();
	}
	
	
	@Test
	public void returnsAllRows() throws Exception {
		assertEquals(0, allPictures.all().size());
		allPictures.add(new Picture());
		allPictures.add(new Picture());
		assertEquals(1, allPictures.all().size());
	}
	
	@Test
	public void loadsAllAttributes() throws Exception {
		Picture original = new Picture();
		original.set("title", "a title");
		original.set("author", "an author");
		original.set("url", "a url");
		allPictures.add(original);
		
		List<Picture> all = allPictures.all();
		assertEquals(1, all.size());

		Picture actual = all.get(0);
		assertEquals("a title", actual.get("title"));
		assertEquals("an author", actual.get("author"));
		assertEquals("a url", actual.get("url"));
	}
}
