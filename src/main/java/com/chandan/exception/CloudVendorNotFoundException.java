package com.chandan.exception;

public class CloudVendorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5348697595428256796L;

	public CloudVendorNotFoundException(String msg) {
		super(msg);
	}

	public CloudVendorNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
