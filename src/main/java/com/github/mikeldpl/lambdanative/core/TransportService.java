package com.github.mikeldpl.lambdanative.core;

public interface TransportService {
    String createSuccessResponse(Object body);

    String createErrorResponse(Object body);
}
