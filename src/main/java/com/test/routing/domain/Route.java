package com.test.routing.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Route
{
    @Builder.Default
    private List<String> route = List.of();
}
