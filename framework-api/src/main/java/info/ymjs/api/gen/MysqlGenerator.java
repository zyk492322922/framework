package info.ymjs.api.gen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MysqlGenerator {

    /**
     * RUN THIS
     */
    public static void main(String[] args) {

        String dbUrl = "jdbc:p6spy:mysql://39.106.191.98:3306/ymjs?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String dbUserName = "root";
        String dbPassword = "Hyjf@2019";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        
        //订单 order
        String whichserve = "yimeng-api";
        String modubleName = "";
        String packageName = "info.ymjs.api.service";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String curPath = MysqlGenerator.class.getClassLoader().getResource("").getFile();
        File curDir = new File(curPath);
        curPath = curDir.getParentFile().getParentFile().getParentFile().getParent();
     
        
        curPath = curPath+File.separator+"yimeng"+File.separator+whichserve;
        String projectPath = curPath;
        
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("auto");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);
        dsc.setDriverName("com.p6spy.engine.spy.P6SpyDriver");
        dsc.setUsername(dbUserName);
        dsc.setPassword(dbPassword);
        mpg.setDataSource(dsc);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(modubleName);
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/"+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null).setController(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setInclude("t_banner");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
