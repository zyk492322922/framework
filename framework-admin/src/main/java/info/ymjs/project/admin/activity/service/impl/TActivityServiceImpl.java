package info.ymjs.project.admin.activity.service.impl;

import java.util.List;

import info.ymjs.common.utils.DateUtils;
import info.ymjs.project.admin.activity.domain.TActivity;
import info.ymjs.project.admin.activity.mapper.TActivityMapper;
import info.ymjs.project.admin.activity.service.ITActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
@Service
public class TActivityServiceImpl implements ITActivityService {
    @Autowired
    private TActivityMapper tActivityMapper;

    /**
     * 查询活动管理
     * 
     * @param id 活动管理ID
     * @return 活动管理
     */
    @Override
    public TActivity selectTActivityById(Long id) {
        return tActivityMapper.selectTActivityById(id);
    }

    /**
     * 查询活动管理列表
     * 
     * @param tActivity 活动管理
     * @return 活动管理
     */
    @Override
    public List<TActivity> selectTActivityList(TActivity tActivity) {
        return tActivityMapper.selectTActivityList(tActivity);
    }

    /**
     * 新增活动管理
     * 
     * @param tActivity 活动管理
     * @return 结果
     */
    @Override
    public int insertTActivity(TActivity tActivity) {
        tActivity.setCreateTime(DateUtils.getNowDate());
        return tActivityMapper.insertTActivity(tActivity);
    }

    /**
     * 修改活动管理
     * 
     * @param tActivity 活动管理
     * @return 结果
     */
    @Override
    public int updateTActivity(TActivity tActivity) {
        tActivity.setUpdateTime(DateUtils.getNowDate());
        return tActivityMapper.updateTActivity(tActivity);
    }

    /**
     * 批量删除活动管理
     * 
     * @param ids 需要删除的活动管理ID
     * @return 结果
     */
    @Override
    public int deleteTActivityByIds(Long[] ids) {
        return tActivityMapper.deleteTActivityByIds(ids);
    }

    /**
     * 删除活动管理信息
     * 
     * @param id 活动管理ID
     * @return 结果
     */
    @Override
    public int deleteTActivityById(Long id) {
        return tActivityMapper.deleteTActivityById(id);
    }
}
