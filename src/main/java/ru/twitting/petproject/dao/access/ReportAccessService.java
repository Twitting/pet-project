package ru.twitting.petproject.dao.access;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.dao.repository.ReportRepository;
import ru.twitting.petproject.model.base.ReportType;

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

    public Page<ReportEntity> findAllByReportType(ReportType reportType, Pageable pageable) {
        return repository.findAllByReportType(reportType, pageable);
    }
}
