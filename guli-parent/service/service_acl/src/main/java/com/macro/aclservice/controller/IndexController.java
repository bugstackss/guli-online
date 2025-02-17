package com.macro.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.macro.aclservice.service.IndexService;
import com.macro.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public R info() {
        // 获取当前登录用户用户名
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final Map<String, Object> userInfo = indexService.getUserInfo(username);
        return R.ok().data(userInfo);
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @GetMapping("menu")
    public R getMenu() {
        // 获取当前登录用户用户名
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final List<JSONObject> permissionList = indexService.getMenu(username);
        return R.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }

}
