package com.zyk.project.admin.activity.mapper;

import com.zyk.project.admin.activity.domain.TActivity;

import java.util.List;

/**
 * 活动管理Mapper接口
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
public interface TActivityMapper {
    /**
     * 查询活动管理
     * 
     * @param id 活动管理ID
     * @return 活动管理
     */
    public TActivity selectTActivityById(Long id);

    /**
     * 查询活动管理列表
     * 
     * @param tActivity 活动管理
     * @return 活动管理集合
     */
    public List<TActivity> selectTActivityList(TActivity tActivity);

    /**
     * 新增活动管理
     * 
     * @param tActivity 活动管理
     * @return 结果
     */
    public int insertTActivity(TActivity tActivity);

    /**
     * 修改活动管理
     * 
     * @param tActivity 活动管理
     * @return 结果
     */
    public int updateTActivity(TActivity tActivity);

    /**
     * 删除活动管理
     * 
     * @param id 活动管理ID
     * @return 结果
     */
    public int deleteTActivityById(Long id);

    /**
     * 批量删除活动管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTActivityByIds(Long[] ids);
}
