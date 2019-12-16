package com.zyk.api.common.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 平台用户表
 * </p>
 *
 * @author dzs
 * @since 2019-11-29
 */
@Data
public class TActivityRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "查询活动0为最新活动1为往期活动2为都有")
    private Integer type;
    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "用户Id")
    private Integer userId;

  

}
