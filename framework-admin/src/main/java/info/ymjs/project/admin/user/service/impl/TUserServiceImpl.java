package info.ymjs.project.admin.user.service.impl;

import java.util.List;

import info.ymjs.common.utils.DateUtils;
import info.ymjs.project.admin.user.domain.TUser;
import info.ymjs.project.admin.user.mapper.TUserMapper;
import info.ymjs.project.admin.user.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 平台用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-12-02
 */
@Service
public class TUserServiceImpl implements ITUserService {
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 查询平台用户
     * 
     * @param userId 平台用户ID
     * @return 平台用户
     */
    @Override
    public TUser selectTUserById(Long userId) {
        return tUserMapper.selectTUserById(userId);
    }

    /**
     * 查询平台用户列表
     * 
     * @param tUser 平台用户
     * @return 平台用户
     */
    @Override
    public List<TUser> selectTUserList(TUser tUser) {
        return tUserMapper.selectTUserList(tUser);
    }

    /**
     * 新增平台用户
     * 
     * @param tUser 平台用户
     * @return 结果
     */
    @Override
    public int insertTUser(TUser tUser) {
        tUser.setCreateTime(DateUtils.getNowDate());
        return tUserMapper.insertTUser(tUser);
    }

    /**
     * 修改平台用户
     * 
     * @param tUser 平台用户
     * @return 结果
     */
    @Override
    public int updateTUser(TUser tUser) {
        tUser.setUpdateTime(DateUtils.getNowDate());
        return tUserMapper.updateTUser(tUser);
    }

    /**
     * 批量删除平台用户
     * 
     * @param userIds 需要删除的平台用户ID
     * @return 结果
     */
    @Override
    public int deleteTUserByIds(Long[] userIds) {
        return tUserMapper.deleteTUserByIds(userIds);
    }

    /**
     * 删除平台用户信息
     * 
     * @param userId 平台用户ID
     * @return 结果
     */
    @Override
    public int deleteTUserById(Long userId) {
        return tUserMapper.deleteTUserById(userId);
    }
}
