package com.nemytow.recycleCo.RecycleCo.api.trash;

import com.nemytow.recycleCo.RecycleCo.api.account.AccountApi;
import com.nemytow.recycleCo.RecycleCo.domain.Trash;
import com.nemytow.recycleCo.RecycleCo.dto.TrashData;
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
}
