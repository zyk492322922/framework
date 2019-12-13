package info.ymjs.project.admin.sms.service.impl;

import java.util.List;

import info.ymjs.common.utils.DateUtils;
import info.ymjs.project.admin.sms.domain.TSmsConfig;
import info.ymjs.project.admin.sms.mapper.TSmsConfigMapper;
import info.ymjs.project.admin.sms.service.ITSmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
@Service
public class TSmsConfigServiceImpl implements ITSmsConfigService {
    @Autowired
    private TSmsConfigMapper tSmsConfigMapper;

    /**
     * 查询短信配置
     * 
     * @param id 短信配置ID
     * @return 短信配置
     */
    @Override
    public TSmsConfig selectTSmsConfigById(Long id) {
        return tSmsConfigMapper.selectTSmsConfigById(id);
    }

    /**
     * 查询短信配置列表
     * 
     * @param tSmsConfig 短信配置
     * @return 短信配置
     */
    @Override
    public List<TSmsConfig> selectTSmsConfigList(TSmsConfig tSmsConfig) {
        return tSmsConfigMapper.selectTSmsConfigList(tSmsConfig);
    }

    /**
     * 新增短信配置
     * 
     * @param tSmsConfig 短信配置
     * @return 结果
     */
    @Override
    public int insertTSmsConfig(TSmsConfig tSmsConfig) {
        tSmsConfig.setCreateTime(DateUtils.getNowDate());
        return tSmsConfigMapper.insertTSmsConfig(tSmsConfig);
    }

    /**
     * 修改短信配置
     * 
     * @param tSmsConfig 短信配置
     * @return 结果
     */
    @Override
    public int updateTSmsConfig(TSmsConfig tSmsConfig) {
        return tSmsConfigMapper.updateTSmsConfig(tSmsConfig);
    }

    /**
     * 批量删除短信配置
     * 
     * @param ids 需要删除的短信配置ID
     * @return 结果
     */
    @Override
    public int deleteTSmsConfigByIds(Long[] ids) {
        return tSmsConfigMapper.deleteTSmsConfigByIds(ids);
    }

    /**
     * 删除短信配置信息
     * 
     * @param id 短信配置ID
     * @return 结果
     */
    @Override
    public int deleteTSmsConfigById(Long id) {
        return tSmsConfigMapper.deleteTSmsConfigById(id);
    }
}
