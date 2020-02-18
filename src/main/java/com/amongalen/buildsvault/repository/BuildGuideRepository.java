package com.amongalen.buildsvault.repository;

import com.amongalen.buildsvault.model.BuildGuide;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BuildGuideRepository extends MongoRepository<BuildGuide,String> {
    List<BuildGuide> findAllByName(String name);
}
