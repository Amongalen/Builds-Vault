package com.amongalen.buildsvault.model.build;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Calcs {
    @JsonProperty("Input")
    CalcsInput[] inputs;

    @JsonProperty("Section")
    CalcsSection[] sections;
}
