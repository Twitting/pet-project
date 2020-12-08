package ru.twitting.petproject.dao.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.locationtech.jts.geom.Point;
import ru.twitting.petproject.converter.PhotoAttributeConverter;
import ru.twitting.petproject.model.base.PetType;
import ru.twitting.petproject.model.base.ReportType;
import ru.twitting.petproject.model.base.SexType;
import ru.twitting.petproject.util.jackson.PointJacksonSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "report")
@Where(clause = "active = true")
@SQLDelete(sql = "UPDATE report SET active = false, modified = current_timestamp WHERE id = ?", check = ResultCheckStyle.COUNT)
public class ReportEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportSeqGenerator")
    @SequenceGenerator(name = "reportSeqGenerator", sequenceName = "report_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "pet_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetType petType;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private SexType sex;

    @Column(name = "report_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Column(name = "breed")
    private String breed;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(
            name = "report_tag",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"report_id", "tag_id"}))
    private Set<TagEntity> tags = new HashSet<>();

    @Convert(converter = PhotoAttributeConverter.class)
    @Column(name = "photos")
    private Set<String> photos;

    @JsonSerialize(using = PointJacksonSerializer.class)
    @Column(name = "geo_location", nullable = false, columnDefinition = "geometry(Point,4326)")
    private Point geoLocation;

    @Column(name = "radius")
    private Double radius;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "geo_description")
    private String geoDescription;

    @Column(name = "lost_found_date", nullable = false)
    private LocalDate lostFoundDate;
}
