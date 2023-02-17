package com.test.routing.services;

import com.test.routing.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RoutingService
{
    @Autowired
    private CountryService countryService;

    public Optional<List<String>> search( final String start, final String end )
    {
        final var routes = new LinkedList<List<String>>();
        final var visited = new HashSet<String>();

        addRouteAndMarkVisited( routes, visited, List.of( start ) );

        while ( !routes.isEmpty() )
        {
            final var path = routes.poll();
            final var current = getLastAdded( path );

            if ( current.equals( end ) )
            {
                return Optional.of( path );
            }

            countryService.getCountriesMap().getOrDefault( current, Country.builder().build() )
                .getBorders().stream()
                .filter( neighbour -> !visited.contains( neighbour ) )
                .forEach( neighbour ->
                    addRouteAndMarkVisited( routes, visited,
                        addAndReturnCopy( path, neighbour ) ) );
        }

        return Optional.empty();
    }

    private List<String> addAndReturnCopy( List<String> path, String country )
    {
        return Stream.concat( path.stream(), Stream.of( country ) ).collect( Collectors.toList() );
    }

    private String getLastAdded( List<String> path )
    {
        return path.get( path.size() - 1 );
    }

    private void addRouteAndMarkVisited(
        Queue<List<String>> routes, Set<String> visited, List<String> path )
    {
        routes.add( path );
        visited.add( getLastAdded( path ) );
    }
}
