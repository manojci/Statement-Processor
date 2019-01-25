package com.banking.rb.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Christopher Error Response
 */
@Getter
@Setter
public class ErrorResponse {

	private String errDescription;
	private int statusCode;
}
