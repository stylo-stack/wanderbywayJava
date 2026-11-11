package com.herbert.wanderbyway.utils.rest;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class RestUtils {
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final HttpHeaders headers = new HttpHeaders();

    public RestUtils(RestTemplate restTemplate, String baseUrl, List<Header> headers) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.forEach(header -> this.headers.set(header.key(), header.value()));
    }

    public RestUtils(RestTemplate restTemplate, String baseUrl) {
        this(restTemplate, baseUrl, List.of());
    }

    public <T> T call(String endpoint, HttpMethod method, Class<T> responseType) {
        return callApi(uriBuilder(endpoint).build().toUri(), method, responseType);
    }

    public <T> T call(String endpoint, HttpMethod method, SearchParams params, Class<T> responseType) {
        return callApi(uriBuilder(endpoint).queryParams(params.toMap()).build().toUri(), method, responseType);
    }

    private UriComponentsBuilder uriBuilder(String endpoint) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl + endpoint);
    }

    private <T> T callApi(URI uri, HttpMethod method, Class<T> responseType) {
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(uri, method, request, responseType);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return null;
    }
}
