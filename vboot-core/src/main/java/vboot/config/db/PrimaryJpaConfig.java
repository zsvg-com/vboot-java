package vboot.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class PrimaryJpaConfig {

    @Autowired
    private DataSource dataSource;

    @Value("${spring.datasource.primary.jpa.entityScan}")
    private String ENTITY_SCAN;

    @Value("${spring.datasource.primary.jpa.hbm2ddl.auto}")
    private String DDL_AUTO;

    @Primary
    @Bean(name = "entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    private Map<String, String> getVendorProperties() {
        Map<String, String> map = new HashMap<>();
//        map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        map.put("hibernate.hbm2ddl.auto", DDL_AUTO);
        map.put("hibernate.physical_naming_strategy", "vboot.config.db.VbootNamingStrategy");
        return map;
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("vboot",ENTITY_SCAN)
                .properties(getVendorProperties())
                .persistenceUnit("persistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

}