package com.amongalen.buildsvault.service;

import com.amongalen.buildsvault.model.BuildGuide;

import java.util.List;
import java.util.Optional;

public interface BuildGuideService {
    List<BuildGuide> getAllGuides();
    Optional<BuildGuide> getGuideById(String id);
}
