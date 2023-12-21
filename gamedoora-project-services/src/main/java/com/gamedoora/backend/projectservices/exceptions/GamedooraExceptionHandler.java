package com.gamedoora.backend.projectservices.exceptions;

@ControllerAdvice
public class GamedooraExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) {
        GamedooraExceptionResponseEntity bodyOfResponse =
                GamedooraExceptionResponseEntity.builder()
                        .status(HttpStatus.NOT_FOUND.name())
                        .message(ex.getMessage())
                        .details(ex.getMessage())
                        .build();
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
