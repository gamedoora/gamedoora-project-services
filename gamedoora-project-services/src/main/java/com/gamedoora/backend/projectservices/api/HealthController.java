package com.gamedoora.backend.projectservices.api;

@RestController
public class HealthController extends BaseController{

    @GetMapping(value = "/health",produces =  {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> provideHealthStatus() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
