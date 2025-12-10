package cn.mgt.device.service;

import cn.mgt.device.bean.DeviceDO;
import cn.mgt.device.bean.ResultDO;

import java.util.List;

public interface DeviceService {
    List queryDevice(DeviceDO deviceDO) throws Exception;

    ResultDO deleteDevice(int id, Integer adminId, boolean isSuperAdmin) throws Exception;

    ResultDO updateDevice(DeviceDO deviceDO, Integer adminId, boolean isSuperAdmin) throws Exception;

    ResultDO addDevice(DeviceDO deviceDO);
}
