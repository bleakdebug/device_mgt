package cn.mgt.device.bean;

import lombok.Data;

import java.util.Date;

/**
 * 组装线监控信息
 */
@Data
public class AssRecordDO {
    private int id;
    private String workstationId;
    private String partId;
    private String partType;
    private String assemblyDateTime;
    private String assemblyStatus;
    private String detail;
    private Integer deviceId;
}
