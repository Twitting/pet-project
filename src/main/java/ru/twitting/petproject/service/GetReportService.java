package ru.twitting.petproject.service;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.twitting.petproject.model.dto.ReportSearchParamsDto;
import ru.twitting.petproject.model.dto.response.ReportResponse;
import ru.twitting.petproject.model.dto.response.ShortReportResponse;


public interface GetReportService {

    Page<ShortReportResponse> getReports(ReportSearchParamsDto searchParams, Pageable pageable);

    Page<ShortReportResponse> getUserReports(Pageable pageable);

    ReportResponse getReport(Long reportId, Point point);
}
