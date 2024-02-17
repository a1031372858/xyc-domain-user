package org.xyc.domain.user.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;

/**
 * @author xuyachang
 * @date 2024/1/16
 */
public class MybatisPlusAutoGeneratorUtil {

    public static void execute(){
        DataSourceConfig.Builder config = new DataSourceConfig.Builder(
                "jdbc:mysql://47.116.216.209:3306/xyc_mall",
                "root",
                "xycmall20240204");
        //mybatisPlus总共支持6种配置
        FastAutoGenerator.create(config)
                //自定义全局配置
                .globalConfig((scanner,builder)  -> {
                    //配置作者
                    builder.author("xuyachang")
                            //配置时间
                            .commentDate("yyyy-MM-dd")
                            //输出目录
                            .outputDir("D:\\workspace\\private\\xyc-domain-user\\xyc-domain-user-implement\\src\\main\\java");
                })
                //包配置
                .packageConfig((scanner,builder) -> {
                    //配置父包名
                    builder.parent("org.xyc.domain.user");
                })
                //策略配置
                .strategyConfig((scanner,builder)  -> {
                    // 设置需要生成的表名
                    builder.addInclude("user_p_o")
                            //配置模型
                            .entityBuilder().enableLombok().enableFileOverride()
                            //配置控制器
                            .controllerBuilder().enableRestStyle().enableFileOverride()
                            //配置服务
                            .serviceBuilder().enableFileOverride()
                            //配置Mapper
                            .mapperBuilder().enableFileOverride();
                })
                //模板配置
                .templateConfig((scanner,builder)->{
                    //配置不需要生成的文件
                    builder.disable(TemplateType.CONTROLLER,TemplateType.SERVICE,TemplateType.SERVICE_IMPL);
                })
                //注入配置
//                .injectionConfig((scanner,builder)  ->{})
                //模板引擎配置
//                .templateConfig(builder->{})
                .execute();
    }

    public static void main(String[] args) {
        MybatisPlusAutoGeneratorUtil.execute();
    }
}
