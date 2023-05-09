package org.springframework.boot.PetLove.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.PetLove.entities.DatChoEntity;
import org.springframework.boot.PetLove.entities.embedded.ThongTinDatCho;
import org.springframework.boot.PetLove.exception.CollectionException;
import org.springframework.boot.PetLove.repository.DatChoRepository;
import org.springframework.boot.PetLove.service.DatChoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DatChoServiceImpl implements DatChoService {
    @Autowired
    private DatChoRepository DatChoRepo;

    @Override
    public List<DatChoEntity> getAllDatCho() {
        List<DatChoEntity> DatCho = DatChoRepo.findAll();
        if (DatCho.size() > 0) {
            return DatCho;
        }else {
            return new ArrayList<DatChoEntity>();
        }
    }

    @Override
    public DatChoEntity getSingleDatCho(String id) throws CollectionException {
        Optional<DatChoEntity> DatChoOptional = DatChoRepo.findById(id);
        if (!DatChoOptional.isPresent()) {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }else {
            return DatChoOptional.get();
        }
    }

    @Override
    public void createDatCho(DatChoEntity DatCho) throws CollectionException {
        Optional<DatChoEntity> DatChoOptional= DatChoRepo.findByDatCho(DatCho.getEmail());
        if(DatChoOptional.isPresent())
        {
            throw new CollectionException(CollectionException.AlreadyExists());
        }
        else
        {

            DatCho.setThoiGian(new Date(System.currentTimeMillis()));
            DatChoRepo.save(DatCho);
        }

    }

    @Override
    public void updateDatCho(String id, DatChoEntity DatCho) throws CollectionException {
        Optional<DatChoEntity> DatChoWithId = DatChoRepo.findById(id);
        Optional<DatChoEntity> DatChoWithSameName = DatChoRepo.findByDatCho(DatCho.getEmail());
        if(DatChoWithId.isPresent())
        {
            if(DatChoWithSameName.isPresent() && !DatChoWithSameName.get().getId().equals(id))
            {

                throw new CollectionException(CollectionException.AlreadyExists());
            }
            DatChoEntity DatChoToUpdate=DatChoWithId.get();

            DatChoToUpdate.setEmail(DatCho.getEmail());
            DatChoToUpdate.setThongTinDatChos(DatCho.getThongTinDatChos());
            DatChoToUpdate.setCanDan(DatCho.getCanDan());
            DatChoToUpdate.setTrangThaiDatCho(DatCho.getTrangThaiDatCho());
            DatChoToUpdate.setThoiGian(new Date(System.currentTimeMillis()));
            DatChoRepo.save(DatChoToUpdate);
        }
        else
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteDatChoById(String id) throws CollectionException {
        Optional<DatChoEntity> DatChoOptional = DatChoRepo.findById(id);
        if(!DatChoOptional.isPresent())
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
        else
        {
            DatChoRepo.deleteById(id);
        }

    }
}
