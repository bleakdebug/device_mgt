package cn.mgt.device.controller;

import cn.mgt.device.bean.DeviceAdminDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.service.AdminService;
import cn.mgt.device.util.CommonUtil;
import cn.mgt.device.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/deviceAdmin")
public class DeviceAdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/query")
    public List queryDeviceAdmin(@RequestBody DeviceAdminDO deviceAdminDO, HttpSession session) {
        if (!SessionUtil.isSuperAdmin(session)) {
            return new java.util.ArrayList();
        }
        return adminService.queryDeviceAdmin(deviceAdminDO);
    }

    @PostMapping("/add")
    public ResultDO addDeviceAdmin(@RequestBody DeviceAdminDO deviceAdminDO, HttpSession session) {
        if (!SessionUtil.isSuperAdmin(session)) {
            return CommonUtil.sResult(false, "无权限操作");
        }
        return adminService.addDeviceAdmin(deviceAdminDO);
    }

    @PostMapping("/update")
    public ResultDO updateDeviceAdmin(@RequestBody DeviceAdminDO deviceAdminDO, HttpSession session) {
        if (!SessionUtil.isSuperAdmin(session)) {
            return CommonUtil.sResult(false, "无权限操作");
        }
        return adminService.updateDeviceAdmin(deviceAdminDO);
    }

    @PostMapping("/delete")
    public ResultDO deleteDeviceAdmin(@RequestBody DeviceAdminDO deviceAdminDO, HttpSession session) {
        if (!SessionUtil.isSuperAdmin(session)) {
            return CommonUtil.sResult(false, "无权限操作");
        }
        return adminService.deleteDeviceAdmin(deviceAdminDO.getId());
    }
}

