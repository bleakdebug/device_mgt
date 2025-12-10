package cn.mgt.device.controller;

import cn.mgt.device.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 页面控制器 - 返回Web前端页面
 * 替代nginx提供Web页面的功能
 */
@Controller
public class PageController {

    /**
     * 首页 - 重定向到登录页
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    /**
     * 设备管理页面
     */
    @GetMapping("/device")
    public String device(HttpSession session) {
        if (!SessionUtil.isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "device";
    }

    /**
     * 组装线监控页面
     */
    @GetMapping("/assRecord")
    public String assRecord(HttpSession session) {
        if (!SessionUtil.isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "assRecord";
    }

    /**
     * 质量检测页面
     */
    @GetMapping("/check")
    public String check(HttpSession session) {
        if (!SessionUtil.isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "check";
    }

    /**
     * API文档页面（已禁用Swagger）
     */
    @GetMapping("/api-docs")
    public String apiDocs() {
        return "redirect:/home";
    }

    /**
     * 超级管理员登录页面
     */
    @GetMapping("/superLogin")
    public String superLogin() {
        return "superLogin";
    }

    /**
     * 主页（需要登录）
     */
    @GetMapping("/home")
    public String home(HttpSession session) {
        if (!SessionUtil.isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "home";
    }

    /**
     * 设备管理员管理页面（仅超级管理员）
     */
    @GetMapping("/deviceAdmin")
    public String deviceAdmin(HttpSession session) {
        if (!SessionUtil.isLoggedIn(session)) {
            return "redirect:/login";
        }
        if (!SessionUtil.isSuperAdmin(session)) {
            return "redirect:/home";
        }
        return "deviceAdmin";
    }
}

