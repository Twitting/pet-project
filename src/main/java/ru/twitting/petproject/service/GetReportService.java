package ru.twitting.petproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.twitting.petproject.model.dto.ReportSearchParamsDto;
import ru.twitting.petproject.model.dto.response.ReportResponse;

public interface GetReportService {

    Page<ReportResponse> getReports(ReportSearchParamsDto searchParams, Pageable pageable);
}
