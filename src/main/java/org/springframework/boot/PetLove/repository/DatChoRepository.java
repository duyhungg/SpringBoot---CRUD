package org.springframework.boot.PetLove.repository;

import org.springframework.boot.PetLove.entities.DatChoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface DatChoRepository extends MongoRepository<DatChoEntity, String> {
    @Query("{'datcho': ?0}")
    Optional<DatChoEntity> findByDatCho(String DatCho);
}