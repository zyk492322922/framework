package com.zyk.project.admin.sms.mapper;

import com.zyk.project.admin.sms.domain.TSmsConfig;

import java.util.List;

/**
 * 短信配置Mapper接口
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
public interface TSmsConfigMapper {
    /**
     * 查询短信配置
     * 
     * @param id 短信配置ID
     * @return 短信配置
     */
    public TSmsConfig selectTSmsConfigById(Long id);

    /**
     * 查询短信配置列表
     * 
     * @param tSmsConfig 短信配置
     * @return 短信配置集合
     */
    public List<TSmsConfig> selectTSmsConfigList(TSmsConfig tSmsConfig);

    /**
     * 新增短信配置
     * 
     * @param tSmsConfig 短信配置
     * @return 结果
     */
    public int insertTSmsConfig(TSmsConfig tSmsConfig);

    /**
     * 修改短信配置
     * 
     * @param tSmsConfig 短信配置
     * @return 结果
     */
    public int updateTSmsConfig(TSmsConfig tSmsConfig);

    /**
     * 删除短信配置
     * 
     * @param id 短信配置ID
     * @return 结果
     */
    public int deleteTSmsConfigById(Long id);

    /**
     * 批量删除短信配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTSmsConfigByIds(Long[] ids);
}
