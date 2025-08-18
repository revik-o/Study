package com.data.ox.domain.logic.dao;

import com.data.ox.core.common.Page;
import lombok.NonNull;

import java.io.Serializable;

public interface DaoI<T extends Serializable> {

    Page<T> findAll(int pageNumber, int limit);

    T findById(long id);

    T save(@NonNull T entity);
}
