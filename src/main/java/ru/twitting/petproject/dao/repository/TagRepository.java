package ru.twitting.petproject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.twitting.petproject.dao.entity.TagEntity;
import ru.twitting.petproject.dao.entity.UserEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

    Set<TagEntity> findAllByNameIn(Collection<String> names);

}
