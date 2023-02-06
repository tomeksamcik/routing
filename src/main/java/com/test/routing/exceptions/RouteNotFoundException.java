package com.test.routing.exceptions;

public class RouteNotFoundException extends RuntimeException
{
    public RouteNotFoundException( String message )
    {
        super( message );
    }
}
