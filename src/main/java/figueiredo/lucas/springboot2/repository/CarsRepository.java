package figueiredo.lucas.springboot2.repository;

import figueiredo.lucas.springboot2.domain.Car;

import java.util.List;

public interface CarsRepository {
    List<Car> listAll();
}
