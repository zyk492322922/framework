package info.ymjs.api.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import info.ymjs.api.common.request.TActivityRequest;
import info.ymjs.api.service.entity.TActivity;

/**
 * <p>
 * 平台活动表 服务类
 * </p>
 *
 * @author auto
 * @since 2019-11-29
 */
public interface ITActivityService extends IService<TActivity> {

	IPage<TActivity> pageSearch(TActivityRequest tActivityRequest);

}
