package com.herbert.wanderbyway.entryPoint.rest.search;

import com.herbert.wanderbyway.core.search.entity.SearchItem;
import com.herbert.wanderbyway.core.search.entity.SearchItemType;
import com.herbert.wanderbyway.core.search.entity.SearchOptions;
import com.herbert.wanderbyway.core.search.useCases.FindAllByNameUseCase;
import com.herbert.wanderbyway.core.search.useCases.FindByIdAndTypeUseCase;
import com.herbert.wanderbyway.utils.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchRestController {
    private final FindAllByNameUseCase findAllByNameUseCase;
    private final FindByIdAndTypeUseCase findByIdAndTypeUseCase;

    public SearchRestController(
            FindAllByNameUseCase findAllByNameUseCase,
            FindByIdAndTypeUseCase findByIdAndTypeUseCase
    ) {
        this.findAllByNameUseCase = findAllByNameUseCase;
        this.findByIdAndTypeUseCase = findByIdAndTypeUseCase;
    }
    @Cacheable("searchByName")
    @GetMapping("/{query}")
    SearchResult searchByName(
            @PathVariable String query,
            @RequestParam(required = false) Boolean airport,
            @RequestParam(required = false) Boolean train,
            @RequestParam(required = false) Boolean city,
            @RequestParam(required = false) Boolean port,
            @RequestParam(required = false) Boolean bus
            ){
        SearchOptions options = new SearchOptions(airport, train, city, port, bus);
        String normalisedQueryString = StringUtils.removeAccentsAndSpecialCharacters(query);
        List<SearchItem> results = findAllByNameUseCase.findAllByName(normalisedQueryString, options);
        List<SearchItem> sortedResults = this.sortResultsBySimilarity(results, normalisedQueryString);
        return new SearchResult(results.size(), options, sortedResults);
    }
    @Cacheable("searchById")
    @GetMapping("place/{id}")
    SearchItem getById(
            @PathVariable int id,
            @RequestParam(required = true) SearchItemType type
    ){
        return findByIdAndTypeUseCase.findByIdAndType(id, type);
    }

    private List<SearchItem> sortResultsBySimilarity(List<SearchItem> items, String target) {
        items.sort(Comparator.comparingInt((SearchItem a) -> StringUtils.getLevenshteinDistance(a.getSlug(), target)));
        return items;
    }
}
