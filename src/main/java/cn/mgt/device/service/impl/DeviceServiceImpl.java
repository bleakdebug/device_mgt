package cn.mgt.device.service.impl;

import cn.mgt.device.bean.DeviceDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.service.DeviceService;
import cn.mgt.device.dao.DeviceDao;
import cn.mgt.device.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;  //注入Dao


    //@Override
    public List queryDevice(DeviceDO deviceDO) throws Exception{
        return deviceDao.queryDevice(deviceDO);
    }

   // @Override
    public ResultDO addDevice(DeviceDO deviceDO){
        int count = deviceDao.addDevice(deviceDO);
        return CommonUtil.sResult(count > 0,count > 0 ? "添加成功" : "添加失败");
    }

   // @Override
    public ResultDO deleteDevice(int id, Integer adminId, boolean isSuperAdmin) throws Exception{
        // 检查权限：如果不是超级管理员，需要验证设备是否属于该管理员
        if (!isSuperAdmin) {
            DeviceDO device = deviceDao.getDeviceById(id);
            if (device == null || !adminId.equals(device.getAdminId())) {
                return CommonUtil.sResult(false, "无权删除此设备");
            }
        }
        //删除当前id在数据库中的记录
        int count = deviceDao.deleteDevice(id);
        return CommonUtil.sResult(count > 0,count > 0 ? "删除成功" : "删除失败");
    }

   // @Override
    public ResultDO updateDevice(DeviceDO deviceDO, Integer adminId, boolean isSuperAdmin) throws Exception{
        // 检查权限：如果不是超级管理员，需要验证设备是否属于该管理员
        if (!isSuperAdmin) {
            DeviceDO existingDevice = deviceDao.getDeviceById(deviceDO.getId());
            if (existingDevice == null || !adminId.equals(existingDevice.getAdminId())) {
                return CommonUtil.sResult(false, "无权修改此设备");
            }
            // 确保不能修改adminId
            deviceDO.setAdminId(adminId);
        }
        int count = deviceDao.updateDevice(deviceDO);
        return CommonUtil.sResult(count > 0,count > 0 ? "修改成功" : "修改失败");
    }
}
