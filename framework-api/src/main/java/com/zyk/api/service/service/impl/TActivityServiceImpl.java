package com.zyk.api.service.service.impl;

import com.zyk.api.common.request.TActivityRequest;
import com.zyk.api.service.entity.TActivity;
import com.zyk.api.common.request.TActivityRequest;
import com.zyk.api.service.entity.SysDept;
import com.zyk.api.service.entity.TActivity;
import com.zyk.api.service.entity.TUser;
import com.zyk.api.service.mapper.TActivityMapper;
import com.zyk.api.service.service.ITActivityService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台活动表 服务实现类
 * </p>
 *
 * @author auto
 * @since 2019-11-29
 */
@Service
public class TActivityServiceImpl extends ServiceImpl<TActivityMapper, TActivity> implements ITActivityService {

	@Override
	public IPage<TActivity> pageSearch(TActivityRequest tActivityRequest) {
		QueryWrapper<TActivity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().select().and(status -> status.eq(TActivity::getStatus, 0))
				.and(delFlag -> delFlag.eq(TActivity::getDelFlag, 0));
		if (tActivityRequest.getType() == 0) {
			queryWrapper.lambda().select().and(endTime -> endTime.gt(TActivity::getEndTime, LocalDateTime.now()));
		}
		if (tActivityRequest.getType() == 1) {
			queryWrapper.lambda().select().and(endTime -> endTime.lt(TActivity::getEndTime, LocalDateTime.now()));
		}
		queryWrapper.lambda().orderByDesc(TActivity::getSort, TActivity::getCreateTime);
		IPage<TActivity> all = page(new Page<>(tActivityRequest.getCurrPage(), tActivityRequest.getPageSize()),
				queryWrapper);
		return all;
	}

}
