package com.neuedu.ec.common;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class AppConfig {

    @Value("${cors.origin}")
    private String corsOrigin;

    /**
     * 分页插件
     * @return
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        paginationInterceptor.setDialect(new MySqlDialect());
//        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
//        return paginationInterceptor;
//    }

    @Bean
    public PaginationInterceptor paginationInterceptor2() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        page.setOverflow(true); //合理化
        return page;
    }


    /**
     * Springboot 跨域
     * @return
     */
    @Bean
    FilterRegistrationBean<CorsFilter> cors(){
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<CorsFilter>();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin(corsOrigin);
//        config.addAllowedOrigin("http://127.0.0.1:81");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        CorsFilter filter = new CorsFilter(source);
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }



}
