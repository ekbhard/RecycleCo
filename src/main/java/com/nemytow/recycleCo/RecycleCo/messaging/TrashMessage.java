package com.nemytow.recycleCo.RecycleCo.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
public class TrashMessage implements Serializable {

    @NonNull
    Long typeId;

    @NonNull
    Long userId;

    @NonNull
    Long beanId;

    String uri;

    Boolean resolved;

    public TrashMessage(@JsonProperty("typeId") Long typeId,
                        @JsonProperty("userId") Long userId,
                        @JsonProperty("beanId") Long beanId,
                        @JsonProperty("uri") String uri,
                        @JsonProperty("resolved") Boolean resolved) {
        this.uri = uri;
        this.typeId = typeId;
        this.userId = userId;
        this.beanId = beanId;
        this.resolved = resolved;
    }

    public String toString() {
        return "{\"typeId\":" + typeId +
                ", \"resolved\": " + resolved +
                ", \"beanId\": " + beanId +
                ", \"userId\": " + userId +
                ", \"uri\":\"" + uri + "\"}";
    }
}


