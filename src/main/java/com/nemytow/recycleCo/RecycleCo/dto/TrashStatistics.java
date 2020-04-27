package com.nemytow.recycleCo.RecycleCo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TrashStatistics {

    long bigBatteries;

    long circleBatteries;

    long other;

    long phoneBattery;

    long resolved;
}
