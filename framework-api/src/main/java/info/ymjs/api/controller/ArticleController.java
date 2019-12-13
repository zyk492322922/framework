/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package info.ymjs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import info.ymjs.api.common.request.BaseRequest;
import info.ymjs.api.common.request.TActivityRequest;
import info.ymjs.api.common.response.R;
import info.ymjs.api.service.entity.TArticle;
import info.ymjs.api.service.service.ITArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dzs
 * @version UserController, v0.1 2019/11/27 9:52
 */
@Slf4j
@RestController
@Api(value = "文章接口", tags = "文章接口")
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	ITArticleService iTArticleService;

	@ApiOperation(value = "查询文章")
	@PostMapping("/select")
	public R<IPage<TArticle>> select(@RequestBody BaseRequest baseRequest) {
		IPage<TArticle> result = iTArticleService.pageSearch(baseRequest);
		return R.success(result);
	}

	@ApiOperation(value = "查询单个文章")
	@PostMapping("/selectone")
	public R<TArticle> selectone(@RequestBody TActivityRequest baseRequest) {
		TArticle result = iTArticleService.getById(baseRequest.getActivityId());
		return R.success(result);
	}

}
