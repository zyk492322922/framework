package com.zyk.api.common.response.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Integer id;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "活动标题")
    private String title;

    /**
     * 活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间")
    private LocalDateTime beginTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty(value = "活动结束时间")
    private LocalDateTime endTime;

    /**
     * 地点
     */
    @ApiModelProperty(value = "0未开始1进行中2已完成")
    private int type;

  
    /**
     * 状态,0启动,1近用
     */
    @ApiModelProperty(value = "状态,0启动,1近用")
    private Boolean status;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;



}
