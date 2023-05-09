package org.springframework.boot.PetLove.service;

import org.springframework.boot.PetLove.entities.DichVuEntity;
import org.springframework.boot.PetLove.entities.DichVuEntity;
import org.springframework.boot.PetLove.exception.CollectionException;

import java.util.List;

public interface DichVuService {
    public List<DichVuEntity> getAllDichVu();

    public DichVuEntity getSingleDichVu(String id) throws CollectionException;

    public void createDichVu(DichVuEntity DichVu) throws CollectionException;

    public void updateDichVu(String id, DichVuEntity DichVu) throws CollectionException;

    public void deleteDichVuById(String id) throws CollectionException;
}
