package com.amongalen.buildsvault.model.pob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoBCalcs {
    @JsonProperty("Input")
    PoBCalcsInput[] inputs;

    @JsonProperty("Section")
    PoBCalcsSection[] sections;
}
