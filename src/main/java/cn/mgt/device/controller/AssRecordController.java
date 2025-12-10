package cn.mgt.device.controller;

import cn.mgt.device.bean.AssRecordDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.service.AssRecordService;
import cn.mgt.device.util.CommonUtil;
import cn.mgt.device.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/api/assRecord")
public class AssRecordController {

    @Autowired
    private AssRecordService assRecordService;  //注入Service
    
    @Autowired
    private cn.mgt.device.service.DeviceService deviceService;  //注入DeviceService

    @RequestMapping(value = "/addAssRecord", method = RequestMethod.POST)
    public ResultDO addAssRecord(@RequestBody AssRecordDO assRecordDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return CommonUtil.sResult(false, "请先登录");
        }
        // 如果是设备管理员，需要验证deviceId是否属于该管理员
        if (SessionUtil.isDeviceAdmin(session) && assRecordDO.getDeviceId() != null) {
            Integer adminId = SessionUtil.getAdminId(session);
            cn.mgt.device.bean.DeviceDO device = new cn.mgt.device.bean.DeviceDO();
            device.setId(assRecordDO.getDeviceId());
            device.setAdminId(adminId);
            java.util.List devices = deviceService.queryDevice(device);
            if (devices == null || devices.isEmpty()) {
                return CommonUtil.sResult(false, "无权操作此设备");
            }
        }
        return assRecordService.addAssRecord(assRecordDO);
    }

    @RequestMapping(value = "/deleteAssRecord", method = RequestMethod.POST)
    public ResultDO deleteAssRecord(@RequestBody AssRecordDO assRecordDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return CommonUtil.sResult(false, "请先登录");
        }
        return assRecordService.deleteAssRecord(assRecordDO.getId(), SessionUtil.getAdminId(session), SessionUtil.isSuperAdmin(session));
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public List queryAssRecord(@RequestBody AssRecordDO assRecordDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return new java.util.ArrayList();
        }
        // 直接调用Service层，Service层会处理权限过滤
        return assRecordService.queryAssRecord(assRecordDO, SessionUtil.getAdminId(session), SessionUtil.isSuperAdmin(session));
    }

    @RequestMapping(value = "/updateAssRecord", method = RequestMethod.POST)
    public ResultDO updateAssRecord(@RequestBody AssRecordDO assRecordDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return CommonUtil.sResult(false, "请先登录");
        }
        // 如果是设备管理员，需要验证deviceId是否属于该管理员
        if (SessionUtil.isDeviceAdmin(session) && assRecordDO.getDeviceId() != null) {
            Integer adminId = SessionUtil.getAdminId(session);
            cn.mgt.device.bean.DeviceDO device = new cn.mgt.device.bean.DeviceDO();
            device.setId(assRecordDO.getDeviceId());
            device.setAdminId(adminId);
            java.util.List devices = deviceService.queryDevice(device);
            if (devices == null || devices.isEmpty()) {
                return CommonUtil.sResult(false, "无权操作此设备");
            }
        }
        return assRecordService.updateAssRecord(assRecordDO, SessionUtil.getAdminId(session), SessionUtil.isSuperAdmin(session));
    }

}
