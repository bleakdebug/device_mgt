package cn.mgt.device.controller;

import cn.mgt.device.bean.CheckDO;
import cn.mgt.device.bean.CheckQuryDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.service.CheckService;
import cn.mgt.device.util.CommonUtil;
import cn.mgt.device.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/api/check")
public class CheckController {

    @Autowired
    private CheckService checkService;  //注入Service
    
    @Autowired
    private cn.mgt.device.service.AssRecordService assRecordService;  //注入AssRecordService

    @RequestMapping(value = "/addCheck", method = RequestMethod.POST)
    public ResultDO addCheck(@RequestBody CheckDO checkDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return CommonUtil.sResult(false, "请先登录");
        }
        // 如果是设备管理员，需要验证partId对应的组装记录是否属于该管理员管理的设备
        if (SessionUtil.isDeviceAdmin(session) && checkDO.getPartId() != null) {
            Integer adminId = SessionUtil.getAdminId(session);
            // 查询该partId对应的组装记录，检查其device_id是否属于该管理员
            cn.mgt.device.bean.AssRecordDO queryRecord = new cn.mgt.device.bean.AssRecordDO();
            queryRecord.setPartId(checkDO.getPartId());
            List records = assRecordService.queryAssRecord(queryRecord, adminId, false);
            if (records == null || records.isEmpty()) {
                return CommonUtil.sResult(false, "该部件编号不属于您管理的设备");
            }
        }
        return checkService.addCheck(checkDO, SessionUtil.getAdminId(session), SessionUtil.isSuperAdmin(session));
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public List queryCheck(@RequestBody CheckQuryDO checkQuryDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return new java.util.ArrayList();
        }
        // Service层会处理权限过滤：设备管理员只能查看自己管理的设备对应的part_id的记录
        return checkService.queryCheck(checkQuryDO, SessionUtil.getAdminId(session), SessionUtil.isSuperAdmin(session));
    }

    @RequestMapping(value = "/updateCheck", method = RequestMethod.POST)
    public ResultDO updateCheck(@RequestBody CheckDO checkDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return CommonUtil.sResult(false, "请先登录");
        }
        // 如果是设备管理员，需要验证partId对应的组装记录是否属于该管理员管理的设备
        if (SessionUtil.isDeviceAdmin(session) && checkDO.getPartId() != null) {
            Integer adminId = SessionUtil.getAdminId(session);
            cn.mgt.device.bean.AssRecordDO queryRecord = new cn.mgt.device.bean.AssRecordDO();
            queryRecord.setPartId(checkDO.getPartId());
            List records = assRecordService.queryAssRecord(queryRecord, adminId, false);
            if (records == null || records.isEmpty()) {
                return CommonUtil.sResult(false, "该部件编号不属于您管理的设备");
            }
        }
        return checkService.updateCheck(checkDO, SessionUtil.getAdminId(session), SessionUtil.isSuperAdmin(session));
    }

    @RequestMapping(value = "/deleteCheck", method = RequestMethod.POST)
    public ResultDO deleteCheck(@RequestBody CheckDO checkDO, HttpSession session) throws Exception {
        if (!SessionUtil.isLoggedIn(session)) {
            return CommonUtil.sResult(false, "请先登录");
        }
        return checkService.deleteCheck(checkDO.getId(), SessionUtil.getAdminId(session), SessionUtil.isSuperAdmin(session));
    }

}
