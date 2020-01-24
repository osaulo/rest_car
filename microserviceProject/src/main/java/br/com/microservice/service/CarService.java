package br.com.microservice.service;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.microservice.domain.entity.Car;
import br.com.microservice.domain.entity.User;
import br.com.microservice.dto.CarDTO;
import br.com.microservice.repository.CarRepository;
import br.com.microservice.repository.UserRepository;
import br.com.microservice.util.HeaderUtil;
import br.com.microservice.validation.CarValidation;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CarService {
	
	private CarRepository carRepository;
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private CarValidation carValidation; 

	@Autowired
	public CarService(CarRepository carRepository,
			CarValidation carValidation, UserRepository userRepository) {
		super();
		this.carRepository = carRepository;
		this.carValidation = carValidation;
		this.userRepository = userRepository;
		this.modelMapper = new ModelMapper();
	}
	
	@Transactional
	public CarDTO create(CarDTO carDTO) throws Exception {
		this.carValidation.validateUniqueLicensePlate(carDTO.getLicensePlate());
		Car car = modelMapper.map(carDTO, Car.class);
		car.setId(null);
		
		User user = userRepository.findByLogin(HeaderUtil.getLogin()).get();
		car.setUser(User.builder().id(user.getId()).build());
		this.setAuditingEntity(car);
		
		Car save = carRepository.save(car);
		
		return modelMapper.map(save, CarDTO.class);
	}
	
	@Transactional
	public CarDTO update(CarDTO carDTO, Long id_car) throws Exception {
		this.carValidation.validateUniqueLicensePlate(carDTO.getLicensePlate());
		Car car = modelMapper.map(carDTO, Car.class);
		this.setAuditingEntity(car);
		
		car.setId(id_car);
		User user = userRepository.findByLogin(HeaderUtil.getLogin()).get();
		car.setUser(User.builder().id(user.getId()).build());
		Car save = carRepository.save(car);
		
		return modelMapper.map(save, CarDTO.class);
	}
	
	@Transactional
	public void delete(Long id_car) throws Exception {
		this.carValidation.validateExist(id_car);
		carRepository.deleteById(id_car);
	}
	
	public Page<Car> list(Pageable pageable) throws IOException {
		return carRepository.findAll(pageable);
	}
	
	public CarDTO findById(Long id_car) throws Exception {
		Car car = this.carValidation.validateExist(id_car);
		
		return modelMapper.map(car, CarDTO.class);
	}
	
	private void setAuditingEntity(Car car) {
		car.setCreatedBy(HeaderUtil.getLogin()); //TODO 
		car.setCreatedBy("system");
	}
}
