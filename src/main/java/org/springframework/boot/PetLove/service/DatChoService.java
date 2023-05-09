package org.springframework.boot.PetLove.service;

import org.springframework.boot.PetLove.entities.DatChoEntity;
import org.springframework.boot.PetLove.exception.CollectionException;

import java.util.List;

public interface DatChoService {
    public List<DatChoEntity> getAllDatCho();

    public DatChoEntity getSingleDatCho(String id) throws CollectionException;

    public void createDatCho(DatChoEntity datCho) throws CollectionException;

    public void updateDatCho(String id, DatChoEntity datCho) throws CollectionException;

    public void deleteDatChoById(String id) throws CollectionException;
}
