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
 * 平台文章表
 * </p>
 *
 * @author auto
 * @since 2019-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_article")
public class TArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 封面图图片
     */
    private String url;

    /**
     * 状态,0启动,1近用
     */
    private Integer status;

    /**
     * 文章标题
     */
    private String title;

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
     * 文章详情
     */
    private String details;

    /**
     * 文章类型：1沂蒙精神2其他
     */
    private Integer type;
}
