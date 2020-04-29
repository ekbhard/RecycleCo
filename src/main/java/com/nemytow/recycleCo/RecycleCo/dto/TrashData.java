package com.nemytow.recycleCo.RecycleCo.dto;

import com.nemytow.recycleCo.RecycleCo.domain.Trash;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class TrashData {

    @NonNull
    String type;

    @NonNull
    Long binId;

    @NonNull
    boolean resolved;

    String uri;

    public static TrashData of(Trash trash) {
        return TrashData.builder()
                .uri(trash.getUri())
                .type(trash.getType().getType())
                .binId(trash.getBinId())
                .resolved(trash.isResolved())
                .build();
    }

}
