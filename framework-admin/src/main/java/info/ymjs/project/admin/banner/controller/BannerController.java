package info.ymjs.project.admin.banner.controller;


import info.ymjs.common.utils.poi.ExcelUtil;
import info.ymjs.framework.aspectj.lang.annotation.Log;
import info.ymjs.framework.aspectj.lang.enums.BusinessType;
import info.ymjs.framework.web.controller.BaseController;
import info.ymjs.framework.web.domain.AjaxResult;
import info.ymjs.framework.web.page.TableDataInfo;
import info.ymjs.project.admin.banner.domain.Banner;
import info.ymjs.project.admin.banner.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广告banner信息Controller
 * 
 * @author framework
 * @date 2019-12-02
 */
@RestController
@RequestMapping("/admin/banner")
public class BannerController extends BaseController {
    @Autowired
    private IBannerService bannerService;

    /**
     * 查询广告banner信息列表
     */
    @PreAuthorize("@ss.hasPermi('admin:banner:list')")
    @GetMapping("/list")
    public TableDataInfo list(Banner banner) {
        startPage();
        List<Banner> list = bannerService.selectBannerList(banner);
        return getDataTable(list);
    }

    /**
     * 导出广告banner信息列表
     */
    @PreAuthorize("@ss.hasPermi('admin:banner:export')")
    @Log(title = "广告banner信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Banner banner) {
        List<Banner> list = bannerService.selectBannerList(banner);
        ExcelUtil<Banner> util = new ExcelUtil<Banner>(Banner.class);
        return util.exportExcel(list, "banner");
    }

    /**
     * 获取广告banner信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:banner:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(bannerService.selectBannerById(id));
    }

    /**
     * 新增广告banner信息
     */
    @PreAuthorize("@ss.hasPermi('admin:banner:add')")
    @Log(title = "广告banner信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Banner banner) {
        return toAjax(bannerService.insertBanner(banner));
    }

    /**
     * 修改广告banner信息
     */
    @PreAuthorize("@ss.hasPermi('admin:banner:edit')")
    @Log(title = "广告banner信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Banner banner) {
        return toAjax(bannerService.updateBanner(banner));
    }

    /**
     * 删除广告banner信息
     */
    @PreAuthorize("@ss.hasPermi('admin:banner:remove')")
    @Log(title = "广告banner信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(bannerService.deleteBannerByIds(ids));
    }
}
