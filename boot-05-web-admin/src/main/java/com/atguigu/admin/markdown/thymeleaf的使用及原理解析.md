>Thymeleaf的官方网站 https://www.thymeleaf.org/
>1、thymeleaf使用
   1.1、引入Starter 
   ```xml
       <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
    ```
2、自动配置好了thymeleaf  ThymeleafAutoConfiguration

        @Configuration(proxyBeanMethods = false)
        @EnableConfigurationProperties(ThymeleafProperties.class)
        @ConditionalOnClass({ TemplateMode.class, SpringTemplateEngine.class })
        @AutoConfigureAfter({ WebMvcAutoConfiguration.class, WebFluxAutoConfiguration.class })
        public class ThymeleafAutoConfiguration {}
        
自动配好的策略
● 1、所有thymeleaf的配置值都在 ThymeleafProperties
● 2、配置好了 SpringTemplateEngine 模板引擎
● 3、配好了 ThymeleafViewResolver  视图解析器
● 4、我们只需要直接开发页面
       
        @ConfigurationProperties(prefix = "spring.thymeleaf")
         public class ThymeleafProperties {
             private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
             public static final String DEFAULT_PREFIX = "classpath:/templates/";
             public static final String DEFAULT_SUFFIX = ".html";
        
       
3.编写程序
     页面开发需要一个名称空间 <html lang="en" xmlns:th="http://www.thymeleaf.org">
     
     
     
     
