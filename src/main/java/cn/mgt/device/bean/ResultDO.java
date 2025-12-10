package cn.mgt.device.bean;

import lombok.Data;

/**
 * 返回信息
 */
@Data
public class ResultDO {
    private String message;
    private String code;
    private Object data;
}
