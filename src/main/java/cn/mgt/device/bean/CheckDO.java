package cn.mgt.device.bean;

import lombok.Data;

import java.util.Date;

/**
 * 部件质量检测信息
 */
@Data
public class CheckDO {
    private int id;
    private String partId;
    private String projectName;
    private String result;
    private String time;
    private String remark;
}
