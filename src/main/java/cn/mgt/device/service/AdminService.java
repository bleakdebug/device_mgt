package cn.mgt.device.service;

import cn.mgt.device.bean.DeviceAdminDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.bean.SuperAdminDO;

import java.util.List;

public interface AdminService {
    ResultDO deviceAdminLogin(DeviceAdminDO deviceAdminDO);
    ResultDO superAdminLogin(SuperAdminDO superAdminDO);
    DeviceAdminDO getDeviceAdminById(int id);
    SuperAdminDO getSuperAdminById(int id);
    
    // 设备管理员管理（仅超级管理员）
    List queryDeviceAdmin(DeviceAdminDO deviceAdminDO);
    ResultDO addDeviceAdmin(DeviceAdminDO deviceAdminDO);
    ResultDO updateDeviceAdmin(DeviceAdminDO deviceAdminDO);
    ResultDO deleteDeviceAdmin(int id);
}

