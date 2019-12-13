package info.ymjs.project.admin.user.mapper;


import info.ymjs.project.admin.user.domain.TUser;

import java.util.List;

/**
 * 平台用户Mapper接口
 * 
 * @author ruoyi
 * @date 2019-12-02
 */
public interface TUserMapper {
    /**
     * 查询平台用户
     * 
     * @param userId 平台用户ID
     * @return 平台用户
     */
    public TUser selectTUserById(Long userId);

    /**
     * 查询平台用户列表
     * 
     * @param tUser 平台用户
     * @return 平台用户集合
     */
    public List<TUser> selectTUserList(TUser tUser);

    /**
     * 新增平台用户
     * 
     * @param tUser 平台用户
     * @return 结果
     */
    public int insertTUser(TUser tUser);

    /**
     * 修改平台用户
     * 
     * @param tUser 平台用户
     * @return 结果
     */
    public int updateTUser(TUser tUser);

    /**
     * 删除平台用户
     * 
     * @param userId 平台用户ID
     * @return 结果
     */
    public int deleteTUserById(Long userId);

    /**
     * 批量删除平台用户
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTUserByIds(Long[] userIds);
}
