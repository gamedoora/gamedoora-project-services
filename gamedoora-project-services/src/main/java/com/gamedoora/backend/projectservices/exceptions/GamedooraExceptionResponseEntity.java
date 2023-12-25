package com.gamedoora.backend.projectservices.exceptions;

import lombok.Builder;

@Builder
public class GamedooraExceptionResponseEntity {
    private String status;
    private String message;
    private String details;
}
