package ru.twitting.petproject.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class GeoDto {

    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;
    @NotNull
    private Double radius;
    private String description;
}
