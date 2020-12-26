package com.purchase.purchasecommon.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * mybatis CRUD 代码生成器
 */

public class CodeGenerator {

    /**
     * 模块名
     */
    private static final String moduleName = "purchase-user";

    /**
     * 作者
     */
    private static final String author = "ZhengHuaJing";

    /**
     * 数据库相关
     */
    private static final String dbDriverName = "com.mysql.cj.jdbc.Driver";
    private static final String dbName = "changgou_user";
    private static final String dbTablePrefix = "tb";
    private static final String dbHost = "127.0.0.1";
    private static final String dbPort = "3308";
    private static final String dbUserName = "root";
    private static final String dbPassword = "root123456";

    /**
     * 包名
     */
    private static final String packageName = "com.purchase.purchaseuser";

    private CodeGenerator() {
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(final String tip) {
        final Scanner scanner = new Scanner(System.in);

        final StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            final String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(final String[] args) {
        // 代码生成器
        final AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        final GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir") + "/" + moduleName;
        gc.setOutputDir(projectPath + "/src/main/java");


//        gc.setOutputDir("D:\\test");
        gc.setAuthor(author);
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        final DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName(dbDriverName);
        dsc.setUsername(dbUserName);
        dsc.setPassword(dbPassword);
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        final InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        final String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        final List<FileOutConfig> focList = new ArrayList();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(final TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        final TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        final StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(dbTablePrefix);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
