package com.herbert.wanderbyway.dataprovider.database.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AirportModelRepository extends JpaRepository<AirportModel, Integer> {
    List<AirportModel> findBySlugContaining(String slug);
    List<AirportModel> findByIataIn(List<String> iatas);

    AirportModel findByIata(String iata);
}
