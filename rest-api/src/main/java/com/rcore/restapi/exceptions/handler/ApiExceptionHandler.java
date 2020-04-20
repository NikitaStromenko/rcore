package com.rcore.restapi.exceptions.handler;

import com.rcore.restapi.exceptions.BadRequestApiException;
import com.rcore.restapi.exceptions.ExceptionDTO;
import com.rcore.restapi.exceptions.NotFoundApiException;
import com.rcore.restapi.web.api.response.ErrorApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestApiException.class)
    public ErrorApiResponse<ExceptionDTO> handleBadRequestApiException(BadRequestApiException e) {
        return ErrorApiResponse.of(e.getError());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundApiException.class)
    public ErrorApiResponse<ExceptionDTO> handleNotFountApiException(NotFoundApiException e) {
        return ErrorApiResponse.of(e.getError());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ErrorApiResponse<ExceptionDTO> handleInternalServerError(Exception e) {
        e.printStackTrace();
        return ErrorApiResponse.of(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .message("Ошибка сервера. Попробуйте повторить позже")
                        .build())
                .domain("SERVER")
                .details(e.getMessage())
                .build());
    }

}