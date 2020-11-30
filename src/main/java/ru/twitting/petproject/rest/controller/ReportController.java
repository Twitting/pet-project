package ru.twitting.petproject.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.twitting.petproject.config.swagger.tags.ApiPageable;
import ru.twitting.petproject.model.base.PetType;
import ru.twitting.petproject.model.base.ReportType;
import ru.twitting.petproject.model.base.SexType;
import ru.twitting.petproject.model.dto.BaseResponse;
import ru.twitting.petproject.model.dto.ReportSearchParamsDto;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.model.dto.response.ReportResponse;
import ru.twitting.petproject.service.CreateReportService;
import ru.twitting.petproject.service.GetReportService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import java.util.Set;

import static ru.twitting.petproject.util.PointUtils.ofPostGisNullable;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Api(value = "Report controller")
@Validated
public class ReportController {

    private final CreateReportService createReportService;
    private final GetReportService getReportService;

    @ApiOperation(value = "Создать объявление о пропаже/находке")
    @PostMapping
    public ResponseEntity<BaseResponse> createReport(@Valid @RequestBody CreateReportRequest request) {
        createReportService.createReport(request);
        return ResponseEntity.ok(new BaseResponse());
    }

    @ApiOperation(value = "Получить страницу с объявлениями")
    @GetMapping
    @ApiPageable
    public ResponseEntity<BaseResponse<Page<ReportResponse>>> getReports(@ApiIgnore("Сокрытие пагинации") Pageable pageable,
                                                                         @RequestParam ReportType reportType,
                                                                         @RequestParam PetType petType,
                                                                         @RequestParam (required = false, defaultValue = "") SexType sexType,
                                                                         @RequestParam (required = false, defaultValue = "") Double latitude,
                                                                         @RequestParam (required = false, defaultValue = "") Double longitude,
                                                                         @RequestParam (required = false, defaultValue = "") Double radius,
                                                                         @RequestParam (required = false, defaultValue = "", name = "tag") Set<String> tags) {
        var searchParams = ReportSearchParamsDto.builder()
                .tags(tags)
                .radius(radius)
                .geoLocation(ofPostGisNullable(latitude, longitude))
                .petType(petType)
                .sexType(sexType)
                .reportType(reportType)
                .build();
        return ResponseEntity.ok(new BaseResponse(getReportService.getReports(searchParams, pageable)));
    }


}