package com.nemytow.recycleCo.RecycleCo.repository;

import com.nemytow.recycleCo.RecycleCo.domain.Profile;
import com.nemytow.recycleCo.RecycleCo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile,Long> {
    Profile findByUser(String username);

}
