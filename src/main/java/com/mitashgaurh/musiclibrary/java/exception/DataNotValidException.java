package com.mitashgaurh.musiclibrary.java.exception;

public class DataNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataNotValidException() {
		super();
	}

	public DataNotValidException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataNotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotValidException(String message) {
		super(message);
	}

	public DataNotValidException(Throwable cause) {
		super(cause);
	}

}
