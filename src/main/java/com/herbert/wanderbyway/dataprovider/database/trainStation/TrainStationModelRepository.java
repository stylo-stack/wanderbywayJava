package com.herbert.wanderbyway.dataprovider.database.trainStation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainStationModelRepository extends JpaRepository<TrainStationModel, Integer> {
    List<TrainStationModel> findBySlugContaining(String slug);
    List<TrainStationModel> findByDbIdIn(List<String> dbIds);
    @Query("SELECT t FROM TrainStationModel t WHERE t.slug LIKE %:slug% AND t.dbId IS NOT NULL")
    List<TrainStationModel> findBySlugContainingAndDbIdIsPresent(String slug);
}
