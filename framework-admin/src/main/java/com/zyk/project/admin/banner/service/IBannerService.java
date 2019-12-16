package com.zyk.project.admin.banner.service;


import com.zyk.project.admin.banner.domain.Banner;

import java.util.List;

/**
 * 广告banner信息Service接口
 * 
 * @author framework
 * @date 2019-12-02
 */
public interface IBannerService  {
    /**
     * 查询广告banner信息
     * 
     * @param id 广告banner信息ID
     * @return 广告banner信息
     */
    public Banner selectBannerById(Long id);

    /**
     * 查询广告banner信息列表
     * 
     * @param banner 广告banner信息
     * @return 广告banner信息集合
     */
    public List<Banner> selectBannerList(Banner banner);

    /**
     * 新增广告banner信息
     * 
     * @param banner 广告banner信息
     * @return 结果
     */
    public int insertBanner(Banner banner);

    /**
     * 修改广告banner信息
     * 
     * @param banner 广告banner信息
     * @return 结果
     */
    public int updateBanner(Banner banner);

    /**
     * 批量删除广告banner信息
     * 
     * @param ids 需要删除的广告banner信息ID
     * @return 结果
     */
    public int deleteBannerByIds(Long[] ids);

    /**
     * 删除广告banner信息信息
     * 
     * @param id 广告banner信息ID
     * @return 结果
     */
    public int deleteBannerById(Long id);
}
