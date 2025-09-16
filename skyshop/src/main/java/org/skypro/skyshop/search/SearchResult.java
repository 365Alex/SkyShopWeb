package org.skypro.skyshop.search;


import java.util.UUID;

public class SearchResult {
    private final String name;
    private final String id;
    private final String contentType;

    public SearchResult (String id, String name, String contentType){
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public static SearchResult fromSearchable(Searchable search){
        return new SearchResult(search.getId().toString(), search.getName(), search.getContent());
    }
}
