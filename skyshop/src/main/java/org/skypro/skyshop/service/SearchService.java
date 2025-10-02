package org.skypro.skyshop.service;

import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchService {
    private final StorageService storageService;


    @Autowired
    public SearchService(StorageService storageService) {
        this.storageService = storageService;

    }

    public List<SearchResult> search(String term) {
        return storageService.getSearchable().stream().filter(s -> s.searchTerm().contains(term)).
                map(SearchResult::fromSearchable).collect(Collectors.toList());
    }
}