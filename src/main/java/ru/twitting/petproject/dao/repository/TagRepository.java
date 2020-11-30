package ru.twitting.petproject.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.twitting.petproject.dao.entity.TagEntity;

import java.util.Collection;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

    Set<TagEntity> findAllByNameIn(Collection<String> names);

    @Query(value = "SELECT t.*, count(report_id) " +
            "FROM tag t " +
            "JOIN report_tag rt " +
            "ON t.id = rt.tag_id " +
            "GROUP BY t.id ",
            countQuery = "SELECT * FROM pet_project.tag ",
            nativeQuery = true)
    Page<TagEntity> findAllOrderByPopularity(Pageable pageable);
}
