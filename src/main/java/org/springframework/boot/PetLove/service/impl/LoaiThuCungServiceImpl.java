package org.springframework.boot.PetLove.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.PetLove.entities.LoaiThuCungEntity;
import org.springframework.boot.PetLove.exception.CollectionException;
import org.springframework.boot.PetLove.repository.LoaiThuCungRepository;
import org.springframework.boot.PetLove.service.LoaiThuCungService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LoaiThuCungServiceImpl implements LoaiThuCungService {
    @Autowired
    private LoaiThuCungRepository LoaiThuCungRepo;

    @Override
    public List<LoaiThuCungEntity> getAllLoaiThuCung() {
        List<LoaiThuCungEntity> LoaiThuCung = LoaiThuCungRepo.findAll();
        if (LoaiThuCung.size() > 0) {
            return LoaiThuCung;
        }else {
            return new ArrayList<LoaiThuCungEntity>();
        }
    }

    @Override
    public LoaiThuCungEntity getSingleLoaiThuCung(String id) throws CollectionException {
        Optional<LoaiThuCungEntity> todoOptional = LoaiThuCungRepo.findById(id);
        if (!todoOptional.isPresent()) {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }else {
            return todoOptional.get();
        }
    }

    @Override
    public void createLoaiThuCung(LoaiThuCungEntity LoaiThuCung) throws CollectionException {
        Optional<LoaiThuCungEntity> LoaiThuCungOptional= LoaiThuCungRepo.findByLoaiThuCung(LoaiThuCung.getMaLoaiThuCung());
        if(LoaiThuCungOptional.isPresent())
        {
            throw new CollectionException(CollectionException.AlreadyExists());
        }
        else
        {
            LoaiThuCungRepo.save(LoaiThuCung);
        }

    }

    @Override
    public void updateLoaiThuCung(String id, LoaiThuCungEntity LoaiThuCung) throws CollectionException {
        Optional<LoaiThuCungEntity> LoaiThuCungWithId = LoaiThuCungRepo.findById(id);
        Optional<LoaiThuCungEntity> LoaiThuCungWithSameName = LoaiThuCungRepo.findByLoaiThuCung(LoaiThuCung.getMaLoaiThuCung());
        if(LoaiThuCungWithId.isPresent())
        {
            if(LoaiThuCungWithSameName.isPresent() && !LoaiThuCungWithSameName.get().getId().equals(id))
            {

                throw new CollectionException(CollectionException.AlreadyExists());
            }
            LoaiThuCungEntity LoaiThuCungToUpdate=LoaiThuCungWithId.get();

            LoaiThuCungToUpdate.setMaLoaiThuCung(LoaiThuCung.getMaLoaiThuCung());
            LoaiThuCungToUpdate.setTenLoaiThuCung(LoaiThuCung.getTenLoaiThuCung());
            LoaiThuCungRepo.save(LoaiThuCungToUpdate);
        }
        else
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteLoaiThuCungById(String id) throws CollectionException {
        Optional<LoaiThuCungEntity> LoaiThuCungOptional = LoaiThuCungRepo.findById(id);
        if(!LoaiThuCungOptional.isPresent())
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
        else
        {
            LoaiThuCungRepo.deleteById(id);
        }

    }
}
