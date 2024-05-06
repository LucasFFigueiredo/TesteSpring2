package figueiredo.lucas.springboot2.controller;


import figueiredo.lucas.springboot2.domain.Car;
import figueiredo.lucas.springboot2.service.CarsServices;
import figueiredo.lucas.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//localhost:8080/Cars

@RestController
@RequestMapping("Cars")
@Log4j2
@RequiredArgsConstructor
public class CarsController {
    private final DateUtil dateUtil;
    private final CarsServices carsServices;

    @GetMapping
    public ResponseEntity<List<Car>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(carsServices.listAll());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Car> findById(@PathVariable long id){
        return ResponseEntity.ok(carsServices.findById(id));
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car){
        return new ResponseEntity<>(carsServices.save(car), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        carsServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Car car){
        carsServices.replace(car);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
