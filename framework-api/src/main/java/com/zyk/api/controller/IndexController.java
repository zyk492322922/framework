/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.zyk.api.controller;

import java.util.List;

import com.zyk.api.common.request.TActivityRequest;
import com.zyk.api.common.response.R;
import com.zyk.api.service.entity.TBanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zyk.api.common.request.TActivityRequest;
import com.zyk.api.common.response.R;
import com.zyk.api.service.entity.TBanner;
import com.zyk.api.service.service.ITBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dzs
 * @version UserController, v0.1 2019/11/27 9:52
 */
@Slf4j
@RestController
@Api(value = "首页接口", tags = "首页接口")
@RequestMapping("/index")
public class IndexController {
	@Autowired
	ITBannerService iTBannerService;

	@ApiOperation(value = "查询首页")
	@PostMapping("/select")
	public R<List<TBanner>> select(@RequestBody TActivityRequest tActivityRequest) {
		QueryWrapper<TBanner> queryWrapper = new QueryWrapper<TBanner>();
		queryWrapper.lambda().select().and(type -> type.eq(TBanner::getBannerType, 1))
				.and(status -> status.eq(TBanner::getStatus, 0)).and(delFlag -> delFlag.eq(TBanner::getDelFlag, 0))
				.orderByAsc(TBanner::getSort).orderByDesc(TBanner::getCreateTime);
		List<TBanner> result = iTBannerService.list(queryWrapper);
		return R.success(result);
	}

}
