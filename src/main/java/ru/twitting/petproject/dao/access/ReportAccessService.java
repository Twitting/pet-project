package ru.twitting.petproject.dao.access;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.dao.repository.ReportRepository;
import ru.twitting.petproject.dao.specification.ReportSpecification;
import ru.twitting.petproject.exception.NotFoundException;
import ru.twitting.petproject.model.dto.ReportSearchParamsDto;

import javax.transaction.Transactional;

@Service
@Slf4j(topic = "PET-ACCESS-SERVICE")
@RequiredArgsConstructor
@Transactional
public class ReportAccessService {

    private final ReportRepository repository;

    public ReportEntity save(ReportEntity entity) {
        return repository.save(entity);
    }

    public Page<ReportEntity> findAllByReportType(ReportSearchParamsDto searchParams, Pageable pageable) {
        return repository.findAll(Specification.where(new ReportSpecification(searchParams)), pageable);
    }

    public ReportEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Not found report with id %d", id)));
    }

    public Page<ReportEntity> findAllByUsername(String username, Pageable pageable) {
        return repository.findAllByUser_Username(username, pageable);
    }

    public void delete(ReportEntity entity) {
        LOGGER.debug("Entity with id {} was soft-deleted", entity.getId());
        repository.delete(entity);
    }
}
