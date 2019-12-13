package info.ymjs.api.service.service.impl;

import info.ymjs.api.common.request.BaseRequest;
import info.ymjs.api.service.entity.TActivity;
import info.ymjs.api.service.entity.TArticle;
import info.ymjs.api.service.mapper.TArticleMapper;
import info.ymjs.api.service.service.ITArticleService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台文章表 服务实现类
 * </p>
 *
 * @author auto
 * @since 2019-11-29
 */
@Service
public class TArticleServiceImpl extends ServiceImpl<TArticleMapper, TArticle> implements ITArticleService {

	@Override
	public IPage<TArticle> pageSearch(BaseRequest baseRequest) {
		QueryWrapper<TArticle> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().select().and(type -> type.eq(TArticle::getType, 1))
				.and(status -> status.eq(TArticle::getStatus, 0)).and(delFlag -> delFlag.eq(TArticle::getDelFlag, 0));

		queryWrapper.lambda().orderByDesc(TArticle::getCreateTime);
		IPage<TArticle> all = page(new Page<>(baseRequest.getCurrPage(), baseRequest.getPageSize()), queryWrapper);
		return all;
	}

}
