package com.zyk.api.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyk.api.service.entity.SysDept;
import com.zyk.api.service.entity.SysDept;
import com.zyk.api.service.mapper.SysDeptMapper;
import com.zyk.api.service.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author auto
 * @since 2019-11-27
 */
@Slf4j
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

	@Override
	public IPage<SysDept> pageSearch(int current, int size) {
		QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().orderByDesc(SysDept::getDeptId);
		log.info("current:" + current + ";size:" + size);
		IPage<SysDept> all = page(new Page<>(current, size), queryWrapper);
		return all;
	}
}
