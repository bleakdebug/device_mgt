package cn.mgt.device.controller;

import cn.mgt.device.bean.DeviceAdminDO;
import cn.mgt.device.bean.ResultDO;
import cn.mgt.device.bean.SuperAdminDO;
import cn.mgt.device.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * 显示登录页面
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * 设备管理员登录
     */
    @PostMapping("/api/login/deviceAdmin")
    @ResponseBody
    public ResultDO deviceAdminLogin(@RequestBody DeviceAdminDO deviceAdminDO, HttpSession session) {
        ResultDO result = adminService.deviceAdminLogin(deviceAdminDO);
        if ("1".equals(result.getCode())) {
            // 登录成功，保存到Session
            DeviceAdminDO admin = (DeviceAdminDO) result.getData();
            session.setAttribute("adminId", admin.getId());
            session.setAttribute("adminName", admin.getName());
            session.setAttribute("adminType", "device");
        }
        return result;
    }

    /**
     * 超级管理员登录
     */
    @PostMapping("/api/login/superAdmin")
    @ResponseBody
    public ResultDO superAdminLogin(@RequestBody SuperAdminDO superAdminDO, HttpSession session) {
        ResultDO result = adminService.superAdminLogin(superAdminDO);
        if ("1".equals(result.getCode())) {
            // 登录成功，保存到Session
            SuperAdminDO admin = (SuperAdminDO) result.getData();
            session.setAttribute("adminId", admin.getId());
            session.setAttribute("adminName", admin.getName());
            session.setAttribute("adminType", "super");
        }
        return result;
    }

    /**
     * 退出登录
     */
    @GetMapping("/api/logout")
    @ResponseBody
    public ResultDO logout(HttpSession session) {
        session.invalidate();
        return new ResultDO() {{
            setCode("1");
            setMessage("退出成功");
        }};
    }

    /**
     * 获取当前登录信息
     */
    @GetMapping("/api/login/info")
    @ResponseBody
    public ResultDO getLoginInfo(HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("adminId");
        String adminName = (String) session.getAttribute("adminName");
        String adminType = (String) session.getAttribute("adminType");
        
        if (adminId != null) {
            return new ResultDO() {{
                setCode("1");
                setMessage("已登录");
                setData(new java.util.HashMap<String, Object>() {{
                    put("adminId", adminId);
                    put("adminName", adminName);
                    put("adminType", adminType);
                }});
            }};
        } else {
            return new ResultDO() {{
                setCode("0");
                setMessage("未登录");
            }};
        }
    }
}

