package com.nemytow.recycleCo.RecycleCo.api.admin;

import com.nemytow.recycleCo.RecycleCo.domain.Trash;
import com.nemytow.recycleCo.RecycleCo.repository.TrashRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Log4j2
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class AdminApiImpl implements AdminApi {

    @Autowired
    TrashRepository repository;

    @Override
    public void resolveTrash(Long trashId){
        Trash trashForResolve = repository.findTrashById(trashId);
        trashForResolve.setResolved(true);
        repository.save(trashForResolve);
    }
}
