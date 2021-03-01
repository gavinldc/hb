package com.dc.hb.facade.controller;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class FailResult extends BaseResult {
    /**
     * Exception 推荐未捕获业务异常
     * @param code
     * @param message
     * @param errorCode
     */
    public FailResult(String code, String message,String errorCode) {
        super(code,message,errorCode ,null);
        log.error("error code:[{}]",this.getResult());
    }

    /**
     * APPException 推荐通过此方法返回错误报文
     * @param code
     * @param message
     */
    public FailResult(String code, String message) {
        super(code,message, String.valueOf((int)((Math.random()*9+1)*1000)) ,null);
        log.warn("error code:[{}]",this.getResult());
    }
}
