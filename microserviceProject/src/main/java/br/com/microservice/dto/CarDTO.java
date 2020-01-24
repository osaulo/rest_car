package br.com.microservice.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.microservice.domain.AbstractAuditingEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@Getter @Setter @NoArgsConstructor
public class CarDTO extends AbstractAuditingEntity implements Serializable {
	
	private static final long serialVersionUID = -5812478667253417292L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Missing fields")
    @ApiModelProperty(value = "The car user's year", required = true, example = "2020", position = 1)
	private Integer year;
	
	@NotNull(message = "Missing fields")
    @ApiModelProperty(value = "The car user's licensePlate", required = true, example = "AAA2020", position = 2)
	private String licensePlate;
	
	@NotNull(message = "Missing fields")
    @ApiModelProperty(value = "The car user's model", required = true, example = "fusca", position = 3)
	private String model;
	
	@NotNull(message = "Missing fields")
    @ApiModelProperty(value = "The car user's color", required = true, example = "branco", position = 4)
	private String color;
	
	private UserDTO userDTO;
}
