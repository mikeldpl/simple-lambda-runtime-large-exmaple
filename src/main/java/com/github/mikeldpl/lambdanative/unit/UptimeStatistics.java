package com.github.mikeldpl.lambdanative.unit;

import lombok.Data;

@Data
public class UptimeStatistics {
    private final UptimeStatisticUnit instantContext;
    private final UptimeStatisticUnit staticContext;
    private final UptimeStatisticUnit tempFolderContext;
}

