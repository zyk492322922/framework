package com.zyk.api.service.service.impl;

import com.zyk.api.service.entity.TUser;
import com.zyk.api.service.mapper.TUserMapper;
import com.zyk.api.service.entity.TUser;
import com.zyk.api.service.mapper.TUserMapper;
import com.zyk.api.service.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台用户表 服务实现类
 * </p>
 *
 * @author auto
 * @since 2019-11-29
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
