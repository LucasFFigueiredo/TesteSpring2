package figueiredo.lucas.springboot2.service;

import figueiredo.lucas.springboot2.domain.Car;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CarsServices {
    private static List<Car> cars;

    static {
        cars = new ArrayList<>(List.of(new Car(1L,"Fit"), new Car(2L,"Duster"), new Car(3L,"Eco Sport")));
    }

    public List<Car> listAll(){
        return cars;
    }
    
    public Car findById(long id) {
        return cars.stream()
                .filter(car -> Objects.equals(car.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not Found"));
    }

    public Car save(Car car){
        car.setId(ThreadLocalRandom.current().nextLong(4,1000));
        cars.add(car);
        return car;
    }

    public void delete(long id){
        cars.remove(findById(id));
    }

    public void replace(Car car){
        delete(car.getId());
        cars.add(car);
    }

}
