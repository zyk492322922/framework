package com.zyk.project.demo.service;


import com.zyk.project.demo.domain.Demo;
import com.zyk.project.demo.domain.Demo;

import java.util.List;

/**
 * 测试 服务层
 * 
 * @author framework
 * @date 2019-11-27
 */
public interface IDemoService {
	/**
     * 查询测试信息
     * 
     * @param id 测试ID
     * @return 测试信息
     */
	public Demo selectDemoById(Integer id);
	
	/**
     * 查询测试列表
     * 
     * @param demo 测试信息
     * @return 测试集合
     */
	public List<Demo> selectDemoList(Demo demo);
	
	/**
     * 新增测试
     * 
     * @param demo 测试信息
     * @return 结果
     */
	public int insertDemo(Demo demo);
	
	/**
     * 修改测试
     * 
     * @param demo 测试信息
     * @return 结果
     */
	public int updateDemo(Demo demo);
		
	/**
     * 删除测试信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDemoByIds(String[] ids);
	
}
