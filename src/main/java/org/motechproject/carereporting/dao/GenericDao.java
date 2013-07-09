package org.motechproject.carereporting.dao;

import org.motechproject.carereporting.domain.AbstractEntity;

import java.util.Map;
import java.util.Set;

public interface GenericDao<T extends AbstractEntity> {

    Set<T> findAll();

    T findById(Integer id);

    void save(T entity);

    void update(T entity);

    void remove(T entity);

    void removeAll();

    Object executeNamedQuery(final String queryName, final Map<String, ?> queryParams);

    Object executeNamedQueryWithUniqueResult(final String queryName, final Map<String, ?> queryParams);
}
