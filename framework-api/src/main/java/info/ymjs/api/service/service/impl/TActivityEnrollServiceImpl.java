package info.ymjs.api.service.service.impl;

import info.ymjs.api.common.request.TActivityRequest;
import info.ymjs.api.service.entity.TActivity;
import info.ymjs.api.service.entity.TActivityEnroll;
import info.ymjs.api.service.mapper.TActivityEnrollMapper;
import info.ymjs.api.service.service.ITActivityEnrollService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台活动报名表 服务实现类
 * </p>
 *
 * @author auto
 * @since 2019-11-29
 */
@Service
public class TActivityEnrollServiceImpl extends ServiceImpl<TActivityEnrollMapper, TActivityEnroll>
		implements ITActivityEnrollService {

	@Override
	public IPage<TActivityEnroll> pageSearch(TActivityRequest tActivityRequest) {
		QueryWrapper<TActivityEnroll> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().select()
				.and(userId -> userId.eq(TActivityEnroll::getUserId, tActivityRequest.getUserId()))
				.and(delFlag -> delFlag.eq(TActivityEnroll::getDelFlag, 0));
		queryWrapper.lambda().orderByDesc(TActivityEnroll::getCreateTime);
		IPage<TActivityEnroll> all = page(new Page<>(tActivityRequest.getCurrPage(), tActivityRequest.getPageSize()),
				queryWrapper);
		return all;
	}

}
