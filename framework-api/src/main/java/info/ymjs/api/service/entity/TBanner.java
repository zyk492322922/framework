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
 * 广告banner信息表
 * </p>
 *
 * @author auto
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_banner")
public class TBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 广告id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告位,1首页banner,2banner下面广告位
     */
    private Integer bannerType;

    /**
     * 广告名称
     */
    private String bannerName;

    /**
     * 广告图片
     */
    private String url;

    /**
     * 状态,0启用,1禁用
     */
    private Integer status;

    /**
     * 跳转链接
     */
    private String jumpUrl;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 广告描述
     */
    private String details;

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
     * 是否可跳转,0是,1否
     */
    private Integer type;


}
