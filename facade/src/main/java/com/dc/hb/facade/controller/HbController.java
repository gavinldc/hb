package com.dc.hb.facade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.hb.dtos.UserGrabHbReqDTO;
import com.dc.hb.dtos.UserSendHbReqDTO;
import com.dc.hb.service.IHbService;
/*
 *  =======================================
 *  项目：hb
 *  模块：facade
 *  文件：HbController.java
 *  创建：gavin
 *  更新：2021/3/1 下午16:30
 *  =======================================
 */
@RestController
@RequestMapping("hb")
public class HbController extends BaseController{

	@Autowired
	private IHbService hbService;
	
	
	/**
	 * 发送红包
	 * @param dto
	 * @return
	 */
	@PostMapping("/sendHbByGroup")
    public ResponseEntity<BaseResult> sendHbByGroup(@RequestBody UserSendHbReqDTO dto){
		
		dto.validate();
		
		hbService.sendHb(dto);

        return success(null);
    }
    	
	
	
	/**
	 * 抢红包
	 * @param dto
	 * @return
	 */
	@PostMapping("/grabHb")
    public ResponseEntity<BaseResult> grabHb(@RequestBody UserGrabHbReqDTO dto){
		
		dto.validate();
        return success(hbService.grabHb(dto));
    }
    	
	
	
}
