package ru.twitting.petproject.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.twitting.petproject.model.base.PetType;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class PetDto {

    @NotNull
    private String name;
    @NotNull
    private PetType type;
    private String breed;
    private Set<String> tags;
    private Set<String> photos;

}
