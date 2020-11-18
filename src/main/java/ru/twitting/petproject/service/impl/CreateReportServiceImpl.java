package ru.twitting.petproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.twitting.petproject.builder.ReportEntityBuilder;
import ru.twitting.petproject.dao.access.ReportAccessService;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.service.CreateReportService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReportServiceImpl implements CreateReportService {

    private final ReportEntityBuilder reportEntityBuilder;
    private final ReportAccessService reportAccessService;

    @Override
    @Transactional
    public void createReport(CreateReportRequest request) {
        var report = reportEntityBuilder.build(request);
        reportAccessService.save(report);
    }
}
