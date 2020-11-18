package ru.twitting.petproject.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tag")
@NoArgsConstructor
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tagSeqGenerator")
    @SequenceGenerator(name = "tagSeqGenerator", sequenceName = "tag_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public TagEntity(String name) {
        this.name = name;
    }
}
