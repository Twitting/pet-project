package ru.twitting.petproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.twitting.petproject.builder.ReportEntityBuilder;
import ru.twitting.petproject.builder.ReportResponseBuilder;
import ru.twitting.petproject.dao.access.ReportAccessService;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.model.dto.response.ReportResponse;
import ru.twitting.petproject.service.ManageReportService;


import static ru.twitting.petproject.util.UserVerificator.checkUsername;

@Service
@RequiredArgsConstructor
public class ManageReportServiceImpl implements ManageReportService {

    private final ReportEntityBuilder reportEntityBuilder;
    private final ReportAccessService reportAccessService;

    @Override
    @Transactional
    public ReportResponse createReport(CreateReportRequest request) {
        var report = reportEntityBuilder.build(request);
        var savedReport = reportAccessService.save(report);
        return new ReportResponseBuilder(savedReport).build();
    }

    @Override
    @Transactional
    public void closeReport(Long reportId) {
        var report = reportAccessService.findById(reportId);
        checkUsername(report.getUser().getUsername());
        reportAccessService.delete(report);
    }
}
