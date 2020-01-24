package br.com.microservice.validation;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservice.domain.entity.User;
import br.com.microservice.repository.UserRepository;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UserValidation {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserValidation(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public User validateExist(Long id_user) throws Exception {

		Optional<User> optional = userRepository.findById(id_user);

		if (!optional.isPresent()) {
			throw new Exception("User not exist!");
		}
		
		return optional.get();
	}

	public void validateUniqueEmail(String valor) throws Exception {
		Optional<User> optional = userRepository.findByEmail(valor);

		if (optional.isPresent()) {
			throw new ConstraintViolationException("Email already exists", null);
		}
	}
	
	public void validateUniqueLogin(String valor) throws Exception {
		Optional<User> optional = userRepository.findByLogin(valor);

		if (optional.isPresent()) {
			throw new ConstraintViolationException("Login already exists", null);
		}
	}
}
