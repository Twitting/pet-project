package ru.twitting.petproject.dao.access;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.twitting.petproject.dao.entity.TagEntity;
import ru.twitting.petproject.dao.repository.TagRepository;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j(topic = "PET-ACCESS-SERVICE")
@RequiredArgsConstructor
@Transactional
public class TagAccessService {

    private final TagRepository repository;

    public Set<TagEntity> findOrCreateByNames(Set<String> names) {
        var result = repository.findAllByNameIn(names)
                .stream()
                .collect(Collectors.toMap(TagEntity::getName, Function.identity()));
        return names.stream()
                .map(it -> result.getOrDefault(it, new TagEntity(it)))
                .collect(Collectors.toSet());
    }

}
