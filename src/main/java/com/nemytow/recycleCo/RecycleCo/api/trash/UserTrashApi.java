package com.nemytow.recycleCo.RecycleCo.api.trash;

import com.nemytow.recycleCo.RecycleCo.dto.TrashData;
import com.nemytow.recycleCo.RecycleCo.dto.TrashStatistics;

import java.util.List;

public interface UserTrashApi {
    List<TrashData> getTrashByUser();

    TrashStatistics getTrashStatistic();
}
