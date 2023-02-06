package com.test.routing.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Route
{
    @Builder.Default
    private List<String> route = List.of();
}
