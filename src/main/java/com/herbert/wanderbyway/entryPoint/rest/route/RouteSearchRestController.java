package com.herbert.wanderbyway.entryPoint.rest.route;

import com.herbert.wanderbyway.core.routeSearch.entity.RouteDetails;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchItemPlaceType;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchResult;
import com.herbert.wanderbyway.core.routeSearch.useCases.FindRoutesFromPlaceUseCase;
import com.herbert.wanderbyway.core.routeSearch.useCases.GetRouteDetailsUseCase;
import com.herbert.wanderbyway.entryPoint.rest.route.entity.RouteSearchQueryResult;
import com.herbert.wanderbyway.exceptions.NotFoundException;
import com.herbert.wanderbyway.utils.DateFormat;
import com.herbert.wanderbyway.utils.DateUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteSearchRestController {
    private final FindRoutesFromPlaceUseCase findRoutesFromPlaceUseCase;
    private final RouteSearchRestMapper routeSearchRestMapper;
    private final GetRouteDetailsUseCase getRouteDetailsUseCase;

    public RouteSearchRestController(
            FindRoutesFromPlaceUseCase findRoutesFromPlaceUseCase,
            RouteSearchRestMapper routeSearchRestMapper,
            GetRouteDetailsUseCase getRouteDetailsUseCase
    ) {
        this.findRoutesFromPlaceUseCase = findRoutesFromPlaceUseCase;
        this.routeSearchRestMapper = routeSearchRestMapper;
        this.getRouteDetailsUseCase = getRouteDetailsUseCase;
    }

    @Cacheable("routeSearch")
    @GetMapping("search/{id}")
    RouteSearchQueryResult getRoutes(
            @PathVariable int id,
            @RequestParam RouteSearchItemPlaceType type,
            @RequestParam String startDate
    ) {
        Date start;
        try {
            start = DateUtils.parseDate(startDate, DateFormat.ENTRY);
        } catch (IllegalArgumentException error) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage());
        }

        try {
            RouteSearchResult routes = findRoutesFromPlaceUseCase.findRoutes(id, type, start, start);
            return routeSearchRestMapper.toRouteSearchQueryResult(routes);
        } catch (NotFoundException error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nothing found for id " + id + " and type " + type.name());
        }
    }

    @Cacheable("routeDetails")
    @GetMapping("/{id}")
    List<RouteDetails> getRouteDetails(@PathVariable String id, @RequestParam RouteSearchItemPlaceType type) {
        List<String> ids = List.of(id.split(","));
        return getRouteDetailsUseCase.findRouteDetails(ids, type);
    }
}
