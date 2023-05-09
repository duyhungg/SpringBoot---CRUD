package org.springframework.boot.PetLove.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.PetLove.entities.TaiKhoanEntity;
import org.springframework.boot.PetLove.exception.CollectionException;
import org.springframework.boot.PetLove.service.TaiKhoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService TaiKhoanService;

    @PostMapping("/user")
    public ResponseEntity<?> createTaiKhoan(@RequestBody TaiKhoanEntity TaiKhoan) {
        try {
            TaiKhoanService.createTaiKhoan(TaiKhoan);
            return new ResponseEntity<TaiKhoanEntity>(TaiKhoan, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllTaiKhoan() {
        List<TaiKhoanEntity> TaiKhoan = TaiKhoanService.getAllTaiKhoan();
        return new ResponseEntity<>(TaiKhoan, TaiKhoan.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getSingleTaiKhoan(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(TaiKhoanService.getSingleTaiKhoan(id), HttpStatus.OK);
        } catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws CollectionException {
        try{
            TaiKhoanService.deleteTaiKhoanById(id);
            return new ResponseEntity<>("Successfully deleted TaiKhoan with id "+id, HttpStatus.OK);
        }
        catch (CollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TaiKhoanEntity TaiKhoan)
    {
        try {
            TaiKhoanService.updateTaiKhoan(id,TaiKhoan);
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
