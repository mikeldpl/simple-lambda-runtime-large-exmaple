package com.github.mikeldpl.lambdanative;

import java.util.TimeZone;

import com.github.mikeldpl.lambda.runtime.LambdaRuntime;
import com.github.mikeldpl.lambda.runtime.LambdaRuntimeApi;
import com.github.mikeldpl.lambda.runtime.aws.AwsLambdaRuntimeApi;
import com.github.mikeldpl.lambdanative.core.ErrorMessage;
import com.github.mikeldpl.lambdanative.core.TransportService;
import com.github.mikeldpl.lambdanative.unit.LambdaMonitoringService;
import com.github.mikeldpl.lambdanative.unit.UptimeStatisticUnit;
import com.github.mikeldpl.lambdanative.unit.UptimeStatistics;


public class Main {
    private static final AppComponent APP_COMPONENT = DaggerAppComponent.create();

    public static void main(String[] args) {
        final LambdaRuntimeApi awsLambdaRuntimeApi = new AwsLambdaRuntimeApi();
        UptimeStatisticUnit uptimeStatisticUnit = new UptimeStatisticUnit();
        new LambdaRuntime(awsLambdaRuntimeApi,
                          //handler
                          invocation -> {

                              final LambdaMonitoringService lambdaMonitoringService = APP_COMPONENT.getLambdaMonitoringService();
                              final TransportService transportService = APP_COMPONENT.getTransportService();

                              System.err.println(invocation + "---------------");
                              System.err.println(TimeZone.getDefault() + "---------------");
                              System.err.println(Thread.currentThread().getName() + "---------------");

                              final UptimeStatistics uptimeStatistics = lambdaMonitoringService.getUptimeStatistics(uptimeStatisticUnit);

                              return transportService.createSuccessResponse(uptimeStatistics);
                          },
                          //error handler
                          (invocation, e) -> {
                              final TransportService transportService = APP_COMPONENT.getTransportService();

                              e.printStackTrace();
                              final ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
                              return transportService.createErrorResponse(errorMessage);
                          }).run();
    }
}
