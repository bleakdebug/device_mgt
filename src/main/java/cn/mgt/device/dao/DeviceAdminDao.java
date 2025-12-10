package cn.mgt.device.dao;

import cn.mgt.device.bean.DeviceAdminDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceAdminDao {
    DeviceAdminDO login(DeviceAdminDO deviceAdminDO);

    DeviceAdminDO getById(int id);

    List queryDeviceAdmin(DeviceAdminDO deviceAdminDO);

    int addDeviceAdmin(DeviceAdminDO deviceAdminDO);

    int updateDeviceAdmin(DeviceAdminDO deviceAdminDO);

    int deleteDeviceAdmin(int id);
}

