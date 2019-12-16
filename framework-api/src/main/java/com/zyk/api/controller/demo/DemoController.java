/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.zyk.api.controller.demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyk.api.common.response.R;
import com.zyk.api.service.entity.SysDept;
import com.zyk.api.common.response.R;
import com.zyk.api.service.entity.SysDept;
import com.zyk.api.service.service.ISysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangqingqing
 * @version DemoController, v0.1 2019/11/27 9:52
 */
@Slf4j
@RestController
@Api(value = "接口示例",tags = "接口示例")
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    ISysDeptService iSysDeptService;

    @ApiOperation(value = "分页查询")
    @GetMapping("/pageSearch")
    public R<List<SysDept>> pageSearch(){
        IPage<SysDept> result = iSysDeptService.pageSearch(2,3);
        return R.data(result.getRecords());
    }
}
