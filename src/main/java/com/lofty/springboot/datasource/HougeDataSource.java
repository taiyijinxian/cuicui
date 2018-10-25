package com.lofty.springboot.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "entityManagerFactoryHouge",
//        transactionManagerRef = "transactionManagerHouge",
//        basePackages = {"com.lofty.springboot.repository.houge" })
public class HougeDataSource {

    @Autowired
    @Qualifier("dataSourceHouge")
    private DataSource dataSourceHouge;
 
    @Primary
    @Bean(name = "entityManagerFactoryHouge")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryHouge(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSourceHouge)
                .packages("com.lofty.springboot.domain.houge")
                .persistenceUnit("hougePersistenceUnit").build();
        return em;
    }
 
    @Primary
    @Bean(name = "transactionManagerHouge")
    PlatformTransactionManager transactionManagerHouge(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryHouge(builder).getObject());
    }

}
