package net.wangjifeng.config;

import com.google.common.base.Predicates;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: WJF
 * @date: 2020/5/21
 * @description: SwaggerConfig
 */

/**
 * {@link Configuration}：标志这是一个配置类，在spring boot引导类启动时，会自动加载这个类的配置。
 * {@link Profile}：说明加载配置文件 'application.yml' 时加载对应的配置。
 * {@link EnableSwagger2}：启用Swagger2的配置。
 */
@Configuration
@Profile("dev")
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket restApi() {
        Class[] classes = this.getClasses();
        Set<String> consumesSet = new HashSet<>();
        consumesSet.add("application/x-www-form-urlencoded");
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .globalOperationParameters(parameters())
            .ignoredParameterTypes(classes)
            .forCodeGeneration(true)
            .consumes(consumesSet)
            .select()
            .apis(Predicates.and(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * API文档的基本信息
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("学生管理平台")
                .description("学生管理平台文档")
                .termsOfServiceUrl("http://127.0.0.1:8080/swagger-ui.html")
                .contact(new Contact("wangjifeng", "http://wangjifeng.net", "wangjifeng0205@qq.com"))
                .version("2.0")
                .build();
    }

    /**
     * Swagger2可以在Swagger2文档中添加参数
     * @return List<Parameter>
     */
    private List<Parameter> parameters() {
        // 添加一个参数wangjifeng，参数描述：wjf
        ParameterBuilder paramBuilder = new ParameterBuilder();
        List<Parameter> paramList = new ArrayList<Parameter>();
        paramBuilder.name("wangjifeng")
                .description("wjf")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();
        paramList.add(paramBuilder.build());
        return paramList;
    }


    /**
     * 获取class数组
     * @return Class[]
     */
    private Class[] getClasses() {
        return new Class[]{};
    }

}
