package com.test.routing.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@ExtendWith( SpringExtension.class )
public class CountryServiceTest
{
    @Autowired
    private CountryService countryService;

    @Test
    public void shouldReturnMapOfCountries()
    {
        final var countries = countryService.getCountriesMap();

        assertThat( countries.size(), equalTo( 250 ) );
        assertThat( countries.keySet(), hasItem( "POL" ) );
        assertThat( countries.get( "POL" ).getBorders(),
            contains( "BLR", "CZE", "DEU", "LTU", "RUS", "SVK", "UKR" ) );
    }
}
