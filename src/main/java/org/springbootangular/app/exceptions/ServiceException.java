/**
 * 
 */
package org.springbootangular.app.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author jmedina
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 5247139263101233666L;
	private final HttpStatus httpStatus;

	public ServiceException( String msg, HttpStatus status ) {
		super(msg);
		this.httpStatus = status;
	}

	public ServiceException(String msg, Exception e, HttpStatus httpStatus) {
		super(msg,e);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
