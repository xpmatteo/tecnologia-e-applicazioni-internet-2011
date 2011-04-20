package it.xpug.tai.lesson05.gallery.repositories;

import it.xpug.tai.lesson05.gallery.jdbc.Database;
import it.xpug.tai.lesson05.gallery.model.Picture;

import java.util.List;

public class AllPicturesInDatabase implements AllPictures {

	public AllPicturesInDatabase(Database database) {
	}

	@Override
	public Object add(Picture picture) {
		// Should return the new object's id, which I can find with 
		// "select top 1 identity() from pictures"
		throw new RuntimeException("Not yet implemented!");
	}

	@Override
	public void remove(Object pictureId) {
		throw new RuntimeException("Not yet implemented!");
	}

	@Override
	public List<Picture> withId(Object pictureId) {
		throw new RuntimeException("Not yet implemented!");
	}

	@Override
	public List<Picture> withAuthor(String authorName) {
		throw new RuntimeException("Not yet implemented!");
	}

	@Override
	public List<Picture> paintedInYears(int startYear, int endYear) {
		throw new RuntimeException("Not yet implemented!");
	}

	@Override
	public List<Picture> all() {
		throw new RuntimeException("Not yet implemented!");
	}
	
	public void deleteAll() {
		throw new RuntimeException("Not yet implemented!");
	}

}
