package info.ymjs.project.demo.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import info.ymjs.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 测试表 sys_demo
 * 
 * @author framework
 * @date 2019-11-27
 */
@Data
public class Demo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 姓名 */
	private String demoName;
	/** 年龄 */
	private Integer demoAge;


}
