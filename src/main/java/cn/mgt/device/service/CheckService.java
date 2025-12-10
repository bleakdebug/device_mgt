package cn.mgt.device.service;

import cn.mgt.device.bean.CheckDO;
import cn.mgt.device.bean.CheckQuryDO;
import cn.mgt.device.bean.ResultDO;

import java.util.List;

public interface CheckService {
    List queryCheck(CheckQuryDO checkQuryDO, Integer adminId, boolean isSuperAdmin) throws Exception;
    ResultDO addCheck(CheckDO checkDO, Integer adminId, boolean isSuperAdmin) throws Exception;
    ResultDO updateCheck(CheckDO checkDO, Integer adminId, boolean isSuperAdmin) throws Exception;
    ResultDO deleteCheck(int id, Integer adminId, boolean isSuperAdmin) throws Exception;
}
