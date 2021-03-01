package com.dc.hb.facade.controller;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuccessResult extends BaseResult {
    public SuccessResult(Object data) {
        super("0", "", "success", data);
    }
}
