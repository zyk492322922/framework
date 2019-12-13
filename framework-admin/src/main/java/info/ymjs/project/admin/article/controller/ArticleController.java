package info.ymjs.project.admin.article.controller;

import java.util.List;

import info.ymjs.project.admin.article.domain.Article;
import info.ymjs.project.admin.article.service.IArticleService;
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
 * 文章管理Controller
 * 
 * @author framework
 * @date 2019-12-05
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleController extends BaseController {
    @Autowired
    private IArticleService articleService;

    /**
     * 查询文章管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(Article article) {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }

    /**
     * 导出文章管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:article:export')")
    @Log(title = "文章管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Article article) {
        List<Article> list = articleService.selectArticleList(article);
        ExcelUtil<Article> util = new ExcelUtil<Article>(Article.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 获取文章管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(articleService.selectArticleById(id));
    }

    /**
     * 新增文章管理
     */
    @PreAuthorize("@ss.hasPermi('admin:article:add')")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Article article) {
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改文章管理
     */
    @PreAuthorize("@ss.hasPermi('admin:article:edit')")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Article article) {
        return toAjax(articleService.updateArticle(article));
    }

    /**
     * 删除文章管理
     */
    @PreAuthorize("@ss.hasPermi('admin:article:remove')")
    @Log(title = "文章管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(articleService.deleteArticleByIds(ids));
    }
}
