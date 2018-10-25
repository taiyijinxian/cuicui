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
//        entityManagerFactoryRef = "entityManagerFactoryGkh",
//        transactionManagerRef = "transactionManagerGkh",
//        basePackages = {"com.lofty.springboot.repository.gkh" })
public class GkhDataSource {

//    @Autowired
//    private JpaProperties jpaProperties;
 
    @Autowired
    @Qualifier("dataSourceGkh")
    private DataSource dataSourceGkh;
 
    @Bean(name = "entityManagerFactoryGkh")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryGkh(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSourceGkh)
//                .properties(getVendorProperties(dataSourceHouge))
                .packages("com.lofty.springboot.domain.gkh")
                .persistenceUnit("gkhPersistenceUnit").build();
        return em;
    }
 
    @Bean(name = "transactionManagerGkh")
    PlatformTransactionManager transactionManagerGkh(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryGkh(builder).getObject());
    }
 
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }


}
