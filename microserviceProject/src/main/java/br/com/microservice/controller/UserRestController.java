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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.constants.AppConstants;
import br.com.microservice.domain.entity.User;
import br.com.microservice.dto.UserDTO;
import br.com.microservice.service.UserService;
import br.com.microservice.util.BuilderLink;
import br.com.microservice.util.HeaderUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = AppConstants.PATH)
public class UserRestController {
	
	private UserService userService;
	private ModelMapper modelMapper;
	public BuilderLink builderLink;

	@Autowired
	public UserRestController(UserService userService, BuilderLink builderLink) {
		super();
		this.userService = userService;
		this.modelMapper = new ModelMapper();
		this.builderLink = builderLink;
	}

	@Timed
    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a new user",
            notes = "Creates a new user. Some fields are mandatory, see the api documentation for more information!",
            response = UserDTO.class,
            responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = HttpStatus.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) throws Exception {
        return new ResponseEntity<>(this.userService.create(dto), HttpStatus.CREATED);
    }

    @Timed
    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a user",
            notes = "Update a user. See the api documentation for more information!",
            response = UserDTO.class,
            responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HttpStatus.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<UserDTO> update(@Validated @RequestBody UserDTO dto, @PathVariable Long id) throws Exception {
    	return new ResponseEntity<>(this.userService.update(dto, id), HttpStatus.OK);
    }

    @Timed
    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets all users",
            notes = "Obtains users",
            response = UserDTO.class,
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
    	Page<User> page = this.userService.list(pageable);
    	
    	List<UserDTO> dtos = page.stream()
	    	.map(t -> modelMapper.map(t, UserDTO.class))
	    	.collect(Collectors.toList());
    	
    	ResponseEntity<List<UserDTO>> response = new ResponseEntity<>(dtos, HeaderUtil.createPaginationHeader(page), HttpStatus.OK);
		return this.builderLink.byFilterLink(this.getClass(), "financial-provider", pageable, response.getBody(), response.getHeaders(), dtos);
    }

    @Timed
    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets user",
            notes = "Gets the user by id",
            response = UserDTO.class,
            responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = HttpStatus.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) throws Exception{
    	UserDTO userDTO = this.userService.findById(id);

        if (Objects.nonNull(userDTO)) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Timed
    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a user",
            notes = "Delete a user, with past id to segment of url!",
            response = HttpStatus.class,
            responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content", response = HttpStatus.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws Exception {
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
