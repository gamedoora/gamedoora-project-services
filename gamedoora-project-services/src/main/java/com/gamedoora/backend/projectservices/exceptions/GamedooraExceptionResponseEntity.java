package com.gamedoora.backend.projectservices.exceptions;

@Builder
public class GamedooraExceptionResponseEntity {
    private String status;
    private String message;
    private String details;
}
