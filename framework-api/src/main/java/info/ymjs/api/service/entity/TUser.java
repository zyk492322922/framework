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
 * 平台用户表
 * </p>
 *
 * @author auto
 * @since 2019-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户是否锁定,0未锁定,1锁定
     */
    private Integer status;

    /**
     * 性别:0未知,1男,2女
     */
    private Integer sex;

    /**
     * app头像的url
     */
    private String iconUrl;

    /**
     * 注册ip
     */
    private String regIp;

    /**
     * 注册时间
     */
    private LocalDateTime regTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否删除0未删除1已删除
     */
    private Integer delFlag;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 姓名
     */
    private String name;


}
