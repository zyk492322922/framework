package info.ymjs.project.admin.activity.controller;

import java.util.List;

import info.ymjs.project.admin.activity.domain.TActivity;
import info.ymjs.project.admin.activity.service.ITActivityService;
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
import info.ymjs.framework.aspectj.lang.annotation.Log;
import info.ymjs.framework.aspectj.lang.enums.BusinessType;
import info.ymjs.framework.web.controller.BaseController;
import info.ymjs.framework.web.domain.AjaxResult;
import info.ymjs.framework.web.page.TableDataInfo;
import info.ymjs.common.utils.poi.ExcelUtil;
/**
 * 活动管理Controller
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
@RestController
@RequestMapping("/admin/activity")
public class TActivityController extends BaseController {
    @Autowired
    private ITActivityService tActivityService;

    /**
     * 查询活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(TActivity tActivity) {
        startPage();
        List<TActivity> list = tActivityService.selectTActivityList(tActivity);
        return getDataTable(list);
    }

    /**
     * 导出活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:activity:export')")
    @Log(title = "活动管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TActivity tActivity) {
        List<TActivity> list = tActivityService.selectTActivityList(tActivity);
        ExcelUtil<TActivity> util = new ExcelUtil<TActivity>(TActivity.class);
        return util.exportExcel(list, "activity");
    }

    /**
     * 获取活动管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tActivityService.selectTActivityById(id));
    }

    /**
     * 新增活动管理
     */
    @PreAuthorize("@ss.hasPermi('admin:activity:add')")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TActivity tActivity) {
        return toAjax(tActivityService.insertTActivity(tActivity));
    }

    /**
     * 修改活动管理
     */
    @PreAuthorize("@ss.hasPermi('admin:activity:edit')")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TActivity tActivity) {
        return toAjax(tActivityService.updateTActivity(tActivity));
    }

    /**
     * 删除活动管理
     */
    @PreAuthorize("@ss.hasPermi('admin:activity:remove')")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tActivityService.deleteTActivityByIds(ids));
    }
}
