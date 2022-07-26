package com.icerabbit.wirefish.io;

import lombok.Data;

/**
 * @Author iceRabbit
 * @Date 3/22/22 8:55 AM
 * @Description: TODO
 **/
@Data
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public Response(){}

    public static <T> Response<T> success(String message){
        Response<T> response = new Response<>();
        response.setCode(Code.SUCCESS.getCode());
        response.setMessage(message);
        response.setData(null);
        return response;
    }

    public static <T> Response<T> success(T data){
        Response<T> response = new Response<>();
        response.setCode(Code.SUCCESS.getCode());
        response.setMessage(Code.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> success(T data, String message){
        Response<T> response = new Response<>();
        response.setCode(Code.SUCCESS.getCode());
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> error(T data){
        Response<T> response = new Response<>();
        response.setCode(Code.ERROR.getCode());
        response.setMessage(Code.ERROR.getMessage());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> error(String message){
        Response<T> response = new Response<>();
        response.setCode(Code.ERROR.getCode());
        response.setMessage(message);
        response.setData(null);
        return response;
    }

    public static <T> Response<T> error(T data, String message){
        Response<T> response = new Response<>();
        response.setCode(Code.ERROR.getCode());
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
