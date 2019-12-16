package com.zyk.project.demo.service.impl;


import com.zyk.project.demo.domain.Demo;
import com.zyk.project.demo.mapper.DemoMapper;
import com.zyk.common.core.text.Convert;
import com.zyk.project.demo.domain.Demo;
import com.zyk.project.demo.mapper.DemoMapper;
import com.zyk.project.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试 服务层实现
 * 
 * @author framework
 * @date 2019-11-27
 */
@Service
public class DemoServiceImpl implements IDemoService {
	@Autowired
	private DemoMapper demoMapper;

	/**
     * 查询测试信息
     * 
     * @param id 测试ID
     * @return 测试信息
     */
    @Override
	public Demo selectDemoById(Integer id)
	{
	    return demoMapper.selectDemoById(id);
	}
	
	/**
     * 查询测试列表
     * 
     * @param demo 测试信息
     * @return 测试集合
     */
	@Override
	public List<Demo> selectDemoList(Demo demo)
	{
	    return demoMapper.selectDemoList(demo);
	}
	
    /**
     * 新增测试
     * 
     * @param demo 测试信息
     * @return 结果
     */
	@Override
	public int insertDemo(Demo demo)
	{
	    return demoMapper.insertDemo(demo);
	}
	
	/**
     * 修改测试
     * 
     * @param demo 测试信息
     * @return 结果
     */
	@Override
	public int updateDemo(Demo demo)
	{
	    return demoMapper.updateDemo(demo);
	}

	/**
     * 删除测试对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDemoByIds(String[] ids)
	{
		return demoMapper.deleteDemoByIds(ids);
	}
	
}
