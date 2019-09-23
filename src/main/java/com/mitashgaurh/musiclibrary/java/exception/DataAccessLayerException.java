package com.mitashgaurh.musiclibrary.java.exception;

public class DataAccessLayerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataAccessLayerException() {
        super();
    }

    public DataAccessLayerException(String arg0, Throwable arg1, boolean arg2,
                                    boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public DataAccessLayerException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DataAccessLayerException(String arg0) {
        super(arg0);
    }

    public DataAccessLayerException(Throwable arg0) {
        super(arg0);
    }
}
