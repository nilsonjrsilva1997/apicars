package com.apicars.apicars.validators;

import java.util.ArrayList;
import java.util.List;

import com.apicars.apicars.models.Car;


public class CarValidator {
	private Car car;

	public CarValidator(Car car) {
		super();
		this.car = car;
	}
	
	public List<String> validate() {
		List<String> errors = new ArrayList<String>();
    	
    	if(this.car.getBrand() == null || this.car.getBrand() == "") {
    		errors.add("O campo Marca é obrigatório");
    	}
    	
    	if(this.car.getDescription() == null || this.car.getDescription() == "") {
    		errors.add("O campo Descrição é obrigatório");
    	}
    	
    	if(this.car.getVehicle() == null || this.car.getVehicle() == "") {
    		errors.add("O campo Veículo é obrigatório");
    	}
    	
    	if(this.car.getYear() == null || this.car.getYear() == 0) {
    		errors.add("O campo Ano é obrigatório");
    	}
    	
    	if(this.car.getSold() != true && this.car.getSold() != false) {
    		errors.add("O campo Vendido é obrigatório");
    	}
    	
    	return errors;
	}
}
