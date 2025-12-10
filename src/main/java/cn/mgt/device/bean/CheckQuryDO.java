package cn.mgt.device.bean;

import lombok.Data;

/**
 * 部件质量检测信息
 */
@Data
public class CheckQuryDO {
    private int id;
    private String partId;
    private String projectName;
    private String result;
}
