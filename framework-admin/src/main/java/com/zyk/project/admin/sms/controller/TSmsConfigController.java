package com.zyk.project.admin.sms.controller;

import java.util.List;

import com.zyk.framework.aspectj.lang.annotation.Log;
import com.zyk.framework.aspectj.lang.enums.BusinessType;
import com.zyk.framework.web.controller.BaseController;
import com.zyk.framework.web.domain.AjaxResult;
import com.zyk.framework.web.page.TableDataInfo;
import com.zyk.project.admin.sms.domain.TSmsConfig;
import com.zyk.project.admin.sms.domain.TSmsConfig;
import com.zyk.project.admin.sms.service.ITSmsConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zyk.framework.aspectj.lang.annotation.Log;
import com.zyk.framework.aspectj.lang.enums.BusinessType;
import com.zyk.framework.web.controller.BaseController;
import com.zyk.framework.web.domain.AjaxResult;
import com.zyk.framework.web.page.TableDataInfo;
import com.zyk.common.utils.poi.ExcelUtil;
/**
 * 短信配置Controller
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
@RestController
@RequestMapping("/admin/config")
public class TSmsConfigController extends BaseController {
    @Autowired
    private ITSmsConfigService tSmsConfigService;

    /**
     * 查询短信配置列表
     */
    @PreAuthorize("@ss.hasPermi('admin:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSmsConfig tSmsConfig) {
        startPage();
        List<TSmsConfig> list = tSmsConfigService.selectTSmsConfigList(tSmsConfig);
        return getDataTable(list);
    }

    /**
     * 导出短信配置列表
     */
    @PreAuthorize("@ss.hasPermi('admin:config:export')")
    @Log(title = "短信配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSmsConfig tSmsConfig) {
        List<TSmsConfig> list = tSmsConfigService.selectTSmsConfigList(tSmsConfig);
        ExcelUtil<TSmsConfig> util = new ExcelUtil<TSmsConfig>(TSmsConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 获取短信配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tSmsConfigService.selectTSmsConfigById(id));
    }

    /**
     * 新增短信配置
     */
    @PreAuthorize("@ss.hasPermi('admin:config:add')")
    @Log(title = "短信配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSmsConfig tSmsConfig) {
        return toAjax(tSmsConfigService.insertTSmsConfig(tSmsConfig));
    }

    /**
     * 修改短信配置
     */
    @PreAuthorize("@ss.hasPermi('admin:config:edit')")
    @Log(title = "短信配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSmsConfig tSmsConfig) {
        return toAjax(tSmsConfigService.updateTSmsConfig(tSmsConfig));
    }

    /**
     * 删除短信配置
     */
    @PreAuthorize("@ss.hasPermi('admin:config:remove')")
    @Log(title = "短信配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tSmsConfigService.deleteTSmsConfigByIds(ids));
    }
}
