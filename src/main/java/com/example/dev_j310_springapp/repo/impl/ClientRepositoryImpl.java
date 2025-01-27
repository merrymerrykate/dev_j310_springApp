package com.example.dev_j310_springapp.repo.impl;

import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;
import com.example.dev_j310_springapp.repo.ClientRepository;
import com.example.dev_j310_springapp.repo.CustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class ClientRepositoryImpl implements ClientRepository{


    @PersistenceContext
    private EntityManager em;

    public ClientEntity findClientByOpId(Integer id){
        return Optional.of(id).map(obj -> em.find(ClientEntity.class, obj))
                .orElseThrow(() -> new EAppException(String.format("Элементы по идентификатору 's' не найдены", id)));

    }

    public ClientEntity findClientByClientId(Integer id){
        if(id==null) return null;
        ClientEntity client= em.find(ClientEntity.class, id);
        if(client==null) throw new EAppException(String.format("Элементы по идентификатору '%d' не найдены", id));
        return client;
    }

    public Stream<ClientEntity> findByClientName(String clientname){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery <ClientEntity> cq = cb.createQuery(ClientEntity.class);
        Root<ClientEntity> root = cq.from(ClientEntity.class);
        cq.select(root).where(
                cb.like(root.get("clientName"), '%'+clientname+'%'));
        return em.createQuery(cq).getResultList().stream();
    }
//    @Override
//    public Stream<ClientEntity> findAll() {
//        return em.createNativeQuery("select * from Client", ClientEntity.class).getResultList().stream();
//    }



//    @Override
//    @Transactional
//    public void remove(Integer id) throws EAppException {
//        findById(ClientEntity.class, id).ifPresent(client -> em.remove(client));
//    }

//    @Override
//    @Transactional
//    public void update(ClientEntity clientEntity) throws EAppException {
//        em.merge(clientEntity);
//        em.flush();
//    }
//
//    @Override
//    @Transactional
//    public Optional<ClientEntity> create(ClientEntity clientEntity) {
//        em.persist(clientEntity);
//        return Optional.of(clientEntity);
//    }

    @Override
    public EntityManager getEm(){
        return em;
    }
}
