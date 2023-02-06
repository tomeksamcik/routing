package com.test.routing.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CountryDeserializer extends JsonDeserializer<Country>
{
    @Override
    public Country deserialize( JsonParser jsonParser,
                                DeserializationContext deserializationContext )
        throws IOException
    {
        final JsonNode countryNode = jsonParser.getCodec().readTree( jsonParser );
        return Country.builder()
            .borders( StreamSupport
                .stream( countryNode.findValue( "borders" ).spliterator(), false )
                .map( JsonNode::textValue )
                .collect( Collectors.toList() ) )
            .name( countryNode.get( "cca3" ).textValue() )
            .build();
    }
}
