package org.springframework.boot.PetLove.service;

import org.springframework.boot.PetLove.entities.TaiKhoanEntity;
import org.springframework.boot.PetLove.exception.CollectionException;

import java.util.List;

public interface TaiKhoanService {
    public List<TaiKhoanEntity> getAllTaiKhoan();

    public TaiKhoanEntity getSingleTaiKhoan(String id) throws CollectionException;

    public void createTaiKhoan(TaiKhoanEntity TaiKhoan) throws CollectionException;

    public void updateTaiKhoan(String id, TaiKhoanEntity TaiKhoan) throws CollectionException;

    public void deleteTaiKhoanById(String id) throws CollectionException;
}
