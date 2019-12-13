package info.ymjs.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author zhangqingqing
 * @version SystemConfig, v0.1 2019/4/3 14:30
 */
@Configuration
@Data
public class SystemConfig {


    @Value("${ignore.url}")
    private String ignoreUrl;

}
