package org.skypro.skyshop.search;
import java.util.UUID;

public interface Searchable {

   String searchTerm();

    String getContent();

    default String getStringRepresentation(){
        return getName() + " - " + getContent();
    }

    String getName();
    UUID getId();

}
