package info.ymjs.api.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import info.ymjs.api.common.request.BaseRequest;
import info.ymjs.api.service.entity.TArticle;

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
