package info.ymjs.project.admin.banner.service.impl;


import info.ymjs.common.utils.DateUtils;
import info.ymjs.project.admin.banner.domain.Banner;
import info.ymjs.project.admin.banner.mapper.BannerMapper;
import info.ymjs.project.admin.banner.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告banner信息Service业务层处理
 * 
 * @author framework
 * @date 2019-12-02
 */
@Service
public class BannerServiceImpl implements IBannerService {
    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 查询广告banner信息
     * 
     * @param id 广告banner信息ID
     * @return 广告banner信息
     */
    @Override
    public Banner selectBannerById(Long id) {
        return bannerMapper.selectBannerById(id);
    }

    /**
     * 查询广告banner信息列表
     * 
     * @param banner 广告banner信息
     * @return 广告banner信息
     */
    @Override
    public List<Banner> selectBannerList(Banner banner) {
        return bannerMapper.selectBannerList(banner);
    }

    /**
     * 新增广告banner信息
     * 
     * @param banner 广告banner信息
     * @return 结果
     */
    @Override
    public int insertBanner(Banner banner) {
        banner.setCreateTime(DateUtils.getNowDate());
        return bannerMapper.insertBanner(banner);
    }

    /**
     * 修改广告banner信息
     * 
     * @param banner 广告banner信息
     * @return 结果
     */
    @Override
    public int updateBanner(Banner banner) {
        banner.setUpdateTime(DateUtils.getNowDate());
        return bannerMapper.updateBanner(banner);
    }

    /**
     * 批量删除广告banner信息
     * 
     * @param ids 需要删除的广告banner信息ID
     * @return 结果
     */
    @Override
    public int deleteBannerByIds(Long[] ids) {
        return bannerMapper.deleteBannerByIds(ids);
    }

    /**
     * 删除广告banner信息信息
     * 
     * @param id 广告banner信息ID
     * @return 结果
     */
    @Override
    public int deleteBannerById(Long id) {
        return bannerMapper.deleteBannerById(id);
    }
}
