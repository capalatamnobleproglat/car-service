package com.att.carservice.service;

import com.att.carservice.dto.CarDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import java.util.List;

public interface CarService {

    List<CarDto> getAllCars();

    CarDto getCarById(Long id) throws ChangeSetPersister.NotFoundException;

    CarDto createCar(CarDto carDto);

    CarDto updateCar(Long id, CarDto carDto) throws ChangeSetPersister.NotFoundException;

    void deleteCar(Long id) throws ChangeSetPersister.NotFoundException;
}
