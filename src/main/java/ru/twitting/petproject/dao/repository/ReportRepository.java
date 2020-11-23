package ru.twitting.petproject.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.twitting.petproject.dao.entity.ReportEntity;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long>, JpaSpecificationExecutor<ReportEntity> {

//    Page<ReportEntity> findAllByReportType(Specification<ReportEntity> specification, Pageable pageable);

}
