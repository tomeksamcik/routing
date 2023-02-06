package com.test.routing.services;

import com.test.routing.domain.Country;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.util.TriConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutingService
{
    @Autowired
    private CountryService countryService;

    private static final BiFunction<List<String>, String, List<String>> addAndReturnCopy = ( path, country ) ->
        Stream.concat( path.stream(), Stream.of( country ) ).collect( Collectors.toList() );

    private static final Function<List<String>, String> getLastAdded =
        path -> path.get( path.size() - 1 );

    private static final TriConsumer<Queue<List<String>>, Set<String>, List<String>> addRouteAndMarkVisited =
        ( routes, visited, path ) ->
        {
            routes.add( path );
            visited.add( getLastAdded.apply( path ) );
        };

    public Optional<List<String>> search( String start, String end )
    {
        final var routes = new LinkedList<List<String>>();
        final var visited = new HashSet<String>();

        addRouteAndMarkVisited.accept( routes, visited, List.of( start ) );

        while ( !routes.isEmpty() )
        {
            final var path = routes.poll();
            final var current = getLastAdded.apply( path );

            if ( current.equals( end ) )
            {
                return Optional.of( path );
            }

            countryService.getCountriesMap().getOrDefault( current, Country.builder().build() ).getBorders().stream()
                .filter( neighbour -> !visited.contains( neighbour ) )
                .forEach( neighbour ->
                    addRouteAndMarkVisited.accept( routes, visited,
                        addAndReturnCopy.apply( path, neighbour ) ) );
        }

        return Optional.empty();
    }
}
