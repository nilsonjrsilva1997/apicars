package com.apicars.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apicars.models.Car;
import com.apicars.repositories.CarRepository;
import com.apicars.validators.CarValidator;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarResource {
    @Autowired
    private static CarRepository carRepository;

    @GetMapping("/veiculos")
    public List<Car> listCars() {
        return carRepository.findAll();
    }
    
    @GetMapping("/veiculos/{id}")
    public Car show(@PathVariable(value = "id") long id) {
        return carRepository.findById(id);
    }
    
    @GetMapping("/veiculos/nao-vendido")
    public int countNotSold() {
        return carRepository.countCarNotSold();
    }
    
    @PostMapping("/veiculos")
    public ResponseEntity<Object> create(@RequestBody Car car) {
    	
    	try {    	   
    		CarValidator validator = new CarValidator(car);
    		
    		List<String> errors = validator.validate();
    		
    		if(!errors.isEmpty()) {
    			ResponseEntity<Object> carResponseEntity = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    			return carResponseEntity;
    		} else {    		
    			car = carRepository.save(car);
    			ResponseEntity<Object> carResponseEntity = new ResponseEntity<>(car, HttpStatus.ACCEPTED);
    			return carResponseEntity;
    		}    	
    	} catch(Exception ex) {
    		List<String> errors = new ArrayList<String>();

    		errors.add("Erro ao salvar dados");
    		ResponseEntity<Object> carResponseEntity = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			return carResponseEntity;
    	}
    	
    }
    
    @PutMapping("/veiculos/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Car car) {
        try {
        	CarValidator validator = new CarValidator(car);
    		
    		List<String> errors = validator.validate();
    		
    		if(!errors.isEmpty()) {
    			ResponseEntity<Object> carResponseEntity = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    			return carResponseEntity;
    		} else {    		
    			 Car carRecord = carRepository.findById((long) id);
    	         carRecord.setBrand(car.getBrand());
    	         carRecord.setDescription(car.getDescription());
    	         carRecord.setSold(car.getSold());
    	         carRecord.setVehicle(car.getVehicle());
    	         carRecord.setYear(car.getYear());
    	            
    	         carRepository.save(carRecord);

    	         return new ResponseEntity(carRepository.save(carRecord), HttpStatus.OK);
    		}      	      
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/veiculos/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        try {
        	carRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/veiculos/por-marca/{brand}")
    public int listByBrand(@PathVariable("brand") String brand) {
        return carRepository.countAllByBrand(brand);
    }
    
    @GetMapping("/veiculos/por-decada/{year}")
    public int listByBrand(@PathVariable("year") int year) {
        return carRepository.carByYear(year);
    } 
}