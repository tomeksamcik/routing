package com.test.routing.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.routing.domain.Country;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CountryService
{
    private List<Country> countries = List.of();

    @Autowired
    private ObjectMapper objectMapper;

    @Value( "classpath:countries.json" )
    private Resource countriesResource;

    @PostConstruct
    public void init() throws IOException
    {
        countries = objectMapper
            .readValue( countriesResource.getInputStream(),
                new TypeReference<>()
                {
                } );
    }

    public Map<String, Country> getCountriesMap()
    {
        return countries.stream()
            .collect( Collectors.toMap( Country::getName, Function.identity() ) );
    }
}
