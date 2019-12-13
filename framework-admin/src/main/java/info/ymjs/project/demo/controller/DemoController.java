package info.ymjs.project.demo.controller;

import java.util.List;

import info.ymjs.project.demo.domain.Demo;
import info.ymjs.project.demo.service.IDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import info.ymjs.framework.aspectj.lang.annotation.Log;
import info.ymjs.framework.aspectj.lang.enums.BusinessType;
import info.ymjs.framework.web.controller.BaseController;
import info.ymjs.framework.web.page.TableDataInfo;
import info.ymjs.framework.web.domain.AjaxResult;
import info.ymjs.common.utils.poi.ExcelUtil;

/**
 * 测试 信息操作处理
 * 
 * @author framework
 * @date 2019-11-27
 */
@Api("demo示例")
@Controller
@RequestMapping("/project/demo")
public class DemoController extends BaseController {
    private String prefix = "project/demo";
	
	@Autowired
	private IDemoService demoService;

	@GetMapping()
	public String demo() {
	    return prefix + "/demo";
	}
	
	/**
	 * 查询测试列表
	 */
	@ApiOperation("列表")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Demo demo) {
		startPage();
        List<Demo> list = demoService.selectDemoList(demo);
		return getDataTable(list);

	}
	
	
	/**
	 * 导出测试列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Demo demo) {
    	List<Demo> list = demoService.selectDemoList(demo);
        ExcelUtil<Demo> util = new ExcelUtil<Demo>(Demo.class);
        return util.exportExcel(list, "demo");
    }
	
	/**
	 * 新增测试
	 */
	@GetMapping("/add")
	public String add() {
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存测试
	 */

	@ApiOperation("新增")
	@Log(title = "测试", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody Demo demo) {
		return toAjax(demoService.insertDemo(demo));
	}

	/**
	 * 修改测试
	 */

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
		Demo demo = demoService.selectDemoById(id);
		mmap.put("demo", demo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存测试
	 */
	@ApiOperation("编辑")
	@Log(title = "测试", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave( @RequestBody Demo demo) {
		return toAjax(demoService.updateDemo(demo));
	}
	
	/**
	 * 删除测试
	 */
	@ApiOperation("删除")
	@Log(title = "测试", businessType = BusinessType.DELETE)
	@PostMapping( "/remove/{ids}")
	@ResponseBody
	public AjaxResult remove(@PathVariable String[]  ids) {
		return toAjax(demoService.deleteDemoByIds(ids));
	}
	
}
