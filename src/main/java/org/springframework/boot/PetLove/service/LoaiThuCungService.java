package org.springframework.boot.PetLove.service;

import org.springframework.boot.PetLove.entities.LoaiThuCungEntity;
import org.springframework.boot.PetLove.exception.CollectionException;

import java.util.List;

public interface LoaiThuCungService {
    public List<LoaiThuCungEntity> getAllLoaiThuCung();

    public LoaiThuCungEntity getSingleLoaiThuCung(String id) throws CollectionException;

    public void createLoaiThuCung(LoaiThuCungEntity LoaiThuCung) throws CollectionException;

    public void updateLoaiThuCung(String id, LoaiThuCungEntity LoaiThuCung) throws CollectionException;

    public void deleteLoaiThuCungById(String id) throws CollectionException;
}
