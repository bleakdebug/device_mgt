package cn.mgt.device.service.impl;

import cn.mgt.device.bean.DeviceAdminDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.bean.SuperAdminDO;
import cn.mgt.device.dao.DeviceAdminDao;
import cn.mgt.device.dao.SuperAdminDao;
import cn.mgt.device.service.AdminService;
import cn.mgt.device.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private DeviceAdminDao deviceAdminDao;

    @Autowired
    private SuperAdminDao superAdminDao;

    @Override
    public ResultDO deviceAdminLogin(DeviceAdminDO deviceAdminDO) {
        DeviceAdminDO admin = deviceAdminDao.login(deviceAdminDO);
        if (admin != null) {
            return CommonUtil.sResult(true, "登录成功", admin);
        } else {
            return CommonUtil.sResult(false, "用户名或密码错误");
        }
    }

    @Override
    public ResultDO superAdminLogin(SuperAdminDO superAdminDO) {
        SuperAdminDO admin = superAdminDao.login(superAdminDO);
        if (admin != null) {
            return CommonUtil.sResult(true, "登录成功", admin);
        } else {
            return CommonUtil.sResult(false, "用户名或密码错误");
        }
    }

    @Override
    public DeviceAdminDO getDeviceAdminById(int id) {
        return deviceAdminDao.getById(id);
    }

    @Override
    public SuperAdminDO getSuperAdminById(int id) {
        return superAdminDao.getById(id);
    }

    @Override
    public List queryDeviceAdmin(DeviceAdminDO deviceAdminDO) {
        return deviceAdminDao.queryDeviceAdmin(deviceAdminDO);
    }

    @Override
    public ResultDO addDeviceAdmin(DeviceAdminDO deviceAdminDO) {
        int count = deviceAdminDao.addDeviceAdmin(deviceAdminDO);
        return CommonUtil.sResult(count > 0, count > 0 ? "添加成功" : "添加失败");
    }

    @Override
    public ResultDO updateDeviceAdmin(DeviceAdminDO deviceAdminDO) {
        int count = deviceAdminDao.updateDeviceAdmin(deviceAdminDO);
        return CommonUtil.sResult(count > 0, count > 0 ? "更新成功" : "更新失败");
    }

    @Override
    public ResultDO deleteDeviceAdmin(int id) {
        int count = deviceAdminDao.deleteDeviceAdmin(id);
        return CommonUtil.sResult(count > 0, count > 0 ? "删除成功" : "删除失败");
    }
}

