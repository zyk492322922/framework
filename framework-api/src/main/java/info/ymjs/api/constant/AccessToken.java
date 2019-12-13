package info.ymjs.api.constant;

import lombok.Data;

/**
 * token
 * @author Administrator
 */
@Data
public class AccessToken {
    /** jwt token */
    private String token;
    /** 用户id */
    private int userId;
    /** 用户名 */
    private String username;
    /** 时间毫秒数 */
    private long ts;

}
