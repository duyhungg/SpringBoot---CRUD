package org.springframework.boot.PetLove.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.PetLove.entities.DatChoEntity;
import org.springframework.boot.PetLove.exception.CollectionException;
import org.springframework.boot.PetLove.service.DatChoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class DatChoController {
    @Autowired
    private DatChoService datChoService;

    @PostMapping("/datcho")
    public ResponseEntity<?> createDatCho(@RequestBody DatChoEntity datCho) {
        try {
            datChoService.createDatCho(datCho);
            return new ResponseEntity<DatChoEntity>(datCho, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/datcho")
    public ResponseEntity<?> getAllDatCho() {
        List<DatChoEntity> DatCho = datChoService.getAllDatCho();
        return new ResponseEntity<>(DatCho, DatCho.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/datcho/{id}")
    public ResponseEntity<?> getSingleDatCho(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(datChoService.getSingleDatCho(id), HttpStatus.OK);
        } catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/datcho/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws CollectionException {
        try{
            datChoService.deleteDatChoById(id);
            return new ResponseEntity<>("Successfully deleted DatCho with id "+id, HttpStatus.OK);
        }
        catch (CollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/datcho/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody DatChoEntity datCho)
    {
        try {
            datChoService.updateDatCho(id,datCho);
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