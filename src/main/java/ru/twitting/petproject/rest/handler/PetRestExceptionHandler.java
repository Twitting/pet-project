package ru.twitting.petproject.rest.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.twitting.petproject.exception.BadRequestException;
import ru.twitting.petproject.exception.NotFoundException;
import ru.twitting.petproject.model.dto.BaseResponse;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j(topic = "PET-REST-EXCEPTION-HANDLER")
public class PetRestExceptionHandler {

    @ExceptionHandler({
            MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class,
            IllegalArgumentException.class, NumberFormatException.class,
            HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
            BadRequestException.class
    })
    ResponseEntity<BaseResponse> badRequestException(Exception e) {
        LOGGER.error("Bad request exception occured", e);
        return ResponseEntity.status(BAD_REQUEST).body(new BaseResponse(e.getMessage()));
    }


    @ExceptionHandler(NotFoundException.class)
    ResponseEntity exception(NotFoundException e) {
        LOGGER.error("Not found exception occured", e);
        return ResponseEntity.status(NOT_FOUND).body(new BaseResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity exception(Exception e) {
        LOGGER.error("An unknown exception was caught", e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new BaseResponse(e.getMessage()));
    }
}