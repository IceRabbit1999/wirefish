package com.icerabbit.wirefish.io;

/**
 * @Author iceRabbit
 * @Date 3/22/22 8:55 AM
 * @Description: TODO
 **/
public enum Code {
    //success
    SUCCESS(1,"success"),
    //error
    ERROR(0,"error");

    private int code;
    private String message;

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
