package com.zyk.project.monitor.controller;

import java.util.List;

import com.zyk.framework.aspectj.lang.annotation.Log;
import com.zyk.framework.aspectj.lang.enums.BusinessType;
import com.zyk.framework.web.controller.BaseController;
import com.zyk.framework.web.domain.AjaxResult;
import com.zyk.framework.web.page.TableDataInfo;
import com.zyk.project.monitor.service.ISysLogininforService;
import com.zyk.common.utils.poi.ExcelUtil;
import com.zyk.framework.aspectj.lang.annotation.Log;
import com.zyk.framework.aspectj.lang.enums.BusinessType;
import com.zyk.framework.web.controller.BaseController;
import com.zyk.framework.web.domain.AjaxResult;
import com.zyk.framework.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zyk.common.utils.poi.ExcelUtil;
import com.zyk.framework.aspectj.lang.annotation.Log;
import com.zyk.framework.aspectj.lang.enums.BusinessType;
import com.zyk.framework.web.controller.BaseController;
import com.zyk.framework.web.domain.AjaxResult;
import com.zyk.framework.web.page.TableDataInfo;
import com.zyk.project.monitor.domain.SysLogininfor;
import com.zyk.project.monitor.service.ISysLogininforService;

/**
 * 系统访问记录
 * 
 * @author framework
 */
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController
{
    @Autowired
    private ISysLogininforService logininforService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininfor logininfor)
    {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    @Log(title = "登陆日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
    @GetMapping("/export")
    public AjaxResult export(SysLogininfor logininfor)
    {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        return util.exportExcel(list, "登陆日志");
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        logininforService.cleanLogininfor();
        return AjaxResult.success();
    }
}
