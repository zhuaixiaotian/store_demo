package com.cy.store.util;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;


@Data
public class jsonresult<T>  {
    private  T data;
    private  Integer state;
    private  String msg;

    public  jsonresult(){}


    public jsonresult(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public jsonresult(T data) {
        this.data = data;
    }

    public jsonresult(T data, Integer state, String msg) {
        this.data = data;
        this.state = state;
        this.msg = msg;
    }

    public jsonresult(Integer state) {
        this.state = state;
    }

    public jsonresult(T data, Integer state) {
        this.data = data;
        this.state = state;

    }
}
