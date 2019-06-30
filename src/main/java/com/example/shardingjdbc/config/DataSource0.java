package com.example.shardingjdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name DataSource0
 * @Date 2019/02/21 14:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.db0")
public class DataSource0 {

        private String dbname;
        private String driverClassName;
        private String url;
        private String username;
        private String password;

    public DataSource createDataSource() {
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(getDriverClassName());
        result.setUrl(getUrl());
        result.setUsername(getUsername());
        result.setPassword(getPassword());

        return result;
    }
}
