package com.amongalen.buildsvault.service;

import com.amongalen.buildsvault.model.BuildGuide;
import com.amongalen.buildsvault.repository.BuildGuideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BuildGuideServiceImpl implements BuildGuideService {
    private final BuildGuideRepository buildGuideRepository;

    @Override
    public List<BuildGuide> getAllGuides() {
        return buildGuideRepository.findAll();
    }

    @Override
    public Optional<BuildGuide> getGuideById(String id) {
        return buildGuideRepository.findById(id);
    }
}
