package com.nemytow.recycleCo.RecycleCo.repository;

import com.nemytow.recycleCo.RecycleCo.domain.Trash;
import com.nemytow.recycleCo.RecycleCo.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrashRepository extends CrudRepository<Trash,Long> {
    List<Trash> findTrashByUser(User user);
}
