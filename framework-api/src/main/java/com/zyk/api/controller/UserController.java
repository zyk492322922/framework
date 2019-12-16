/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.zyk.api.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zyk.api.common.request.TUserRequest;
import com.zyk.api.common.response.R;
import com.zyk.api.service.entity.TSmsConfig;
import com.zyk.api.service.entity.TUser;
import com.zyk.api.util.HttpDeal;
import com.zyk.api.util.RedisConstants;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyjf.framework.starter.redis.util.RedisUtils;
import com.hyjf.framework.starter.sms.config.SimpleSMSSender;
import com.hyjf.framework.starter.sms.util.SmsUtil;

import com.zyk.api.common.request.TUserRequest;
import com.zyk.api.common.response.R;
import com.zyk.api.service.entity.TSmsConfig;
import com.zyk.api.service.entity.TUser;
import com.zyk.api.service.service.ITSmsConfigService;
import com.zyk.api.service.service.ITUserService;
import com.zyk.api.util.HttpDeal;
import com.zyk.api.util.RedisConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dzs
 * @version UserController, v0.1 2019/11/27 9:52
 */
@Slf4j
@RestController
@Api(value = "用户接口", tags = "用户接口")
@RequestMapping("/user")
public class UserController {
	@Autowired
	ITUserService iTUserService;
	@Autowired
	ITSmsConfigService iTSmsConfigService;

	@ApiOperation(value = "注册用户")
	@PostMapping("/register")
	public R register(HttpServletRequest request, @RequestBody TUserRequest tUserRequest) {
		if (RedisUtils.get(RedisConstants.SMS + tUserRequest.getMobile()) == null
				|| !tUserRequest.getCode().equals(RedisUtils.get(RedisConstants.SMS + tUserRequest.getMobile()))) {
			return R.fail("短信验证码无效");
		}
		RedisUtils.del(RedisConstants.SMS + tUserRequest.getMobile());
		TUser entity = new TUser();
		BeanUtils.copyProperties(tUserRequest, entity);
		entity.setRegTime(LocalDateTime.now());
		entity.setCreateTime(LocalDateTime.now());
		entity.setRegIp(getIPAddress(request));
		boolean bo = iTUserService.save(entity);
		if (!bo) {
			return R.fail("注册失败");
		}
		return R.success();
	}

	@ApiOperation(value = "发送短信")
	@PostMapping("/sms")
	public R sendSMS(@RequestBody TUserRequest tUserRequest) {
		QueryWrapper<TSmsConfig> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().select().and(mobile -> mobile.eq(TSmsConfig::getMobile, tUserRequest.getMobile()))
				.and(createTime -> createTime.between(TSmsConfig::getCreateTime,
						LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
						LocalDateTime.of(LocalDate.now(), LocalTime.MAX)));
		int count = iTSmsConfigService.count(queryWrapper);

		if (count > 10) {
			return R.fail("超过10条提示：今日短信次数已超限，请明日再试。");
		}
		String code = RandomStringUtils.randomNumeric(4);
//    	 String code="1111";
		SimpleSMSSender.SMS sms = SimpleSMSSender.newSMS();
		sms.setPhoneNumbers(tUserRequest.getMobile());
		sms.setTemplateParam("{\"code\":\"" + code + "\"}");
		sms.setTemplateCode("SMS_180046967");
		SmsUtil.sendSms(sms);
		RedisUtils.set(RedisConstants.SMS + tUserRequest.getMobile(), code, RedisConstants.ONE_MINUTE_EXPIRE);
		TSmsConfig entity = new TSmsConfig();
		entity.setCreateTime(LocalDateTime.now());
		entity.setMobile(tUserRequest.getMobile());
		entity.setSmsContent("短信验证码为:" + code);
		entity.setSmsType(tUserRequest.getSmsType());
		iTSmsConfigService.save(entity);
		return R.success();
	}

	@ApiOperation(value = "忘记密码")
	@PostMapping("/forget")
	public R forget(@RequestBody TUserRequest tUserRequest) {
		if (RedisUtils.get(RedisConstants.SMS + tUserRequest.getMobile()) == null
				|| !tUserRequest.getCode().equals(RedisUtils.get(RedisConstants.SMS + tUserRequest.getMobile()))) {
			return R.fail("短信验证码无效");
		}
		RedisUtils.del(RedisConstants.SMS + tUserRequest.getMobile());
		QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().select().and(mobile -> mobile.eq(TUser::getMobile, tUserRequest.getMobile()));
		List<TUser> iUsers = iTUserService.list(queryWrapper);
		if (iUsers.isEmpty()) {
			return R.fail("该手机号未注册");
		}
		TUser entity = iUsers.get(0);
		entity.setUpdateTime(LocalDateTime.now());
		entity.setPassword(tUserRequest.getPassword());
		boolean bo = iTUserService.updateById(entity);
		if (!bo) {
			return R.fail("修改密码失败");
		}
		return R.success();
	}

	@ApiOperation(value = "修改手机号")
	@PostMapping("/mobile")
	public R mobile(@RequestBody TUserRequest tUserRequest) {
		if (RedisUtils.get(RedisConstants.SMS + tUserRequest.getMobile()) == null
				|| !tUserRequest.getCode().equals(RedisUtils.get(RedisConstants.SMS + tUserRequest.getMobile()))) {
			return R.fail("短信验证码无效");
		}
		RedisUtils.del(RedisConstants.SMS + tUserRequest.getMobile());
		TUser user = iTUserService.getById(tUserRequest.getUserId());
		user.setMobile(tUserRequest.getMobile());
		user.setUpdateTime(LocalDateTime.now());
		boolean bo = iTUserService.updateById(user);
		if (!bo) {
			return R.fail("修改手机号失败");
		}
		return R.success();
	}

	@ApiOperation(value = "修改密码")
	@PostMapping("/password")
	public R password(@RequestBody TUserRequest tUserRequest) {
		TUser user = iTUserService.getById(tUserRequest.getUserId());
		if (!user.getPassword().equals(tUserRequest.getPassword())) {
			return R.fail("原密码不正确");
		}
		user.setPassword(tUserRequest.getNewPassword());
		user.setUpdateTime(LocalDateTime.now());
		boolean bo = iTUserService.updateById(user);
		if (!bo) {
			return R.fail("修改手机号失败");
		}
		return R.success();
	}

	@ApiOperation(value = "登录")
	@PostMapping("/sign")
	public R<TUser> sign(@RequestBody TUserRequest tUserRequest) {
		QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().select().and(mobile -> mobile.eq(TUser::getMobile, tUserRequest.getMobile()));
		List<TUser> iUsers = iTUserService.list(queryWrapper);
		if (iUsers.isEmpty()) {
			return R.fail("该手机号未注册");
		}
		TUser entity = iUsers.get(0);
		if (!entity.getPassword().equals(tUserRequest.getPassword())) {
			return R.fail("密码无效");
		}
		LocalDateTime time = entity.getLastLoginTime();
		entity.setLastLoginTime(LocalDateTime.now());
		boolean bo = iTUserService.updateById(entity);
		if (!bo) {
			return R.fail("登录失败");
		}
		entity.setPassword("");
		entity.setLastLoginTime(time);
		return R.data(entity);
	}

	@ApiOperation(value = "获取当前用户信息,请不要用接口的上次登录时间,用登录时候的")
	@PostMapping("/info")
	public R<TUser> info(@RequestBody TUserRequest tUserRequest) {
		TUser entity = iTUserService.getById(tUserRequest.getUserId());
		entity.setPassword("");
		return R.data(entity);
	}

	private String ticket(HttpServletRequest request) {
		// 拼装所需参数
		String aid = "2026782647";
		String AppSecretKey = "0K6K0OwY9HELsYAQ2u_gzQA**";
		String ticket = request.getParameter("ticket");
		String randstr = request.getParameter("randstr");
		String userIp = getIPAddress(request);
		String verifyUrl = "https://ssl.captcha.qq.com/ticket/verify?aid=" + aid + "&AppSecretKey=" + AppSecretKey
				+ "&Ticket=" + ticket + "&Randstr=" + randstr + "&UserIP=" + userIp;
		log.info(userIp + "=>无感知验证码请求地址:" + verifyUrl);
		String backRes = HttpDeal.get(verifyUrl);
		log.info(userIp + "=>无感知验证码请求返回:" + backRes);

		// 解析返回的JSON串
		JSONObject jsonObject = JSON.parseObject(backRes);
		String backResponse = (String) jsonObject.get("response");
		String backErrMsg = (String) jsonObject.get("err_msg");
		if ("1".equals(backResponse)) {
			// 无感知图形验证码验证通过
			return null;
		} else {
			String getBackErrMsg = this.backErrMsg(backErrMsg);
			return "图形验证码:" + getBackErrMsg;
		}
	}

	private static String getIPAddress(HttpServletRequest request) {
		String ip = null;

		// X-Forwarded-For：Squid 服务代理
		String ipAddresses = request.getHeader("X-Forwarded-For");

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// Proxy-Client-IP：apache 服务代理
			ipAddresses = request.getHeader("Proxy-Client-IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// WL-Proxy-Client-IP：weblogic 服务代理
			ipAddresses = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// HTTP_CLIENT_IP：有些代理服务器
			ipAddresses = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// X-Real-IP：nginx服务代理
			ipAddresses = request.getHeader("X-Real-IP");
		}

		// 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
		if (ipAddresses != null && ipAddresses.length() != 0) {
			ip = ipAddresses.split(",")[0];
		}

		// 还是不能获取到，最后再通过request.getRemoteAddr();获取
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 匹配返回的错误信息
	 * 
	 * @param errCode
	 * @return
	 */
	private String backErrMsg(String errCode) {
		String backMsg = "";
		switch (errCode) {
		case "OK":
			backMsg = "验证通过";
			break;
		case "user code len error":
			backMsg = "验证码长度不匹配";
			break;
		case "captcha no match":
			backMsg = "验证码答案不匹配/Randstr参数不匹配";
			break;
		case "verify timeout":
			backMsg = "验证码签名超时";
			break;
		case "Sequnce repeat":
			backMsg = "验证码签名重放";
			break;
		case "Sequnce invalid":
			backMsg = "验证码签名序列";
			break;
		case "Cookie invalid":
			backMsg = "验证码cookie信息不合法";
			break;
		case "verify ip no match":
			backMsg = "ip不匹配";
			break;
		case "decrypt fail":
			backMsg = "验证码签名解密失败";
			break;
		case "appid no match":
			backMsg = "验证码强校验appid错误";
			break;
		case "param err":
			backMsg = "AppSecretKey参数校验错误";
			break;
		case "cmd no match":
			backMsg = "验证码系统命令号不匹配";
			break;
		case "uin no match":
			backMsg = "号码不匹配";
			break;
		case "seq redirect":
			backMsg = "重定向验证";
			break;
		case "opt no vcode":
			backMsg = "操作使用pt免验证码校验错误";
			break;
		case "diff":
			backMsg = "差别，验证错误";
			break;
		case "captcha type not match":
			backMsg = "验证码类型与拉取时不一致";
			break;
		case "verify type error":
			backMsg = "验证类型错误";
			break;
		case "invalid pkg":
			backMsg = "非法请求包";
			break;
		case "bad visitor":
			backMsg = "策略拦截";
			break;
		case "system busy":
			backMsg = "系统内部错误";
			break;
		default:
			backMsg = "验证错误";
			break;
		}
		return backMsg;
	}
}
