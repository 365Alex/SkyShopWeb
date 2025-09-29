package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchResult;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageServiceOne;
    @InjectMocks
    private SearchService searchServiceOne;
    @Test
    public void searchInCaseOfAbsenceOfObjects() {
        when(storageServiceOne.getSearchable()).thenReturn(Collections.emptyList());
        Collection<SearchResult> result = searchServiceOne.search("лобстер");
        assertTrue(result.isEmpty());
    }

    @Test
    public void search_ProductFound() {
        when(storageServiceOne.getSearchable()).thenReturn(List.of(
                new SimpleProduct(UUID.randomUUID(), "лобстер", 100)));
        List<SearchResult> results = searchServiceOne.search("лобстер");
        assertFalse(results.isEmpty());
        assertEquals(results.get(0).getName(), "лобстер");
    }

    @Test
    public void SearchInCaseThereAreObjectsButNoSuitableOne(){
        when(storageServiceOne.getSearchable()).thenReturn(List.of(
                new Article(UUID.randomUUID(), "рецепты приготовления блинчиков", "Домашние блинчики с начинкой")));
        List<SearchResult> result = searchServiceOne.search("блинчики");
        assertFalse(result.isEmpty());
    }
}
