package br.com.microservice.util;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BuilderLink extends ResourceSupport{
	
	private String create = "create";
	private String delete = "delete";
	private String update = "update";
	private String byFilter = "byFilter";
	private String size = "size";
	private String page = "page";
	private String tPages = "TotalPages";
	private String tElements = "TotalElements";
	private String next = "next";
	private String prev = "prev";
	
	private Integer startPages = 0;
			
	Object data;
	Object filter;
	Integer totalPages = null;
	Integer totalElements = null;
	
	public ResponseEntity<BuilderLink> getLink(Class<?> entity, String requestMap, Long id, Object data) {
		BuilderLink resource = new BuilderLink();
		resource.data = data;
		resource.add( linkTo(entity).slash(requestMap).slash(id).withSelfRel() );		
		resource.add( linkTo(entity).slash(requestMap).slash(delete).slash(id).withRel(delete) );
		resource.add( linkTo(entity).slash(requestMap).slash(update).slash(id).withRel(update) );
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	public ResponseEntity<BuilderLink> byFilterLink(Class<?> entity, String requestMap, Pageable pageable, Object data, HttpHeaders header, Object filter) {
		BuilderLink resource = new BuilderLink();
		resource.data = data;
		resource.filter = filter;
		resource.totalPages = getElement(header, tPages);
		resource.totalElements = getElement(header, tElements);
		addNext(resource, pageable);
		addPrev(resource, pageable);
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	public void addNext(BuilderLink resource, Pageable pageable) {
		Integer paginas = resource.totalPages;
		Integer elements = resource.totalElements;
		/**
		 * The pageable start a count page number in 0, because that
		 * is necessary subtract one to total pages.
		 */
		if(paginas != null && pageable.getPageNumber() < (--paginas)){
			Integer nextPage = pageable.getPageNumber();
			nextPage++;			
			resource.add( factoryLink(next, nextPage, elements, resource.filter) );
		}
	}
	
	
	
	public void addPrev(BuilderLink resource, Pageable pageable) {
		Integer paginas = resource.totalPages;
		Integer elements = resource.totalElements;
		if(paginas != null && pageable.getPageNumber() != startPages){
			Integer prevPage = (pageable.getPageNumber() > paginas) ? paginas : pageable.getPageNumber();
			prevPage--;
			resource.add( factoryLink(prev, prevPage, elements, resource.filter) );
		}		
	}
	
	public Integer getElement(HttpHeaders header, String element) {
		List<String> list = header.get(element);
		if(list != null) {
			Optional<String> value = list.stream().findFirst();
			if(value.isPresent()) {
				return Integer.parseInt(value.get());
			}
		}
		return null;
	}
	
	private ServletUriComponentsBuilder createBuilder() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri();
	}
	
	private Link factoryLink(String ref, Integer valuePage, Integer valueElements, Object filter) {
		UriComponentsBuilder path = createBuilder()
				 .queryParam(page, valuePage)
				 .queryParam(size, valueElements);
		getQueryParameteresFilter(filter, path);
		return new Link(path.toUriString(), ref); 
	}
	
	public UriComponentsBuilder factoryLink(Pageable pageable, Object filter, String uri) {
		UriComponentsBuilder path = UriComponentsBuilder.fromHttpUrl(uri)
			    .queryParam("size", pageable.getPageSize())
                .queryParam("page", pageable.getPageNumber());
		if(pageable.getSort() != null) {
			getSort(pageable.getSort(), path);
		}		
		getQueryParameteresFilter(filter, path);
		return path; 
	}
	
	public UriComponentsBuilder factoryLink(Object filter, String uri) {
		UriComponentsBuilder path = UriComponentsBuilder.fromHttpUrl(uri);
		getQueryParameteresFilter(filter, path);
		return path; 
	}
	
	public UriComponentsBuilder getSort(Sort sort, UriComponentsBuilder path) {
		for (Order order : sort) {
			String orderString = order.getProperty() + "," + order.getDirection();
			path.queryParam("sort", orderString);
		}
		return path;
	}
	
	public UriComponentsBuilder getQueryParameteresFilter(Object filter, UriComponentsBuilder path){

		ReflectionUtils.doWithFields(filter.getClass(),

				new FieldCallback(){

			@Override
			public void doWith(final Field field) throws IllegalArgumentException,
			IllegalAccessException{

				path.queryParam(field.getName(), field.get(filter));	        	
			}
		},
		new FieldFilter(){

			@Override
			public boolean matches(final Field field){
				field.setAccessible(true);
				try {
					return field.get(filter) != null;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});

		return path;
	}
		 
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}
	
}
