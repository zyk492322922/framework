package info.ymjs.project.admin.activity.domain;

import info.ymjs.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import info.ymjs.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 活动管理对象 t_activity
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
@Data
public class TActivity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 活动id */
    private Long id;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String title;

    /** 活动开始时间 */
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 活动结束时间 */
    @Excel(name = "活动结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date offTime;

    /** 地点 */
    private String place;

    /** 费用 */
    private String cost;

    /** 主办方 */
    private String sponsor;

    /** 活动图片 */
    private String url;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 状态,0启动,1近用 */
    @Excel(name = "状态,0启动,1近用")
    private Integer status;

    /** 活动须知 */
    private String notice;

    /** 是否删除0未删除1已删除 */
    private Integer delFlag;

    /** 创建人id */
    private Long createUser;

    /** 活动详情 */
    private String details;




}
