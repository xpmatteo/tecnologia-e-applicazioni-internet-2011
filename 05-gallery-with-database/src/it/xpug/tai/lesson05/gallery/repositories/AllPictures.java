package it.xpug.tai.lesson05.gallery.repositories;

import java.util.List;

import it.xpug.tai.lesson05.gallery.model.Picture;

public interface AllPictures {
    // storage
    Object add(Picture picture);
    void remove(Object pictureId);
    
    // retrieval
    List<Picture> withId(Object pictureId);
    List<Picture> all();
    
    // search
    List<Picture> withAuthor(String authorName);
    List<Picture> paintedInYears(int startYear, int endYear);
}