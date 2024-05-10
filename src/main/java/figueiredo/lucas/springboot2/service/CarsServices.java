package figueiredo.lucas.springboot2.service;

import figueiredo.lucas.springboot2.domain.Car;
import figueiredo.lucas.springboot2.mapper.CarMapper;
import figueiredo.lucas.springboot2.repository.CarsRepository;
import figueiredo.lucas.springboot2.requests.CarPostRequestBody;
import figueiredo.lucas.springboot2.requests.CarPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CarsServices {

    private final CarsRepository carsRepository;

    public List<Car> listAll(){
        return carsRepository.findAll();
    }
    
    public Car findByIdOrThrowBadRequestException(long id) {
        return carsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not Found"));
    }

    public Car save(CarPostRequestBody carPostRequestBody){
        return carsRepository.save(CarMapper.INSTANCE.toCar(carPostRequestBody));
    }

    public void delete(long id){
        carsRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(CarPutRequestBody carPutRequestBody){
        Car bdCar = findByIdOrThrowBadRequestException(carPutRequestBody.getId());
        Car car = CarMapper.INSTANCE.tocar(carPutRequestBody);
        car.setId(bdCar.getId());
        carsRepository.save(car);
    }

}
