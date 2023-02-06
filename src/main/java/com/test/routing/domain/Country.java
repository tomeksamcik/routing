package com.test.routing.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize( using = CountryDeserializer.class )
public class Country
{
    private String name;

    @Builder.Default
    private List<String> borders = List.of();
}
