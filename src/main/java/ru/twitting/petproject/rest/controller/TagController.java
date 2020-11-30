package ru.twitting.petproject.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.twitting.petproject.config.swagger.tags.ApiPageable;
import ru.twitting.petproject.model.dto.BaseResponse;
import ru.twitting.petproject.service.GetTagService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@Api(value = "Tag controller")
@Validated
public class TagController {

    private final GetTagService getTagService;

    @ApiOperation(value = "Получить теги в порядке популярности")
    @GetMapping
    @ApiPageable
    public ResponseEntity<BaseResponse<Page<String>>> getPopularTags(@ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(new BaseResponse(getTagService.getTags(pageable)));
    }


}