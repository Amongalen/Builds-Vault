package com.amongalen.buildsvault.service;

import com.amongalen.buildsvault.model.BuildGuide;
import com.amongalen.buildsvault.repository.BuildGuideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BuildGuideServiceImpl implements BuildGuideService {
    private final BuildGuideRepository buildGuideRepository;

    @Override
    public List<BuildGuide> getAllGuides() {
        return buildGuideRepository.findAll();
    }
}
