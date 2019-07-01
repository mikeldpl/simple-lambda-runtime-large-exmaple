package com.github.mikeldpl.lambdanative;

import javax.inject.Singleton;

import com.github.mikeldpl.lambdanative.core.TransportService;
import com.github.mikeldpl.lambdanative.unit.LambdaMonitoringService;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    LambdaMonitoringService getLambdaMonitoringService();

    TransportService getTransportService();
}
