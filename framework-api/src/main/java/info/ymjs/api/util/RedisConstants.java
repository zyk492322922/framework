package info.ymjs.api.util;

/**
 * redis专用常量
 * @author Administrator
 * @version
 */
public class RedisConstants {

    /**
     * redis - 1分钟过期
     */
    public static final Integer ONE_MINUTE_EXPIRE = 60;

    /**
     * redis - 5分钟过期
     */
    public static final Integer FIVE_MINUTE_EXPIRE = 5*60;

    /**
     * sign(sign:sign)
     */
    public static final String SIGN = "sign:";

    /**
     * 用户token令牌前缀（token:token）
     */
    public static final String USER_TOKEN_KEY = "token:";

    /**
     * 用户信息缓存
     * */
    public static final String USERID_KEY = "user_id:";
    /**
     * 手机短信验证码
     * */
    public static final String SMS = "SMS:";


}
