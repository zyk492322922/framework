package info.ymjs.project.admin.banner.domain;


import info.ymjs.framework.aspectj.lang.annotation.Excel;
import info.ymjs.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 广告banner信息对象 t_banner
 * 
 * @author framework
 * @date 2019-12-02
 */
@Data
public class Banner extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 广告id */
    private Long id;

    /** 广告位,1首页banner,2banner下面广告位 */
    @Excel(name = "广告位,1首页banner,2banner下面广告位")
    private Integer bannerType;

    /** 广告名称 */
    @Excel(name = "广告名称")
    private String bannerName;

    /** 广告图片 */
    @Excel(name = "广告图片")
    private String url;

    /** 状态,0启用,1禁用 */
    @Excel(name = "状态,0启用,1禁用")
    private Integer status;

    /** 是否可跳转,0是,1否 */
    @Excel(name = "是否可跳转,0是,1否")
    private Integer type;

    /** 跳转链接 */
    @Excel(name = "跳转链接")
    private String jumpUrl;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 广告描述 */
    @Excel(name = "广告描述")
    private String details;

    /** 是否删除0未删除1已删除 */
    private Integer delFlag;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createUser;

    /** fabu时间 */
    @Excel(name = "广告时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;
    /** 广告开始时间 */
    @Excel(name = "广告开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 广告结束时间 */
    @Excel(name = "广告结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date offTime;


}
