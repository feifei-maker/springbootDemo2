package com.whc.springboot.filter;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * ClassName: RequestParamaFilter <br/>
 * Description: <br/>
 * date: 2020/8/18 16:24<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@WebFilter(filterName = "requestParamFilter", urlPatterns = "/**")
//当有多个过滤器的时候，使用该注解，数字越小过滤器越靠前
//@Order(1)
public class RequestParamFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("init------------------------------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("doFilter----------------------------------");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        Map<String, String[]> map = httpServletRequest.getParameterMap();
//        map.put("paramKey", new String[]{"***"});
        //使用HttpServletRequest的包装类，就能对map的值进行替换
        //使用匿名内部类就不用再写一个类进行继承
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpServletRequest) {
            //对包装类的接口进行重写
            @Override
            public String getParameter(String name) {
                String value = httpServletRequest.getParameter(name);
                if (StringUtils.isNotBlank(value)) {
                    return value.replace("abc", "***");
                }else {
                    return super.getParameter(name);
                }
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] names = httpServletRequest.getParameterValues(name);
                if (names != null || names.length>0){
                    for (int i = 0; i < names.length; i++) {
                        names[i] = names[i].replaceAll("abc","￥￥￥");
                    }
                    return names;
                }
                return super.getParameterValues(name);
            }
        };
        filterChain.doFilter(wrapper, servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.debug("destroy-----------------------------------");
    }
}
