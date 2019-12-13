package info.ymjs.api.common.response;


import info.ymjs.api.constant.SYSConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务代码枚举
 *
 * @author framework
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

	/**
	 * 操作成功 1
	 */
	SUCCESS(SYSConstant.DEFAULT_SUCCESS_CODE, SYSConstant.DEFAULT_SUCCESS_MESSAGE),

	/**
	 * 操作失败 99
	 */
	FAILURE(SYSConstant.DEFAULT_FAILURE_CODE , SYSConstant.DEFAULT_FAILURE_MESSAGE),

	/**
	 * 需要登录 999
	 */
	NEED_LOGIN(SYSConstant.NEED_LOGIN_CODE,SYSConstant.NEED_LOGIN_MESSAGE),

	/**
	 * 请求没有权限 909
	 */
	UN_AUTHORIZED(SYSConstant.UN_AUTHORIZED_CODE,SYSConstant.UN_AUTHORIZED_MESSAGE),

	// 通用枚举 占位11-50
	// ERR_OBJECT_ 开头
	ERR_OBJECT_REQUIRED(11,"{0}不能为空"),
	ERR_OBJECT_BLANK(12,"{0}未填写"),//**未填写错误
	ERR_OBJECT_DIGIT(13,"{0}不能存在非数字"),
	ERR_OBJECT_DATE(14,"{0}非日期格式"),
	ERR_OBJECT_MAIL(15,"{0}非法邮件地址"),
	ERR_OBJECT_VALUE(16,"传入参数{0}的值非法"),
	ERR_OBJECT_GET(17,"获取{0}失败"),//提取通用
	ERR_OBJECT_INVALID(18,"无效的{0}"),//提取通用
	ERR_OBJECT_UNMATCH(19,"{0}不符合接口要求，请重新传入"),
	ERR_OBJECT_EXCEED_LIMIT(20,"{0}超出限制长度"),
	ERR_OBJECT_DECRYPT(21,"{0}解密失败"),
	ERR_OBJECT_EXISTS(22,"{0}已存在"),
	ERR_OBJECT_NULL(23,"{0}查询的数据不存在"),
	ERR_OBJECT_CUSTOM(99,"{0}"),//自定义错误信息

	// 业务枚举
	// content业务枚举，占位601-700
	// ERR_CONTENT_ 开头

	// order业务枚举,占位701-800
	// ERR_ORDER_ 开头

	// search业务枚举，占位801-900
	// ERR_SEARCH_ 开头

	// user业务枚举，占位901-1000
	// ERR_USER_ 开头
	ERR_USER_CARD_SAVE(911,"员工名片保存失败"),
	ERR_USER_USERNAME_OR_PASSWORD_BLANK(912,"用户名或密码为空"),
	ERR_USER_USERNAME_LENGTH(913,"用户名长度不匹配"),
	ERR_USER_PASSWORD_LENGTH(914,"密码长度不匹配"),
	ERR_USER_EXIST(915,"该用户不存在"),
	ERR_USER_LOGIN(916,"登录失败"),
	ERR_USER_RESET_PASSWORD(917,"重置密码失败"),
	ERR_USER_PASSWORD(918,"手机号或登录密码错误"),
	ERR_USER_TOKEN_CREATE(919,"token生成失败"),
	ERR_USER_SMS_CODE(920,"验证码发送失败"),
	ERR_USER_SMS_CODE_VERIFY(921,"短信验证码错误"),
	ERR_USER_LOGIN_EXPIRE(922,"登录已失效，请重新登录"),
	ERR_USER_VERIFY_CODE(923,"图形验证码验证失败"),
	ERR_USER_TOKEN_EXIST(924,"token不能为空"),
	// system枚举，占位1-10 和 51-99
	// ERR_SYSTEM_ 开头











	/**
	 * 请求未授权
	 *


	 /*//**
	 * 404 没找到请求
	 *//*
	NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 没找到请求"),

	*//**
	 * 消息不能读取
	 *//*
	MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "消息不能读取"),

	*//**
	 * 不支持当前请求方法
	 *//*
	METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "不支持当前请求方法"),

	*//**
	 * 不支持当前媒体类型
	 *//*
	MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "不支持当前媒体类型"),

	*//**
	 * 请求被拒绝
	 *//*
	REQ_REJECT(HttpServletResponse.SC_FORBIDDEN, "请求被拒绝"),

	*//**
	 * 服务器异常
	 *//*
	INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器异常"),

	*//**
	 * 缺少必要的请求参数
	 *//*
	PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST, "缺少必要的请求参数"),

	*//**
	 * 请求参数类型错误
	 *//*
	PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "请求参数类型错误"),

	*//**
	 * 请求参数绑定错误
	 *//*
	PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "请求参数绑定错误"),

	*//**
	 * 参数校验失败
	 *//*
	PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数校验失败"),
	;*/


	// 枚举终结
	ENUM_END(0,"");

	/**
	 * code编码
	 */
	final int status;
	/**
	 * 中文信息描述
	 */
	final String statusDesc;

}
