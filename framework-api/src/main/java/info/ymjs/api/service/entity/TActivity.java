package info.ymjs.api.service.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 平台活动表
 * </p>
 *
 * @author auto
 * @since 2019-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_activity")
public class TActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 活动开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 地点
     */
    private String place;

    /**
     * 费用
     */
    private String cost;

    /**
     * 主办方
     */
    private String sponsor;

    /**
     * 活动图片
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态,0启动,1近用
     */
    private Integer status;

    /**
     * 活动须知
     */
    private String notice;

    /**
     * 是否删除0未删除1已删除
     */
    private Integer delFlag;

    /**
     * 创建人id
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 活动详情
     */
    private String details;


}
