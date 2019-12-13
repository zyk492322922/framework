package info.ymjs.project.monitor.controller;

import info.ymjs.framework.web.controller.BaseController;
import info.ymjs.framework.web.domain.AjaxResult;
import info.ymjs.framework.web.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import info.ymjs.framework.web.controller.BaseController;
import info.ymjs.framework.web.domain.AjaxResult;
import info.ymjs.framework.web.domain.Server;

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
