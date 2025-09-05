package org.skypro.skyshop.service;

import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchService {
private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String term){
        List<SearchResult> searchResults = storageService.stream().filter(s-> s.searchTerm().
                contains(term)).collect(Collectors.toCollection(()-> new ArrayList<SearchResult>()));
        return searchResults;
    }


}
