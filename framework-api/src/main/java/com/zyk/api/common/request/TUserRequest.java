package com.zyk.api.common.request;


import java.time.LocalDateTime;

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
public class TUserRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "userId")
    private Integer userId;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 用户是否锁定,0未锁定,1锁定
     */
    @ApiModelProperty(value = "用户是否锁定,0未锁定,1锁定")
    private Integer status;

    /**
     * 性别:0未知,1男,2女
     */
    @ApiModelProperty(value = " 性别:0未知,1男,2女")
    private Integer sex;

    /**
     * app头像的url
     */
    @ApiModelProperty(value = "app头像的url")
    private String iconUrl;

    /**
     * 注册ip
     */
    @ApiModelProperty(value = "注册ip")
    private String regIp;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime regTime;

    /**
     *创建时间 
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 是否删除0未删除1已删除
     */
    @ApiModelProperty(value = " 是否删除0未删除1已删除")
    private Integer delFlag;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 上次登录时间
     */
    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 短信验证码
     */
    @ApiModelProperty(value = "短信验证码")
    private String code;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
    @ApiModelProperty(value = "sms用途(上传中文): 注册,忘记密码,修改手机号")
    private String smsType;
}
