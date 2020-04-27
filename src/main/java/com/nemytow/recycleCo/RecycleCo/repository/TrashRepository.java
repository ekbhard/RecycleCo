package com.nemytow.recycleCo.RecycleCo.repository;

import com.nemytow.recycleCo.RecycleCo.domain.Trash;
import com.nemytow.recycleCo.RecycleCo.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.Predicate;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

public interface TrashRepository extends CrudRepository<Trash, Long> {
    List<Trash> findTrashByUser(User user);

    Trash findTrashById(Long id);

}
