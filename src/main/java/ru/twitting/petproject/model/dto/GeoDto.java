package ru.twitting.petproject.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeoDto {

    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;
    private Double radius;
    private String description;
}
