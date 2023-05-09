package org.springframework.boot.PetLove.repository;

import org.springframework.boot.PetLove.entities.DatChoEntity;
import org.springframework.boot.PetLove.entities.DichVuEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DichVuRepsitory extends MongoRepository<DichVuEntity, String> {
    @Query("{'dichvu': ?0}")
    Optional<DichVuEntity> findByDichVu(String DichVu);
}
