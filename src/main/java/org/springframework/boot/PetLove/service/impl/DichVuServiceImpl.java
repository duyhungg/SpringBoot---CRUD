package org.springframework.boot.PetLove.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.PetLove.entities.DichVuEntity;
import org.springframework.boot.PetLove.exception.CollectionException;
import org.springframework.boot.PetLove.repository.DichVuRepsitory;
import org.springframework.boot.PetLove.service.DichVuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class DichVuServiceImpl implements DichVuService {
    @Autowired
    private DichVuRepsitory DichVuRepo;

    @Override
    public List<DichVuEntity> getAllDichVu() {
        List<DichVuEntity> DichVu = DichVuRepo.findAll();
        if (DichVu.size() > 0) {
            return DichVu;
        }else {
            return new ArrayList<DichVuEntity>();
        }
    }

    @Override
    public DichVuEntity getSingleDichVu(String id) throws CollectionException {
        Optional<DichVuEntity> todoOptional = DichVuRepo.findById(id);
        if (!todoOptional.isPresent()) {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }else {
            return todoOptional.get();
        }
    }

    @Override
    public void createDichVu(DichVuEntity DichVu) throws CollectionException {
        Optional<DichVuEntity> DichVuOptional= DichVuRepo.findByDichVu(DichVu.getMaDichVu());
        if(DichVuOptional.isPresent())
        {
            throw new CollectionException(CollectionException.AlreadyExists());
        }
        else
        {
            DichVuRepo.save(DichVu);
        }

    }

    @Override
    public void updateDichVu(String id, DichVuEntity DichVu) throws CollectionException {
        Optional<DichVuEntity> DichVuWithId = DichVuRepo.findById(id);
        Optional<DichVuEntity> DichVuWithSameName = DichVuRepo.findByDichVu(DichVu.getMaDichVu());
        if(DichVuWithId.isPresent())
        {
            if(DichVuWithSameName.isPresent() && !DichVuWithSameName.get().getId().equals(id))
            {

                throw new CollectionException(CollectionException.AlreadyExists());
            }
            DichVuEntity DichVuToUpdate=DichVuWithId.get();

            DichVuToUpdate.setMaDichVu(DichVu.getMaDichVu());
            DichVuToUpdate.setTenDichVu(DichVu.getTenDichVu());
            DichVuToUpdate.setNoiDung(DichVu.getNoiDung());
            DichVuToUpdate.setGiaDichVus(DichVu.getGiaDichVus());
            DichVuRepo.save(DichVuToUpdate);
        }
        else
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteDichVuById(String id) throws CollectionException {
        Optional<DichVuEntity> DichVuOptional = DichVuRepo.findById(id);
        if(!DichVuOptional.isPresent())
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
        else
        {
            DichVuRepo.deleteById(id);
        }

    }
}
