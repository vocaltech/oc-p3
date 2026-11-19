package com.equipit.equipment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EquipmentExceptionHandler {
    @ExceptionHandler(EquipmentStateException.class)
    public ProblemDetail handleEquipmentState(EquipmentStateException exc) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Equipment state conflict");
        problemDetail.setDetail(exc.getMessage());

        return problemDetail;
    }
}
