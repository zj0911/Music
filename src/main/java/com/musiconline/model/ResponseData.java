package com.musiconline.model;

import lombok.Data;

@Data
public class ResponseData {
    private int code;
    private boolean success;
    private String message;
    private Object data;

    public ResponseData(int code, boolean success, String message, Object data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
