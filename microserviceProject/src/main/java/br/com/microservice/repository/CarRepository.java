package br.com.microservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.microservice.domain.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

	Optional<Car> findByLicensePlate(String valor);
	
}
