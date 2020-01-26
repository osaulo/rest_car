package br.com.microservice.service;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.microservice.domain.entity.User;
import br.com.microservice.dto.CarDTO;
import br.com.microservice.dto.UserDTO;
import br.com.microservice.repository.UserRepository;
import br.com.microservice.validation.UserValidation;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UserService {
	
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private UserValidation userValidation; 

	@Autowired
	public UserService(UserRepository userRepository,
			UserValidation userValidation) {
		super();
		this.userRepository = userRepository;
		this.userValidation = userValidation;
		this.modelMapper = new ModelMapper();
	}
	
	@Transactional
	public UserDTO create(UserDTO userDTO) throws Exception {
		this.userValidation.validateUniqueEmail(userDTO.getEmail());
		this.userValidation.validateUniqueLogin(userDTO.getLogin());
		User user = modelMapper.map(userDTO, User.class);
		user.setId(null);
		this.setAuditingEntity(user);
		
		User save = userRepository.save(user);
		
		return modelMapper.map(save, UserDTO.class);
	}
	
	@Transactional
	public UserDTO update(UserDTO userDTO, Long id_user) throws Exception {
		this.userValidation.validateUniqueEmail(userDTO.getEmail());
		this.userValidation.validateUniqueLogin(userDTO.getLogin());
		User user = modelMapper.map(userDTO, User.class);
		this.setAuditingEntity(user);
		
		user.setId(id_user);
		User save = userRepository.save(user);
		
		return modelMapper.map(save, UserDTO.class);
	}
	
	@Transactional
	public void delete(Long id_user) throws Exception {
		this.userValidation.validateExist(id_user);
		userRepository.deleteById(id_user);
	}
	
	public Page<User> list(Pageable pageable) throws IOException {
		return userRepository.findAll(pageable);
	}
	
	public UserDTO findById(Long id_user) throws Exception {
		User user = this.userValidation.validateExist(id_user);
		
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		userDTO.setCarDTOs(user.getCars().stream()
				.map(t -> modelMapper.map(t, CarDTO.class))
				.collect(Collectors.toList()));
		return userDTO;
	}
	
	private void setAuditingEntity(User user) {
		user.setCreatedBy(user.getLogin());
	}
}
