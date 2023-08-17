package com.att.carservice.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import com.att.carservice.model.Car;
import com.att.carservice.dto.CarDto;
import com.att.carservice.repository.CarRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDto createCar(CarDto carDto) {
        Car car = convertToEntity(carDto);
        car = carRepository.save(car);
        return convertToDto(car);
    }

    @Override
    public CarDto getCarById(Long id) throws ChangeSetPersister.NotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return convertToDto(car);
    }

    @Override
    public CarDto updateCar(Long id, CarDto carDto) throws ChangeSetPersister.NotFoundException {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Actualizar los campos de car existente con la información del DTO
        BeanUtils.copyProperties(carDto, existingCar, "id");

        existingCar = carRepository.save(existingCar);
        return convertToDto(existingCar);
    }

    @Override
    public void deleteCar(Long id) throws ChangeSetPersister.NotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        carRepository.delete(car);
    }

    private CarDto convertToDto(Car car) {
        CarDto carDto = new CarDto();
        BeanUtils.copyProperties(car, carDto);
        return carDto;
    }

    private Car convertToEntity(CarDto carDto) {
        Car car = new Car();
        BeanUtils.copyProperties(carDto, car);
        return car;
    }
}
