package br.com.microservice.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Data
@ApiModel(value = "UserDTO", description = "Represents user")
public class UserDTO implements Serializable {

	private Long id;
	
	@NotNull(message = "Missing fields")
    @ApiModelProperty(value = "The user's firstname", required = true, example = "Joao", position = 1)
	private String firstName;
	
	@NotNull(message = "Missing fields")
    @ApiModelProperty(value = "The user's lastName", required = true, example = "Silva", position = 2)
	private String lastName;
	
	@Email(message = "Invalid fields")
	@NotNull(message = "Missing fields")
    @ApiModelProperty(value = "The user's email", required = true, example = "joao@gmail.com", position = 3)
	private String email;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "Missing fields")
	@ApiModelProperty(value = "The user's birth date begin interval. Pattern: YYYY-MM-DD, year, month, day", 
	required = true, example = "1980-10-01", position=4)
	private DateTime birthday;
	
	@NotNull(message = "Missing fields")
    @ApiModelProperty(value = "The user's login, enables letters and numbers, is unique", required = true, example = "joao", position = 5)
	private String login;
	
	@NotNull(message = "Missing fields")
	@ApiModelProperty(value = "The user's password, enables letters and numbers", 
		required = true, position = 6)
	private String password;
	
	@NotNull(message = "Missing fields")
	@ApiModelProperty(value = "The user's phone", 
			required = true, example = "5581999999999", position = 7)
	private String phone;
}
