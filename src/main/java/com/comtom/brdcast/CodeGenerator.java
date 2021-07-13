package com.comtom.brdcast;

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
 * 目前版本不支持瀚高数据库 cry~~
 */
public class CodeGenerator {
//    /**
//     * 代码生成位置
//     */
//    public static final String PARENT_NAME = "com.comtom.brdcast";
//
//    /**
//     * modular 名字
//     */
//    public static final String MODULAR_NAME = "";
//
//    /**
//     * 基本路径
//     */
//    public static final String SRC_MAIN_JAVA = "src/main/java/";
//
//    /**
//     * 作者
//     */
//    public static final String AUTHOR = "zw";
//
//    /**
//     * 是否是 rest 接口
//     */
//    private static final boolean REST_CONTROLLER_STYLE = true;
//
//    public static final String JDBC_MYSQL_URL = "jdbc:highgo://192.168.111.7:5866/comtom";
//
//    public static final String JDBC_DRIVER_NAME = "com.highgo.jdbc.Driver";
//
//    public static final String JDBC_USERNAME = "sysdba";
//
//    public static final String JDBC_PASSWORD = "Cqxm@123";
//
//    public static void main(String[] args) {
//        String moduleName = scanner("模块名").replaceAll("#", "");
//        String tableName = scanner("表名");
//        String tablePrefix = scanner("表前缀(无前缀输入#)").replaceAll("#", "");
//        autoGenerator(moduleName, tableName, tablePrefix);
//    }
//
//    public static void autoGenerator(String moduleName, String tableName, String tablePrefix) {
//        new AutoGenerator()
//                .setGlobalConfig(getGlobalConfig())
//                .setDataSource(getDataSourceConfig())
//                .setPackageInfo(getPackageConfig(moduleName))
//                .setStrategy(getStrategyConfig(tableName, tablePrefix))
//                .setCfg(getInjectionConfig(moduleName))
//                .setTemplate(getTemplateConfig())
//                .execute();
//    }
//
//    private static String getDateTime() {
//        LocalDateTime localDate = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        return localDate.format(formatter);
//    }
//
//    private static InjectionConfig getInjectionConfig(final String moduleName) {
//        return new InjectionConfig() {
//            @Override
//            public void initMap() {
//                Map map = new HashMap();
//                map.put("dateTime", getDateTime());
//                setMap(map);
//                final String projectPath = System.getProperty("user.dir");
//                List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
//                // 自定义配置会被优先输出
//                fileOutConfigList.add(new FileOutConfig("/templates/mapper.xml.vm") {
//                    @Override
//                    public String outputFile(TableInfo tableInfo) {
//                        // 自定义输出文件名，如果entity设置了前后缀，此次注意xml的名称也会跟着发生变化
//                        return projectPath + "/src/main/resources/mybatis/" +
//                                moduleName + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//                    }
//                });
//                setFileOutConfigList(fileOutConfigList);
//            }
//        };
//    }
//
//
//    private static StrategyConfig getStrategyConfig(String tableName, String tablePrefix) {
//        return new StrategyConfig()
//                .setNaming(NamingStrategy.underline_to_camel)
//                .setColumnNaming(NamingStrategy.underline_to_camel)
//                .setInclude(tableName)
//                .setRestControllerStyle(REST_CONTROLLER_STYLE)
//                .setEntityBuilderModel(true)
//                //.setControllerMappingHyphenStyle(true)
//                .setEntityLombokModel(true);
//
//                //.entityTableFieldAnnotationEnable(true)
//                //.setTablePrefix(tablePrefix + "_");
//    }
//
//    private static PackageConfig getPackageConfig(String moduleName) {
//        return new PackageConfig()
//                .setModuleName(moduleName)
//                .setParent(PARENT_NAME)
//                .setService("service")
//                .setServiceImpl("service.impl")
//                .setController("controller")
//                .setEntity("entity");
//    }
//
//    private static DataSourceConfig getDataSourceConfig() {
//        return new DataSourceConfig()
//                .setUrl(JDBC_MYSQL_URL)
//                .setDriverName(JDBC_DRIVER_NAME)
//                .setUsername(JDBC_USERNAME)
//                .setPassword(JDBC_PASSWORD);
//    }
//
//    private static GlobalConfig getGlobalConfig() {
//        String projectPath = System.getProperty("user.dir");
//        String filePath = projectPath + "/" + MODULAR_NAME + SRC_MAIN_JAVA;
//        if (PlatformUtil.isWindows()) {
//            filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
//        } else {
//            filePath = filePath.replaceAll("/+|\\\\+", "/");
//        }
//        return new GlobalConfig()
//                .setOutputDir(filePath)
//                .setDateType(DateType.ONLY_DATE)
//                .setIdType(IdType.UUID)
//                .setAuthor(AUTHOR)
//                .setBaseColumnList(true)
//                .setSwagger2(true)
//                .setEnableCache(false)
//                .setBaseResultMap(true)
//                .setOpen(false);
//    }
//
//    private static TemplateConfig getTemplateConfig() {
//        return new TemplateConfig()
//                .setController("/templates-generator/controller.java.vm")
//                .setService("/templates-generator/service.java.vm")
//                .setServiceImpl("/templates-generator/serviceImpl.java.vm")
//                .setEntity("/templates-generator/entity.java.vm")
//                .setMapper("/templates-generator/mapper.java.vm")
//                .setXml("/templates-generator/mapper.xml.vm");
//    }
//
//    private static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder sb = new StringBuilder();
//        sb.append("please input " + tip + " : ");
//        System.out.println(sb.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotBlank(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("please input the correct " + tip + ". ");
//    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zw");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:highgo://192.168.111.7:5866/comtom");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.highgo.jdbc.Driver");
        dsc.setUsername("sysdba");
        dsc.setPassword("Cqxm@123");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.comtom.brdcast");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
         String templatePath = "/templates-generator/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mybatis/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
