package org.springframework.boot.PetLove.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.PetLove.entities.LoaiThuCungEntity;
import org.springframework.boot.PetLove.exception.CollectionException;
import org.springframework.boot.PetLove.service.LoaiThuCungService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class LoaiThuCungController {
    @Autowired
    private LoaiThuCungService LoaiThuCungService;

    @PostMapping("/LoaiThuCung")
    public ResponseEntity<?> createLoaiThuCung(@RequestBody LoaiThuCungEntity LoaiThuCung) {
        try {
            LoaiThuCungService.createLoaiThuCung(LoaiThuCung);
            return new ResponseEntity<LoaiThuCungEntity>(LoaiThuCung, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/LoaiThuCung")
    public ResponseEntity<?> getAllLoaiThuCung() {
        List<LoaiThuCungEntity> LoaiThuCung = LoaiThuCungService.getAllLoaiThuCung();
        return new ResponseEntity<>(LoaiThuCung, LoaiThuCung.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/LoaiThuCung/{id}")
    public ResponseEntity<?> getSingleLoaiThuCung(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(LoaiThuCungService.getSingleLoaiThuCung(id), HttpStatus.OK);
        } catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/LoaiThuCung/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws CollectionException {
        try{
            LoaiThuCungService.deleteLoaiThuCungById(id);
            return new ResponseEntity<>("Successfully deleted LoaiThuCung with id "+id, HttpStatus.OK);
        }
        catch (CollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/LoaiThuCung/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody LoaiThuCungEntity LoaiThuCung)
    {
        try {
            LoaiThuCungService.updateLoaiThuCung(id,LoaiThuCung);
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
