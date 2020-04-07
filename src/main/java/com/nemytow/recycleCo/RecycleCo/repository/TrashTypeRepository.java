package com.nemytow.recycleCo.RecycleCo.repository;

import com.nemytow.recycleCo.RecycleCo.domain.TrashType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TrashTypeRepository extends CrudRepository<TrashType,Long> {
    Optional<TrashType> findById (Long id);
}
