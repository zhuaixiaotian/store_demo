package com.cy.store.config;

import com.cy.store.interceptor.loginintercepton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**拦截器的注册*/
@Configuration //自动加载当前的类并进行拦截器的注册,如果没有@Configuration就相当于没有写类LoginInterceptorConfigure
public class LoginInterceptorConfigure implements WebMvcConfigurer {
    @Autowired
    private    loginintercepton loginintercepton;

    @Override
    //配置拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        //1.创建自定义的拦截器对象

        //2.配置白名单并存放在一个List集合
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");

        //registry.addInterceptor(interceptor);完成拦截
        // 器的注册,后面的addPathPatterns表示拦截哪些url
        //这里的参数/**表示所有请求,再后面的excludePathPatterns表
        // 示有哪些是白名单,且参数是列表
       registry.addInterceptor(loginintercepton).addPathPatterns
                     ("/**")
               .excludePathPatterns(patterns);

    }
}
