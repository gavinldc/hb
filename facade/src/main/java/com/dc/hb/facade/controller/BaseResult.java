package com.dc.hb.facade.controller;


import lombok.Data;

@Data
public class BaseResult {
    //结果代码，0为成功
    private String code;
    //结果描述，如果出错则为错误信息
    private String message;
    //结果状态，成功success，失败fail
    private String result;
    //数据
    private Object data;

    public BaseResult(String code, String message, String result, Object data) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.data = data;
    }

    public BaseResult() {
    }
}
