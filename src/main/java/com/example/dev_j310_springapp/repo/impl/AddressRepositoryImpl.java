package com.example.dev_j310_springapp.repo.impl;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.dto.ClientType;
import com.example.dev_j310_springapp.common.entity.AddressEntity;
import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;
import com.example.dev_j310_springapp.repo.AddressRepository;
import com.example.dev_j310_springapp.repo.CustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    @PersistenceContext
    private EntityManager em;

//    @Override
//    public Stream<AddressEntity> findAll() {
//        return em.createNativeQuery("select * from Address", AddressEntity.class).getResultList().stream();
//    }

    @Override
    public Optional<AddressEntity> findAddressById(Integer id) {
        return Optional.of(id).map(obj -> em.find(AddressEntity.class, obj));
    }



    //    @Override
//    public void removeByClientId(Integer id) throws EAppException {
//        findAddressByClientId(id).forEach(addr -> remove(addr.getId()));
//
//    }
    @Override
    public Stream<AddressEntity> findAddressByClientId(Integer id) {
        String query = "select * from Address where clientid=" + id;
        return em.createNativeQuery(query,
                AddressEntity.class).getResultList().stream();
    }

    @Override
    public Stream<AddressEntity> findByAddress(String address, String type){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AddressEntity> cq = cb.createQuery(AddressEntity.class);
        Root<AddressEntity> root = cq.from(AddressEntity.class);
        Join<AddressEntity, ClientEntity> clientJoin = root.join("client");
        if (type != null && !type.isEmpty()) {
            cq.select(root).where(
                    cb.and(
                            cb.like(root.get("address"), "%" + address+"%"),
                            cb.equal(clientJoin.get("type"), type)
                    ));
        } else

    {
        cq.select(root).where(
                cb.like(root.get("address"), "%" + address + "%"));
    }
            return em.createQuery(cq).getResultList().stream();
    }


    @Override
    public EntityManager getEm() {
        return em;
    }
}
