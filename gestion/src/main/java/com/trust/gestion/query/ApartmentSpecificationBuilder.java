package com.trust.gestion.query;

import java.util.ArrayList;
import java.util.List;

public class ApartmentSpecificationBuilder {
    private final List<QueryCriteria> params;

    public ApartmentSpecificationBuilder() {
        params = new ArrayList<>();
    }
    public final ApartmentSpecificationBuilder with(String key, String operation, Object value, String dataOption) {
        params.add(QueryCriteria.builder()
                .key(key)
                .operation(operation)
                .value(value)
                .dataOption(dataOption)
                .build());
        return this;
    }
}
