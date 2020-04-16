package com.nemytow.recycleCo.RecycleCo.api.trash;

import com.nemytow.recycleCo.RecycleCo.dto.TrashData;

import java.util.List;

public interface TrashApi {

    List<TrashData> getTrashByUser();
}
