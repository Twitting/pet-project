package ru.twitting.petproject.service;

import ru.twitting.petproject.model.dto.request.CreateReportRequest;

public interface CreateReportService {

    void createReport(CreateReportRequest request);
}
