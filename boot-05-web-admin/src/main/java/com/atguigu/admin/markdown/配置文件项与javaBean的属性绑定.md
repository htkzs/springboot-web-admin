@ConfigurationProperties


@EnableConfigurationProperties两个功能 使用说明，必须标注在配置类上
1.指定配置文件需要绑定的javaBean
2.将这个配置文件放入到IOC容器中

@Configuration(proxyBeanMethods = true)
@EnableConfigurationProperties(Car.class)
public class ApplicationConfig {
}
@ConfigurationProperties(prefix = "mycar")
public class Car {}


@Component+@ConfighurationProperties

@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {}

配置文件的写法：
    mycar.brand=BWD
    mycar.price=10000