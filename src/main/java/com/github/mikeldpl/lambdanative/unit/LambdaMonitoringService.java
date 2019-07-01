package com.github.mikeldpl.lambdanative.unit;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;

@Singleton
public class LambdaMonitoringService {

    private static final File TEMP_FILE = new File("/tmp/statistic_unit.json");
    private static final UptimeStatisticUnit STATIC_STATISTIC_UNIT = new UptimeStatisticUnit();
    private final Gson gson;

    @Inject
    public LambdaMonitoringService(Gson gson) {
        this.gson = gson;
    }


    public UptimeStatistics getUptimeStatistics(UptimeStatisticUnit handlerUptimeStatisticUnit) {
        UptimeStatisticUnit tempFolderContextUnit = getUnitFromFile();
        tempFolderContextUnit.increment();
        writeUnitToFile(tempFolderContextUnit);


        STATIC_STATISTIC_UNIT.increment();
        handlerUptimeStatisticUnit.increment();
        return new UptimeStatistics(handlerUptimeStatisticUnit, STATIC_STATISTIC_UNIT, tempFolderContextUnit);
    }

    private void writeUnitToFile(UptimeStatisticUnit tempFolderContextUnit) {
        final String json = gson.toJson(tempFolderContextUnit);
        try {
            Files.write(TEMP_FILE.toPath(), json.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private UptimeStatisticUnit getUnitFromFile() {
        final FileReader json;
        try {
            json = new FileReader(TEMP_FILE);
        } catch (FileNotFoundException e) {
            return new UptimeStatisticUnit();
        }
        return gson.fromJson(json, UptimeStatisticUnit.class);
    }

}
