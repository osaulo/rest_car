package br.com.microservice.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.microservice.domain.AbstractAuditingEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@Getter @Setter @NoArgsConstructor
public class Car extends AbstractAuditingEntity implements Serializable {
	
	private static final long serialVersionUID = -5812478667253417292L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "year", nullable = false)
	private Integer year;
	
	@Column(name = "licensePlate", nullable = false, unique = true)
	private String licensePlate;
	
	@Column(name = "model", nullable = false)
	private String model;
	
	@Column(name = "color", nullable = false)
	private String color;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
