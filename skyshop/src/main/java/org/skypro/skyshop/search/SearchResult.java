package org.skypro.skyshop.search;


public class SearchResult {
    private final String name;
    private final String id;
    private final String contentType;

    public SearchResult (String id, String name, String contentType){
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }
    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }
    public String getContentType(){
        return contentType;
    }

    public static SearchResult fromSearchable(org.skypro.skyshop.search.Searchable search){
        return new SearchResult(search.getId().toString(), search.getName(), search.getContent());
    }
}
