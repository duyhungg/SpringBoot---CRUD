package org.springframework.boot.PetLove.repository;


import org.springframework.boot.PetLove.entities.TaiKhoanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends MongoRepository<TaiKhoanEntity, String> {
    @Query("{'user': ?0}")
    Optional<TaiKhoanEntity> findByTaiKhoan(String TaiKhoan);
}
