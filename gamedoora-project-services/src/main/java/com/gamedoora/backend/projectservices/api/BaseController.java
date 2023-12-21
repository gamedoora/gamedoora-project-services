package com.gamedoora.backend.projectservices.api;

@RequestMapping("api/v1")
@CrossOrigin("*")
public abstract class BaseController {
    public <T> ResponseEntity<T>  createResponse(T entity, HttpStatus httpStatus){
        if(null == entity){
            return new ResponseEntity<>(httpStatus);
        }
        return new ResponseEntity<>(entity, httpStatus);
    }
}
