package ru.twitting.petproject.config.swagger.tags;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Номер страницы (0..N).", defaultValue = "0"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Количество записей на странице.", defaultValue = "20"),
        @ApiImplicitParam(
                allowMultiple = true,
                name = "sort", dataType = "string", paramType = "query",
                value = "Сортировка в формате: property(,asc|desc). По-умолчанию, сортировка в порядке возрастания. " +
                        "Возможно множество критериев сортировки. Критерии сортировки - по неймингу в БД")}
)
public @interface ApiPageable {
}

