package com.github.mikeldpl.lambdanative;


import javax.inject.Singleton;

import com.github.mikeldpl.lambdanative.core.HttpTransportService;
import com.github.mikeldpl.lambdanative.core.TransportService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class AppModule {

    @Provides
    @Singleton
    static Gson gson() {
        return new GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Binds
    abstract TransportService transportService(HttpTransportService httpTransportService);
}
