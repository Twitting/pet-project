package ru.twitting.petproject.service;

import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.model.dto.response.ReportResponse;

public interface CreateReportService {

    ReportResponse createReport(CreateReportRequest request);
}
