package ru.twitting.petproject.dao.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.dao.entity.TagEntity;
import ru.twitting.petproject.model.dto.ReportSearchParamsDto;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
public class ReportSpecification implements Specification<ReportEntity> {

    private final ReportSearchParamsDto searchParams;

    @Override
    public Predicate toPredicate(Root<ReportEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        Join<ReportEntity, TagEntity> tagJoin = root.join("tags");

        var predicates = new ArrayList<Predicate>();
        predicates.add(builder.equal(root.get("reportType"), searchParams.getReportType()));
        predicates.add(builder.equal(root.get("petType"), searchParams.getPetType()));
        Optional.ofNullable(searchParams.getSexType()).ifPresent(sexType ->
                predicates.add(builder.equal(root.get("sex"), searchParams.getSexType())));

        if (Optional.ofNullable(searchParams.getGeoLocation()).isPresent()) {
            query.orderBy(builder.asc(builder.function("public.ST_DistanceSphere", Double.class,
                    root.get("geoLocation"), builder.literal(searchParams.getGeoLocation()))));
            Optional.ofNullable(searchParams.getRadius()).ifPresent(radius ->
                    predicates.add(builder.lessThanOrEqualTo(builder.function("public.ST_DistanceSphere", Double.class,
                            root.get("geoLocation"), builder.literal(searchParams.getGeoLocation())), radius)));
            predicates.add(getTagFilterPredicate(builder, tagJoin));
        } else {
            sortResultByTagMatch(root, query, builder, tagJoin, predicates);
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private void sortResultByTagMatch(Root<ReportEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                      Join<ReportEntity, TagEntity> tagJoin, ArrayList<Predicate> predicates) {
        if (!searchParams.getTags().isEmpty()) {
            var inClause = builder.in(tagJoin.get("name"));
            searchParams.getTags().forEach(inClause::value);
            predicates.add(inClause);
            query.groupBy(root);
            query.orderBy(builder.desc(builder.count(root.get("id"))));
        }
    }

    private Predicate getTagFilterPredicate(CriteriaBuilder builder, Join<ReportEntity, TagEntity> tagJoin) {
        if (searchParams.getTags().isEmpty()) {
            return builder.conjunction();
        } else {
            return builder.or(searchParams.getTags()
                    .stream()
                    .map(tag -> builder.like(tagJoin.get("name"), tag))
                    .toArray(Predicate[]::new)
            );
        }
    }
}