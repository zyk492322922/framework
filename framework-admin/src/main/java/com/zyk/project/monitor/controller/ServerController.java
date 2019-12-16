package com.zyk.project.monitor.controller;

import com.zyk.framework.web.controller.BaseController;
import com.zyk.framework.web.domain.AjaxResult;
import com.zyk.framework.web.domain.Server;
import com.zyk.framework.web.controller.BaseController;
import com.zyk.framework.web.domain.AjaxResult;
import com.zyk.framework.web.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zyk.framework.web.controller.BaseController;
import com.zyk.framework.web.domain.AjaxResult;
import com.zyk.framework.web.domain.Server;

/**
 * 服务器监控
 * 
 * @author framework
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController extends BaseController
{
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
