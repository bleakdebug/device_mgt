package cn.mgt.device.bean;

import lombok.Data;

import java.util.Date;

/**
 * 设备管理
 */

@Data
public class DeviceDO {
    private int id;
    private Integer no;
    private String type;
    private String model;
    private String status;
    private Date purchaseTime;
    private Date dumpeTime;
    private Integer adminId;

}
