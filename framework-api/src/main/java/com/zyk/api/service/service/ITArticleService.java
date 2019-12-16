package com.zyk.api.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.zyk.api.common.request.BaseRequest;
import com.zyk.api.service.entity.TArticle;
import com.zyk.api.common.request.BaseRequest;
import com.zyk.api.service.entity.TArticle;

/**
 * <p>
 * 平台文章表 服务类
 * </p>
 *
 * @author auto
 * @since 2019-11-29
 */
public interface ITArticleService extends IService<TArticle> {

	IPage<TArticle> pageSearch(BaseRequest baseRequest);

}
