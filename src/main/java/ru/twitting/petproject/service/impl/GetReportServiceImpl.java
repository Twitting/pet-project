package ru.twitting.petproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.twitting.petproject.builder.ReportResponseBuilder;
import ru.twitting.petproject.builder.ShortReportResponseBuilder;
import ru.twitting.petproject.dao.access.ReportAccessService;
import ru.twitting.petproject.model.dto.ReportSearchParamsDto;
import ru.twitting.petproject.model.dto.response.ReportResponse;
import ru.twitting.petproject.model.dto.response.ShortReportResponse;
import ru.twitting.petproject.service.GetReportService;

@Service
@RequiredArgsConstructor
public class GetReportServiceImpl implements GetReportService {

    private final ReportAccessService reportAccessService;

    @Override
    @Transactional(readOnly = true)
    public Page<ShortReportResponse> getReports(ReportSearchParamsDto searchParams, Pageable pageable) {
        return reportAccessService.findAllByReportType(searchParams, pageable)
                .map(it -> new ShortReportResponseBuilder(it, searchParams.getGeoLocation()).build());
    }

    @Override
    public Page<ShortReportResponse> getUserReports(Pageable pageable) {
        var context = SecurityContextHolder.getContext();
        var username = context.getAuthentication().getName();
        return reportAccessService.findAllByUsername(username, pageable)
                .map(it -> new ShortReportResponseBuilder(it).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ReportResponse getReport(Long reportId, Point point) {
        return new ReportResponseBuilder(reportAccessService.findById(reportId), point).build();
    }
}
