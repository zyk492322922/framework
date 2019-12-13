package info.ymjs.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hyjf.framework.starter.swagger.EnableSwagger2;

/**
 * 
 * @author framework
 * @date 2019/11/26
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan({ "info.ymjs.**.mapper" })
public class ApiApplication {
	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);
		System.out.println(" (♥◠‿◠)ﾉﾞ ApiApplication 启动成功   ლ(´ڡ`ლ)ﾞ  \n");
	}
}
