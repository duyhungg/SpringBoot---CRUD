package org.springframework.boot.PetLove.repository;

import org.springframework.boot.PetLove.entities.DichVuEntity;
import org.springframework.boot.PetLove.entities.LoaiThuCungEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoaiThuCungRepository extends MongoRepository<LoaiThuCungEntity, String> {
    @Query("{'loaithucung': ?0}")
    Optional<LoaiThuCungEntity> findByLoaiThuCung(String DichVu);
}
