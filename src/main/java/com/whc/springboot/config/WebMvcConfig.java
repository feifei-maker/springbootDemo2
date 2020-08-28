package com.whc.springboot.config;

import com.whc.springboot.filter.RequestParamFilter;
import com.whc.springboot.interceptor.RequestViewInterceptor;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMvcConfig <br/>
 * Description: <br/>
 * date: 2020/8/11 18:35<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${server.http.port}")
    private int httpPort;
    @Autowired
    private RequestViewInterceptor requestViewInterceptor;
    @Autowired
    private ResourceConfigBean resourceConfigBean;


    @Bean
    public Connector connector() {
        Connector connector = new Connector();
        connector.setPort(httpPort);
        connector.setScheme("http");
        return connector;
    }

    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector());
        return tomcat;
    }

    //创建过滤器，并将该过滤器注册到容器里面
    @Bean
    public FilterRegistrationBean<RequestParamFilter> register() {
        FilterRegistrationBean<RequestParamFilter> register =
                new FilterRegistrationBean<RequestParamFilter>();
        register.setFilter(new RequestParamFilter());
        return register;
    }
    //拦截器和过滤器的区别：
    //1、拦截器是基于Java的反射机制，而过滤器是基于函数回调
    //2、拦截器更加灵活，过滤器能实现的功能它都能实现
    //3、拦截器只能对控制器起作用，过滤器对request、response对象进行处理
    //4、拦截器能够调用容器中的Bean

    //创建拦截器，并将该过滤器注册到容器里面
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/**");
    }

    //解决resources下面静态资源无法访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取操作系统名字
        String osName = System.getProperty("os.name");
        //如果操作系统的名字是以小写的"win"开头
        if (osName.toLowerCase().startsWith("win")) {
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocationPathForWindows());
        } else {
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocationPathForLinux());
        }
    }
}
