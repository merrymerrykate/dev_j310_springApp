package com.example.dev_j310_springapp.repo;

import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

public interface CustomRepository {

    default  <T>Optional<T> findById(Class<T> clazz, Integer id){
        return Optional.of(getEm().find(clazz, id));
    }

    default<T> Stream<T> findAll(Class<T> clazz){
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        cq.select(cq.from(clazz));
        return getEm().createQuery(cq).getResultList().stream();
    }

    @Transactional
    default <T> void remove(T entity){
        entity = getEm().merge(entity);
        getEm().remove(entity);
    }

    @Transactional
    default <T>Optional <T> create(T clientEntity){
        getEm().persist(clientEntity);
        getEm().flush();
        return Optional.of(clientEntity);
    }

    @Transactional
    default <T>Optional <T> update(T clientEntity){
        getEm().merge(clientEntity);
        getEm().flush();
        return Optional.of(clientEntity);
    }



    EntityManager getEm();
}
