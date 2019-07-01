package com.github.mikeldpl.lambdanative.unit;

import java.util.Date;

import lombok.Data;

@Data
public class UptimeStatisticUnit {
    private final Date firstAccessTime = new Date();
    private int counter;

    void increment() {
        counter++;
    }
}
