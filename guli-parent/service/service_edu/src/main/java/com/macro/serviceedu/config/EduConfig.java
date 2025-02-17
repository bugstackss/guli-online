package com.macro.serviceedu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author macro
 * @description
 * @date 2023/11/21 15:50
 */
@Configuration
@MapperScan("com.macro.serviceedu.mapper")
public class EduConfig {
    /**
     * SQL 执行性能分析插件 开发环境使用,线上不推荐. maxTime指的是 sql 最大执行时长
     * 设置 dev test 环境开启
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        final PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /* ms, 超过此设置的ms则sql不执行 */
        performanceInterceptor.setMaxTime(5000);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
 