package info.ymjs.api.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import info.ymjs.api.service.entity.SysDept;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author auto
 * @since 2019-11-27
 */
public interface ISysDeptService extends IService<SysDept> {

	/**
	 * 分页查询
	 * 
	 * @param current
	 * @param size
	 * @return
	 */
	IPage<SysDept> pageSearch(int current, int size);
}
