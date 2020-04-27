package com.nemytow.recycleCo.RecycleCo.api.trash;

import com.nemytow.recycleCo.RecycleCo.api.account.AccountApi;
import com.nemytow.recycleCo.RecycleCo.domain.Trash;
import com.nemytow.recycleCo.RecycleCo.dto.TrashData;
import com.nemytow.recycleCo.RecycleCo.dto.TrashStatistics;
import com.nemytow.recycleCo.RecycleCo.dto.TrashEnum;
import com.nemytow.recycleCo.RecycleCo.repository.TrashRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class TrashApiImpl implements UserTrashApi{
    @Autowired
    AccountApi accountApi;

    @Autowired
    TrashRepository repository;

    @Override
    public List<TrashData> getTrashByUser(){
        List<Trash> trashList = repository.findTrashByUser(accountApi.getCurrentUser());
        return trashList.stream().map(TrashData::of).collect(Collectors.toList());
    }

    @Override
    public TrashStatistics getTrashStatistic(){
        List<Trash> trashList = repository.findTrashByUser(accountApi.getCurrentUser());

        long bigBatteries = countTrash(TrashEnum.BIG_BATTERIES,trashList);

        long circleBatteries = countTrash(TrashEnum.CIRCLE_BATTERIES,trashList);

        long other = countTrash(TrashEnum.OTHER,trashList);

        long phoneBattery = countTrash(TrashEnum.PHONE_BATTERY,trashList);

        long resolved = countTrash(TrashEnum.RESOLVED,trashList);

        return TrashStatistics.builder()
                .bigBatteries(bigBatteries)
                .circleBatteries(circleBatteries)
                .other(other)
                .resolved(resolved)
                .phoneBattery(phoneBattery)
                .build();
    }
    private long countTrash(TrashEnum trash, List<Trash> trashList ){
        return trashList.stream()
                .filter(a->a.getType().getType().equals(trash.getName()))
                .count();
    }

}
