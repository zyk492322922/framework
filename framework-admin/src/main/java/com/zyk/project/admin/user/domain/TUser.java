package com.zyk.project.admin.user.domain;

import com.zyk.framework.aspectj.lang.annotation.Excel;
import com.zyk.framework.web.domain.BaseEntity;
import com.zyk.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import com.zyk.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 平台用户对象 t_user
 * 
 * @author ruoyi
 * @date 2019-12-02
 */
@Data
public class TUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 邮箱 */
    private String email;

    /** 密码 */
    private String password;

    /** 用户是否锁定,0未锁定,1锁定 */
    @Excel(name = "用户是否锁定,0未锁定,1锁定")
    private Integer status;

    /** 性别:0未知,1男,2女 */
    @Excel(name = "性别:0未知,1男,2女")
    private Integer sex;

    /** app头像的url */
    private String iconUrl;

    /** 注册ip */
    private String regIp;

    /** 注册时间 */
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date regTime;

    /** 是否删除0未删除1已删除 */
    private Integer delFlag;

    /** 上次登录时间 */
    private Date lastLoginTime;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;




}
