package com.github.mikeldpl.lambdanative.core;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

@Singleton
public class HttpTransportService implements TransportService {

    private final Gson gson;

    @Inject
    public HttpTransportService(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String createSuccessResponse(Object body) {
        return gson.toJson(createResponse(body, 200));
    }

    @Override
    public String createErrorResponse(Object body) {
        return gson.toJson(createResponse(body, 500));
    }


    private GatewayResponse createResponse(Object payload, int i) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        final String body = gson.toJson(payload);
        return new GatewayResponse(body, headers, i);
    }

}
