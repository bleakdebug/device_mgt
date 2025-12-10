package cn.mgt.device.util;

import javax.servlet.http.HttpSession;

/**
 * Session工具类
 */
public class SessionUtil {
    
    /**
     * 检查是否已登录
     */
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("adminId") != null;
    }
    
    /**
     * 获取当前登录的管理员ID
     */
    public static Integer getAdminId(HttpSession session) {
        return (Integer) session.getAttribute("adminId");
    }
    
    /**
     * 获取当前登录的管理员类型
     * @return "super" 超级管理员, "device" 设备管理员, null 未登录
     */
    public static String getAdminType(HttpSession session) {
        return (String) session.getAttribute("adminType");
    }
    
    /**
     * 检查是否是超级管理员
     */
    public static boolean isSuperAdmin(HttpSession session) {
        return "super".equals(getAdminType(session));
    }
    
    /**
     * 检查是否是设备管理员
     */
    public static boolean isDeviceAdmin(HttpSession session) {
        return "device".equals(getAdminType(session));
    }
}

