package com.example.dev_j310_springapp.repo.impl;

import com.example.dev_j310_springapp.common.dto.AddressDto;
import com.example.dev_j310_springapp.common.entity.AddressEntity;
import com.example.dev_j310_springapp.common.entity.ClientEntity;
import com.example.dev_j310_springapp.exception.EAppException;
import com.example.dev_j310_springapp.repo.AddressRepository;
import com.example.dev_j310_springapp.repo.CustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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



//    @Override
//    public void update(AddressEntity addressEntity) throws EAppException {
//       findAddressById(addressEntity.getId()).ifPresentOrElse(
//               entity -> {
//                    entity.setIp(addressEntity.getIp());
//                    entity.setMac(addressEntity.getMac());
//                    entity.setModel(addressEntity.getModel());
//                    entity.setAddress(addressEntity.getAddress());
//                    em.merge(entity);
//                    em.flush();
//               },
//               () -> {
//                   throw new EAppException(String.format("Объект с идентификатором '%d' не найден", addressEntity.getId()));
//               }
//
//       );
//    }
//
//    @Override
//    public Optional<AddressEntity> create(AddressEntity addressEntity) {
//        return Optional.empty();
//    }

    @Override
    public EntityManager getEm() {
        return em;
    }
}
