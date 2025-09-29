package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.search.SearchResult;

import java.util.Collection;
import java.util.Collections;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
