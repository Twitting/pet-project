package ru.twitting.petproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.twitting.petproject.model.base.ReportType;
import ru.twitting.petproject.model.dto.response.ReportResponse;

public interface GetReportService {

    Page<ReportResponse> getReports(ReportType reportType, Pageable pageable);
}
