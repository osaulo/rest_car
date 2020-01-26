package br.com.microservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.constants.AppConstants;
import br.com.microservice.domain.entity.Car;
import br.com.microservice.dto.CarDTO;
import br.com.microservice.service.CarService;
import br.com.microservice.util.BuilderLink;
import br.com.microservice.util.HeaderUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = AppConstants.PATH + "/cars")
@Api(value = "CarRestController", description = "Endpoint for cars management")
public class CarRestController {
	
	private CarService carService;
	private ModelMapper modelMapper;
	public BuilderLink builderLink;

	@Autowired
	public CarRestController(CarService carService, BuilderLink builderLink) {
		super();
		this.carService = carService;
		this.modelMapper = new ModelMapper();
		this.builderLink = builderLink;
	}

	@Timed
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a new car",
            notes = "Creates a new car. Some fields are mandatory, see the api documentation for more information!",
            response = CarDTO.class,
            responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = HttpStatus.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<CarDTO> create(@RequestBody CarDTO dto) throws Exception {
        return new ResponseEntity<>(this.carService.create(dto), HttpStatus.CREATED);
    }

    @Timed
    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a car",
            notes = "Update a car. See the api documentation for more information!",
            response = CarDTO.class,
            responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HttpStatus.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<CarDTO> update(@Validated @RequestBody CarDTO dto, @PathVariable Long id) throws Exception {
    	return new ResponseEntity<>(this.carService.update(dto, id), HttpStatus.OK);
    }

    @Timed
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets all cars",
            notes = "Obtains cars",
            response = CarDTO.class,
            responseContainer = "Object")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "size", value = "size of page", required = false, 
			dataType = "Integer", paramType = "query", defaultValue="20"),
		@ApiImplicitParam(name = "page", value = "number of page", required = false, 
		dataType = "Integer", paramType = "query", defaultValue="0")
	})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HttpStatus.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<BuilderLink> list(Pageable pageable) throws IOException{
    	Page<Car> page = this.carService.list(pageable);
    	
    	List<CarDTO> dtos = page.stream()
	    	.map(t -> modelMapper.map(t, CarDTO.class))
	    	.collect(Collectors.toList());
    	
    	ResponseEntity<List<CarDTO>> response = new ResponseEntity<>(dtos, HeaderUtil.createPaginationHeader(page), HttpStatus.OK);
		return this.builderLink.byFilterLink(this.getClass(), "financial-provider", pageable, response.getBody(), response.getHeaders(), dtos);
    }

    @Timed
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets car",
            notes = "Gets the car by id",
            response = CarDTO.class,
            responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HttpStatus.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<CarDTO> findById(@PathVariable Long id) throws Exception{
    	CarDTO carDTO = this.carService.findById(id);

        if (Objects.nonNull(carDTO)) {
            return new ResponseEntity<>(carDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Timed
    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a car",
            notes = "Delete a car, with past id to segment of url!",
            response = HttpStatus.class,
            responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content", response = HttpStatus.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws Exception {
        this.carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
