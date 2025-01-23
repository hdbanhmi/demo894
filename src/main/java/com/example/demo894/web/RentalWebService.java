
package com.example.demo894.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo894.data.Car;

@RestController
public class RentalWebService {

    Logger logger = LoggerFactory.getLogger(RentalWebService.class);
    List<Car> cars = new ArrayList<>();

    public RentalWebService(){
        cars.add(new Car("AA11BB", "Ferrari", 1000));
        cars.add(new Car("BB22CC", "Porsche", 2000));
    }

    @GetMapping("/bonjour")
    public String disBonjour() {
        return "Bonjour !";
    }

    @PutMapping(value = "/cars/{plateNumber}")
    public void rent(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value="rent", required = true)boolean rent,
            @RequestBody Dates dates){

        logger.info("PlateNumber:" + plateNumber);
        logger.info("Rent:" + rent);
        logger.info("Dates:" + dates);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return cars;
    }

    @GetMapping("/cars/{plateNumber}")
    public Car getACar(@PathVariable("plateNumber") String plateNumber) throws CarNotFoundException {
        return cars.stream().filter(car -> car.getPlateNumber().equals(plateNumber)).findFirst().orElseThrow(() -> new CarNotFoundException(plateNumber));
    }



}
