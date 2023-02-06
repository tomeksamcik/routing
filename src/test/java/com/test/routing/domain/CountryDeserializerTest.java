package com.test.routing.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@ExtendWith( SpringExtension.class )
public class CountryDeserializerTest
{
    private final ObjectMapper mapper = new ObjectMapper();

    @Value( "classpath:country.json" )
    Resource resource;

    @Test
    public void shouldDeserializeCountry() throws IOException
    {
        final var json = Files.readString( Path.of( resource.getURI() ) );
        final var country = mapper.readValue( json, Country.class );

        assertThat( country.getName(), equalTo( "ABW" ) );
        assertThat( country.getBorders(), contains( "IRN", "PAK", "TKM", "UZB", "TJK", "CHN" ) );
    }
}
