package cn.mgt.device.controller;

import cn.mgt.device.bean.DeviceDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.service.DeviceService;
import cn.mgt.device.util.CommonUtil;
import cn.mgt.device.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;  //注入Service

    @RequestMapping(value = "/addDevice", method = RequestMethod.POST)
    public ResultDO addDevice(@RequestBody DeviceDO deviceDO, HttpSession session) {
        if (!SessionUtil.isLoggedIn(session)) {
            return CommonUtil.sResult(false, "请先登录");
        }
        // 如果是设备管理员，自动设置adminId
        if (SessionUtil.isDeviceAdmin(session)) {
            deviceDO.setAdminId(SessionUtil.getAdminId(session));
        }
        return deviceService.addDevice(deviceDO);
    }

    @RequestMapping(value = "/deleteDevice", method = RequestMethod.POST)
    public ResultDO deleteDevice(@RequestBody DeviceDO deviceDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return CommonUtil.sResult(false, "请先登录");
        }
        return deviceService.deleteDevice(deviceDO.getId(), SessionUtil.getAdminId(session), SessionUtil.isSuperAdmin(session));
    }

    @RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
    public ResultDO updateDevice(@RequestBody DeviceDO deviceDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return CommonUtil.sResult(false, "请先登录");
        }
        return deviceService.updateDevice(deviceDO, SessionUtil.getAdminId(session), SessionUtil.isSuperAdmin(session));
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public List queryDevice(@RequestBody DeviceDO deviceDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return new ArrayList();
        }
        // 如果是设备管理员，只查询自己的设备
        if (SessionUtil.isDeviceAdmin(session)) {
            deviceDO.setAdminId(SessionUtil.getAdminId(session));
        }
        return deviceService.queryDevice(deviceDO);
    }

}
