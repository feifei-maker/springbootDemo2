package com.whc.springboot.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: RequestViewInterceptor <br/>
 * Description: <br/>
 * date: 2020/8/19 10:39<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Component
public class RequestViewInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestViewInterceptor.class);

    //在请求发生前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("Pre interceptor-----------------------");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    //在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行（请求完成之后执行）
    //preHandle方法的返回值为true时才会执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.debug("Post interceptor-----------------------");
        if (modelAndView == null||modelAndView.getViewName().startsWith("redirect")){
            return;
        }else {
            String path = request.getServletPath();
            //获取从后端传到前端的数据中的key值为"template"的数据。
            String template = (String) modelAndView.getModelMap().get("template");
            if (StringUtils.isBlank(template)) {
                //如果路径是以"/"开头就去掉
                if (path.startsWith("/")) {
                    path = path.substring(1);
                }
                //toLowerCase()-->把字符串转换为小写
                modelAndView.getModelMap().addAttribute("template", path.toLowerCase());
            }
        }
        HandlerInterceptor.super.preHandle(request, response, handler);
    }

    //在整个请求完成之后，也就是DispatcherServlet渲染了视图执行
    //preHandle方法的返回值为true时才会执行，用于清理资源
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("After interceptor-----------------------");
        HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
