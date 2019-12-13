package info.ymjs.api.common.response.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.List;
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
public class ActivityData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "报名集合")
    private List< Activity>  activity;

    @ApiModelProperty(value = "总行数")
    private long total;
    @ApiModelProperty(value = "总页数")
    private long size;
}
