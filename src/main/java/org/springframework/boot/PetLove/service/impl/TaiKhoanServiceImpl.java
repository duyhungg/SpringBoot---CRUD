package org.springframework.boot.PetLove.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.PetLove.entities.TaiKhoanEntity;
import org.springframework.boot.PetLove.exception.CollectionException;
import org.springframework.boot.PetLove.repository.TaiKhoanRepository;
import org.springframework.boot.PetLove.service.TaiKhoanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private TaiKhoanRepository TaiKhoanRepo;

    @Override
    public List<TaiKhoanEntity> getAllTaiKhoan() {
        List<TaiKhoanEntity> TaiKhoan = TaiKhoanRepo.findAll();
        if (TaiKhoan.size() > 0) {
            return TaiKhoan;
        }else {
            return new ArrayList<TaiKhoanEntity>();
        }
    }

    @Override
    public TaiKhoanEntity getSingleTaiKhoan(String id) throws CollectionException {
        Optional<TaiKhoanEntity> todoOptional = TaiKhoanRepo.findById(id);
        if (!todoOptional.isPresent()) {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }else {
            return todoOptional.get();
        }
    }

    @Override
    public void createTaiKhoan(TaiKhoanEntity TaiKhoan) throws CollectionException {
        Optional<TaiKhoanEntity> TaiKhoanOptional= TaiKhoanRepo.findByTaiKhoan(TaiKhoan.getEmail());
        if(TaiKhoanOptional.isPresent())
        {
            throw new CollectionException(CollectionException.AlreadyExists());
        }
        else
        {
            TaiKhoanRepo.save(TaiKhoan);
        }

    }

    @Override
    public void updateTaiKhoan(String id, TaiKhoanEntity TaiKhoan) throws CollectionException {
        Optional<TaiKhoanEntity> TaiKhoanWithId = TaiKhoanRepo.findById(id);
        Optional<TaiKhoanEntity> TaiKhoanWithSameName = TaiKhoanRepo.findByTaiKhoan(TaiKhoan.getEmail());
        if(TaiKhoanWithId.isPresent())
        {
            if(TaiKhoanWithSameName.isPresent() && !TaiKhoanWithSameName.get().getId().equals(id))
            {

                throw new CollectionException(CollectionException.AlreadyExists());
            }
            TaiKhoanEntity TaiKhoanToUpdate=TaiKhoanWithId.get();

            TaiKhoanToUpdate.setName(TaiKhoan.getName());
            TaiKhoanToUpdate.setEmail(TaiKhoan.getEmail());
            TaiKhoanToUpdate.setPassword(TaiKhoan.getPassword());
            TaiKhoanToUpdate.setDienThoai(TaiKhoan.getDienThoai());
            TaiKhoanToUpdate.setRoles(TaiKhoan.getRoles());
            TaiKhoanRepo.save(TaiKhoanToUpdate);
        }
        else
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteTaiKhoanById(String id) throws CollectionException {
        Optional<TaiKhoanEntity> TaiKhoanOptional = TaiKhoanRepo.findById(id);
        if(!TaiKhoanOptional.isPresent())
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
        else
        {
            TaiKhoanRepo.deleteById(id);
        }

    }
}
