package ru.twitting.petproject.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    @ApiModelProperty(required = true)
    @JsonProperty(value = "message")
    protected final String message;

    private T body;

    public BaseResponse() {
        this.message = "Success";
    }

    public BaseResponse(T body) {
        this.message = "Success";
        this.body = body;
    }

    public BaseResponse(String message) {
        this.message = message;
    }
}