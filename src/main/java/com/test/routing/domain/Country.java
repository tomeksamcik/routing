package com.test.routing.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize( using = CountryDeserializer.class )
public class Country
{
    private String name;

    @Builder.Default
    private List<String> borders = List.of();
}
