package com.att.carservice.controller;

import com.att.carservice.dto.CarDto;
import com.att.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/cars")
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carService.createCar(carDto);
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return carService.getCarById(id);
    }

    @PutMapping("/{id}")
    public CarDto updateCar(@PathVariable Long id, @RequestBody CarDto carDto) throws ChangeSetPersister.NotFoundException {
        return carService.updateCar(id, carDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        carService.deleteCar(id);
    }
}
