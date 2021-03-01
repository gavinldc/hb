package com.dc.hb.facade.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class BaseController {

    /**
     * 成功报文
     * @param data
     * @return
     */
    public ResponseEntity<BaseResult> success(Object data) {
        return  ResponseEntity.ok(new SuccessResult(data));
    }
    /**
     * 失败报文
     * @return
     */
    public ResponseEntity<BaseResult> paramError() {
        return ResponseEntity.ok(new FailResult("996", "请求参数错误"));
    }

    /**
     * 失败报文
     * @return
     */
    public ResponseEntity<BaseResult> paramError(String message) {
        log.info("paramError message:[{}]", message);
        return ResponseEntity.ok(new FailResult("999", message));
    }

}
