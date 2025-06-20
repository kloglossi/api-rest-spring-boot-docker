package com.tech.infrastructure.utils;

import lombok.Data;

import java.util.HashMap;

@Data
public class OperationResult <T>{

    private T data;
    private HashMap<String,String> errors;
    private Boolean isSuccess ;

    public OperationResult(T data, HashMap<String,String>errors){
        this.data = data;
        this.errors=errors;
        this.isSuccess = errors.isEmpty();
    }

}
