package ru.twitting.petproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.twitting.petproject.builder.ReportResponseBuilder;
import ru.twitting.petproject.dao.access.ReportAccessService;
import ru.twitting.petproject.model.dto.ReportSearchParamsDto;
import ru.twitting.petproject.model.dto.response.ReportResponse;
import ru.twitting.petproject.service.GetReportService;

@Service
@RequiredArgsConstructor
public class GetReportServiceImpl implements GetReportService {

    private final ReportAccessService reportAccessService;

    @Override
    @Transactional(readOnly = true)
    public Page<ReportResponse> getReports(ReportSearchParamsDto searchParams, Pageable pageable) {
        return reportAccessService.findAllByReportType(searchParams, pageable)
                .map(it -> new ReportResponseBuilder(it, searchParams.getGeoLocation()).build());
    }
}
