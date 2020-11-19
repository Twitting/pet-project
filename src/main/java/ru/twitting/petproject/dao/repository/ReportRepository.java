package ru.twitting.petproject.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.model.base.ReportType;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    Page<ReportEntity> findAllByReportType(ReportType reportType, Pageable pageable);

}
