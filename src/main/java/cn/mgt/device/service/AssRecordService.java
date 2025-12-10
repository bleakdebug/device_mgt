package cn.mgt.device.service;

import cn.mgt.device.bean.AssRecordDO;
import cn.mgt.device.bean.ResultDO;

import java.util.List;

public interface AssRecordService {
    List queryAssRecord(AssRecordDO assRecordDO, Integer adminId, boolean isSuperAdmin) throws Exception;

    ResultDO deleteAssRecord(int id, Integer adminId, boolean isSuperAdmin) throws Exception;

    ResultDO addAssRecord(AssRecordDO assRecordDO) throws Exception;
    
    ResultDO updateAssRecord(AssRecordDO assRecordDO, Integer adminId, boolean isSuperAdmin) throws Exception;
}
