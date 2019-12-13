package info.ymjs.project.admin.user.controller;

import java.util.List;

import info.ymjs.project.admin.user.domain.TUser;
import info.ymjs.project.admin.user.service.ITUserService;
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
 * 平台用户Controller
 * 
 * @author ruoyi
 * @date 2019-12-02
 */
@RestController
@RequestMapping("/admin/user")
public class TUserController extends BaseController {
    @Autowired
    private ITUserService tUserService;

    /**
     * 查询平台用户列表
     */
    @PreAuthorize("@ss.hasPermi('admin:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUser tUser) {
        startPage();
        List<TUser> list = tUserService.selectTUserList(tUser);
        return getDataTable(list);
    }

    /**
     * 导出平台用户列表
     */
    @PreAuthorize("@ss.hasPermi('admin:user:export')")
    @Log(title = "平台用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TUser tUser) {
        List<TUser> list = tUserService.selectTUserList(tUser);
        ExcelUtil<TUser> util = new ExcelUtil<TUser>(TUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取平台用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId) {
        return AjaxResult.success(tUserService.selectTUserById(userId));
    }

    /**
     * 新增平台用户
     */
    @PreAuthorize("@ss.hasPermi('admin:user:add')")
    @Log(title = "平台用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUser tUser) {
        return toAjax(tUserService.insertTUser(tUser));
    }

    /**
     * 修改平台用户
     */
    @PreAuthorize("@ss.hasPermi('admin:user:edit')")
    @Log(title = "平台用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUser tUser) {
        return toAjax(tUserService.updateTUser(tUser));
    }

    /**
     * 删除平台用户
     */
    @PreAuthorize("@ss.hasPermi('admin:user:remove')")
    @Log(title = "平台用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(tUserService.deleteTUserByIds(userIds));
    }
}
