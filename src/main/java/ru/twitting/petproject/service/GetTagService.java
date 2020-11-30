package ru.twitting.petproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetTagService {

    Page<String> getTags(Pageable pageable);
}
