/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.zyk.api.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zyk.api.common.request.TActivityRequest;
import com.zyk.api.common.response.R;
import com.zyk.api.common.response.entity.Activity;
import com.zyk.api.common.response.entity.ActivityData;
import com.zyk.api.service.entity.TActivity;
import com.zyk.api.service.entity.TActivityEnroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.zyk.api.common.request.TActivityRequest;
import com.zyk.api.common.response.R;
import com.zyk.api.common.response.entity.Activity;
import com.zyk.api.common.response.entity.ActivityData;
import com.zyk.api.service.entity.TActivity;
import com.zyk.api.service.entity.TActivityEnroll;
import com.zyk.api.service.service.ITActivityEnrollService;
import com.zyk.api.service.service.ITActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dzs
 * @version UserController, v0.1 2019/11/27 9:52
 */
@Slf4j
@RestController
@Api(value = "活动接口", tags = "活动接口")
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	ITActivityService iTActivityService;
	@Autowired
	ITActivityEnrollService iTActivityEnrollService;

	@ApiOperation(value = "查询活动")
	@PostMapping("/select")
	public R<IPage<TActivity>> select(@RequestBody TActivityRequest tActivityRequest) {
		IPage<TActivity> result = iTActivityService.pageSearch(tActivityRequest);
		return R.success(result);
	}

	@ApiOperation(value = "查询一个活动")
	@PostMapping("/selectone")
	public R<TActivity> selectone(@RequestBody TActivityRequest tActivityRequest) {
		TActivity result = iTActivityService.getById(tActivityRequest.getActivityId());
		return R.success(result);
	}

	@ApiOperation(value = "活动报名")
	@PostMapping("/enroll")
	public R enroll(@RequestBody TActivityRequest tActivityRequest) {
		QueryWrapper<TActivityEnroll> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().select()
				.and(activityId -> activityId.eq(TActivityEnroll::getActivityId, tActivityRequest.getActivityId()))
				.and(userId -> userId.eq(TActivityEnroll::getUserId, tActivityRequest.getUserId()));
		int count = iTActivityEnrollService.count(queryWrapper);
		if (count != 0) {
			return R.fail("不可重复报名");
		}
		TActivityEnroll entity = new TActivityEnroll();
		entity.setActivityId(tActivityRequest.getActivityId());
		entity.setUserId(tActivityRequest.getUserId());
		entity.setCreateTime(LocalDateTime.now());
		boolean bo = iTActivityEnrollService.save(entity);
		if (!bo) {
			return R.fail("报名失败");
		}
		return R.success("恭喜您，报名成功。");
	}

	@ApiOperation(value = "活动报名列表")
	@PostMapping("/enrolllist")
	public R<ActivityData> enrolllist(@RequestBody TActivityRequest tActivityRequest) {
		IPage<TActivityEnroll> result = iTActivityEnrollService.pageSearch(tActivityRequest);
		List<TActivityEnroll> list = result.getRecords();
		List<Integer> idList = new ArrayList<Integer>();
		for (TActivityEnroll tActivityEnroll : list) {
			idList.add(tActivityEnroll.getActivityId());
		}
		Collection<TActivity> alist = iTActivityService.listByIds(idList);
		List<Activity> rt = new ArrayList<Activity>();
		for (TActivityEnroll tActivityEnroll : list) {
			for (TActivity tActivity : alist) {
				if (tActivityEnroll.getActivityId() == tActivity.getId()) {
					Activity at = new Activity();
					at.setId(tActivity.getId());
					at.setTitle(tActivity.getTitle());
					at.setBeginTime(tActivity.getBeginTime());
					at.setEndTime(tActivity.getEndTime());
					at.setCreateTime(tActivityEnroll.getCreateTime());
					at.setType(belongCalendar(tActivity.getBeginTime(), tActivity.getEndTime()));
					rt.add(at);
				}
			}
		}
		ActivityData ad = new ActivityData();
		ad.setSize(result.getSize());
		ad.setTotal(result.getTotal());
		ad.setActivity(rt);
		return R.success(ad);
	}

	/**
	 * 判断time是否在from，to之内
	 *
	 * @param begin 开始日期
	 * @param end   结束日期
	 * @return 0未开始1进行中2已完成
	 */
	public static int belongCalendar(LocalDateTime begin, LocalDateTime end) {
		LocalDateTime now = LocalDateTime.now();
		if (now.isAfter(end) || now.isBefore(begin)) {
			return 1;
		} else if (now.isBefore(end)) {
			return 2;
		} else {
			return 0;
		}
	}

}
