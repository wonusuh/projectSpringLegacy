package org.zerock.service.exception;

import lombok.Getter;

@Getter
public class ReplyException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;

    public ReplyException(int code, String msg) {
	super(msg);
	this.code = code;
    }
}
