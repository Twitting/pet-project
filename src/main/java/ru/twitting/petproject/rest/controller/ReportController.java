package ru.twitting.petproject.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.twitting.petproject.model.dto.BaseResponse;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.service.CreateReportService;

import javax.validation.Valid;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Api(value = "Report controller")
@Validated
public class ReportController {

    private final CreateReportService createReportService;

    @ApiOperation(value = "Создать объявление о пропаже")
    @PostMapping
    public ResponseEntity<BaseResponse> createReport(@Valid @RequestBody CreateReportRequest request) {
        createReportService.createReport(request);
        return ResponseEntity.ok(new BaseResponse());
    }
}