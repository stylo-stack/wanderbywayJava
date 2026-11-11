package com.herbert.wanderbyway.dataprovider.database.city;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityModelRepository extends JpaRepository<CityModel, Integer> {
    List<CityModel> findBySlugContaining(String slug);
}
