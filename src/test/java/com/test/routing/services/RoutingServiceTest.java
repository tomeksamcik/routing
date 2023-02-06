package com.test.routing.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest
@ExtendWith( SpringExtension.class )
public class RoutingServiceTest
{
    @Autowired
    private RoutingService routingService;

    @Test
    public void shouldFindOneOfThePossibleRoutes()
    {
        final var countries = routingService.search( "POL", "ITA" );

        assertThat( countries.isPresent(), equalTo( true ) );
        assertThat( countries.get().size(), equalTo( 4 ) );
        assertThat( countries.get(), hasItems( "POL", "ITA" ) );
    }

    @Test
    public void shouldNotFindRoute()
    {
        final var countries = routingService.search( "POL", "IRL" );

        assertThat( countries.isPresent(), equalTo( false ) );
    }
}
