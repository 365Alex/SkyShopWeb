package org.skypro.skyshop.article;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private String title;
    private final String text;
    private final UUID id;

    public Article(UUID id, String articleTitle, String text){
        this.id = id;
        this.title = articleTitle;
        this.text = text;
    }

    public UUID getId(){return id;}

    public String getTitle(){
        return title;
    }

    public String getTextOfTheArticle(){
        return text;
    }

    public String toString(){
        return "Название статьи: " + title + "\n" + " Текст статьи: " + text + "\n";
    }


    @JsonIgnore
    public String searchTerm(){
        return toString();
    }

    @JsonIgnore
    public String getContent(){
        return "ARTICLE";
    }

    @Override
    public String getName(){
        return title;
    }

    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Article article = (Article) obj;
        return title.equals(article.title);
    }
    public int hashCode(){
        return Objects.hash(title);
    }

}
