package org.springframework.boot.PetLove.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.PetLove.entities.DichVuEntity;
import org.springframework.boot.PetLove.exception.CollectionException;
import org.springframework.boot.PetLove.service.DichVuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class DichVuController {
    @Autowired
    private DichVuService DichVuService;

    @PostMapping("/DichVu")
    public ResponseEntity<?> createDichVu(@RequestBody DichVuEntity DichVu) {
        try {
            DichVuService.createDichVu(DichVu);
            return new ResponseEntity<DichVuEntity>(DichVu, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/DichVu")
    public ResponseEntity<?> getAllDichVu() {
        List<DichVuEntity> DichVu = DichVuService.getAllDichVu();
        return new ResponseEntity<>(DichVu, DichVu.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/DichVu/{id}")
    public ResponseEntity<?> getSingleDichVu(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(DichVuService.getSingleDichVu(id), HttpStatus.OK);
        } catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/DichVu/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws CollectionException {
        try{
            DichVuService.deleteDichVuById(id);
            return new ResponseEntity<>("Successfully deleted DichVu with id "+id, HttpStatus.OK);
        }
        catch (CollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/DichVu/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody DichVuEntity DichVu)
    {
        try {
            DichVuService.updateDichVu(id,DichVu);
            return new ResponseEntity<>("Updated movie with id "+id+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
