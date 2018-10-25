package com.lofty.springboot.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

//@Configuration
//@PropertySource("classpath:config/jdbc.properties")
public class DataSourceConfig {

    @Primary
    @Bean(name = "dataSourceHouge")
    @ConfigurationProperties(prefix = "spring.datasource.houge")
    public DataSource dataSourceHouge() {
        return DataSourceBuilder.create().build();
    }
 
    @Bean(name = "dataSourceGkh")
    @ConfigurationProperties(prefix = "spring.datasource.gkh")
    public DataSource dataSourceGkh() {
        return DataSourceBuilder.create().build();
    }
 

}
