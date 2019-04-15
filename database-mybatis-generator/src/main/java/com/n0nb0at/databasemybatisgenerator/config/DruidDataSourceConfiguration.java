package com.n0nb0at.databasemybatisgenerator.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author guopeng
 * @date 2019-04-15
 */
public class DruidDataSourceConfiguration {

    @Autowired
    private DruidDataSourceProperties masterProperties;

    @Bean
    public FilterRegistrationBean druidFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,/health/*");
        return filterRegistrationBean;
    }

    @Bean(initMethod = "init", destroyMethod = "close", name="masterDataSource")
    public DataSource masterDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setDriverClassName(masterProperties.getDriverClassName());
            druidDataSource.setUrl(masterProperties.getUrl());
            druidDataSource.setUsername(masterProperties.getUsername());
            druidDataSource.setPassword(masterProperties.getPassword());
            druidDataSource.setInitialSize(masterProperties.getInitialSize());
            druidDataSource.setMinIdle(masterProperties.getMinIdle());
            druidDataSource.setMaxActive(masterProperties.getMaxActive());
            druidDataSource.setValidationQuery(masterProperties.getValidationQuery());
            druidDataSource.setFilters("mergeStat, wall, config");
        } catch (SQLException e) {
            throw new IllegalStateException("Could not initial Druid masterDataSource\n" + e);
        }
        return druidDataSource;
    }
}
