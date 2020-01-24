package br.com.microservice.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Utility class for HTTP header creation.
 */
public class HeaderUtil {

	/**
	 * <p>
	 * Creates some specifics attributes into the HTTP header @see {@link HttpHeaders}.
	 * This header means to be used for pageable search and helps the client apps 
	 * with informations like, total number of pages, and the total number of 
	 * results elements.    
	 * 
	 * @param page
	 * 		@see {@link Page}
	 * @return
	 * 		The configured @see {@link HttpHeaders}
	 */
	@SuppressWarnings("rawtypes")
	public static HttpHeaders createPaginationHeader(Page page) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("TotalPages", new Integer(page.getTotalPages()).toString());
		headers.add("TotalElements", new Long(page.getTotalElements()).toString());
		return headers;
	}
	
	public static String getLogin() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//return request.getHeader("login"); //TODO
		return "joao"; //TODO
	}
}