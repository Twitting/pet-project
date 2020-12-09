package ru.twitting.petproject.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.twitting.petproject.model.base.PetType;
import ru.twitting.petproject.model.base.SexType;

@Data
@NoArgsConstructor
public class ShortReportResponse {

    private Long id;
    private Double latitude;
    private Double longitude;
    private PetType type;
    private SexType sex;
    private String breed;
    private String comment;
    private Double distance;

}
