package com.zyk.project.admin.article.mapper;

import com.zyk.project.admin.article.domain.Article;
import com.zyk.project.admin.article.domain.Article;
import java.util.List;

/**
 * 文章管理Mapper接口
 * 
 * @author framework
 * @date 2019-12-05
 */
public interface ArticleMapper {
    /**
     * 查询文章管理
     * 
     * @param id 文章管理ID
     * @return 文章管理
     */
    public Article selectArticleById(Long id);

    /**
     * 查询文章管理列表
     * 
     * @param article 文章管理
     * @return 文章管理集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增文章管理
     * 
     * @param article 文章管理
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改文章管理
     * 
     * @param article 文章管理
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 删除文章管理
     * 
     * @param id 文章管理ID
     * @return 结果
     */
    public int deleteArticleById(Long id);

    /**
     * 批量删除文章管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleByIds(Long[] ids);
}
