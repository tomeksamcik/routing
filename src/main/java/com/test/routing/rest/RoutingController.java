package com.test.routing.rest;

import com.test.routing.domain.Error;
import com.test.routing.domain.Route;
import com.test.routing.exceptions.RouteNotFoundException;
import com.test.routing.services.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutingController
{
    @Autowired
    private RoutingService routingService;

    @GetMapping( "/routing/{origin}/{destination}" )
    public Route getRoute( @PathVariable String origin, @PathVariable String destination )
    {
        return routingService.search( origin, destination )
            .map( c -> Route.builder().route( c ).build() ).orElseThrow(
                () -> new RouteNotFoundException(
                    String.format( "Route %s - %s has not been found", origin, destination ) ) );
    }

    @ExceptionHandler( RouteNotFoundException.class )
    public ResponseEntity<Error> handleRoutingNotFoundException( RouteNotFoundException ex )
    {
        return ResponseEntity.status( HttpStatus.BAD_REQUEST )
            .body( Error.builder().error( ex.getMessage() ).build() );
    }
}
