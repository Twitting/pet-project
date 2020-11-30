package ru.twitting.petproject.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ReportPhotos {

    @JsonProperty(value = "photos")
    private Set<String> photos = new HashSet<>();

    public ReportPhotos(Set<String> photos) {
        this.photos = photos;
    }
}
