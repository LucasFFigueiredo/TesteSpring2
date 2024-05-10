package figueiredo.lucas.springboot2.repository;

import figueiredo.lucas.springboot2.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car, Long> {

}
