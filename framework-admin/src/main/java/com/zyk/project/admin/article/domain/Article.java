package com.zyk.project.admin.article.domain;

import com.zyk.framework.aspectj.lang.annotation.Excel;
import com.zyk.framework.web.domain.BaseEntity;
import com.zyk.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import com.zyk.framework.web.domain.BaseEntity;

/**
 * 文章管理对象 t_article
 * 
 * @author framework
 * @date 2019-12-05
 */
@Data
public class Article extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 文章id */
    private Long id;

    /** 封面图图片 */
    private String url;

    /** 文章类型：1沂蒙精神2其他 */
    @Excel(name = "文章类型：1沂蒙精神2其他")
    private Integer type;

    /** 状态,0启动,1近用 */
    @Excel(name = "状态,0启动,1近用")
    private Integer status;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String title;

    /** 是否删除0未删除1已删除 */
    private Integer delFlag;

    /** 创建人id */
    private Long createUser;

    /** 文章详情 */
    @Excel(name = "文章详情")
    private String details;




}
