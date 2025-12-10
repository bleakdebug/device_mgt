package cn.mgt.device.dao;


import cn.mgt.device.bean.DeviceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceDao {
    List queryDevice(DeviceDO deviceDO);

    int deleteDevice(int id);

    int addDevice(DeviceDO deviceDO);

    int updateDevice(DeviceDO deviceDO);
    
    DeviceDO getDeviceById(int id);
}
