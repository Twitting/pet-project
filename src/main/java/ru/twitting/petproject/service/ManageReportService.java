package ru.twitting.petproject.service;

import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.model.dto.response.ReportResponse;

public interface ManageReportService {

    ReportResponse createReport(CreateReportRequest request);

    void closeReport(Long reportId);
}
