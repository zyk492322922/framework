package info.ymjs.project.admin.sms.domain;

import info.ymjs.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import info.ymjs.framework.web.domain.BaseEntity;

/**
 * 短信配置对象 t_sms_config
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
@Data
public class TSmsConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 短信内容 */
    @Excel(name = "短信内容")
    private String smsContent;

    /** 短信类型 */
    @Excel(name = "短信类型")
    private String smsType;

    /** 发送状态 */
    @Excel(name = "发送状态")
    private Long sendStatus;

    /** 创建人 */
    private Long createUserId;




}
