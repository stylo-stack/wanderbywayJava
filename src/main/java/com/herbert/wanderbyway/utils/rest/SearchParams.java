package com.herbert.wanderbyway.utils.rest;

import org.springframework.util.MultiValueMap;

public interface SearchParams {
    MultiValueMap<String, String>  toMap();
}
