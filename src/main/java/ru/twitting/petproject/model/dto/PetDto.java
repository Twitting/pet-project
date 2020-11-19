package ru.twitting.petproject.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.twitting.petproject.model.base.PetType;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetDto {

    private String name;
    @NotNull
    private PetType type;
    private String breed;
    private Set<String> tags;
    private Set<String> photos;

}
