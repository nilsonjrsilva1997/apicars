package com.apicars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apicars.models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	Car findById(long id);
    
    @Query("SELECT COUNT(*) FROM Car WHERE sold = false")
    int countCarNotSold();
    
    int countAllByBrand(String brand);
    
    @Query("SELECT COUNT(*) FROM Car WHERE year >= year AND year <= (year + 10)")
    int carByYear(@Param("year") int year);
}
