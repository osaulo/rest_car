package br.com.microservice.validation;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservice.domain.entity.Car;
import br.com.microservice.repository.CarRepository;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CarValidation {
	
	private CarRepository carRepository;
	
	@Autowired
	public CarValidation(CarRepository carRepository) {
		super();
		this.carRepository = carRepository;
	}

	public Car validateExist(Long id_car) throws Exception {

		Optional<Car> optional = carRepository.findById(id_car);

		if (!optional.isPresent()) {
			throw new Exception("Car not exist!");
		}
		
		return optional.get();
	}

	public void validateUniqueLicensePlate(String valor) throws Exception {
		Optional<Car> optional = carRepository.findByLicensePlate(valor);

		if (optional.isPresent()) {
			throw new ConstraintViolationException("License Plate already exists", null);
		}
	}
}
