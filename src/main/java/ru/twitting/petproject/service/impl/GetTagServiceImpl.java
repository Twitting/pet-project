package ru.twitting.petproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.twitting.petproject.dao.access.TagAccessService;
import ru.twitting.petproject.service.GetTagService;

@Service
@RequiredArgsConstructor
public class GetTagServiceImpl implements GetTagService {

    private final TagAccessService tagAccessService;

    @Override
    @Transactional(readOnly = true)
    public Page<String> getTags(Pageable pageable) {
        return tagAccessService.findAllNames(pageable);
    }
}
