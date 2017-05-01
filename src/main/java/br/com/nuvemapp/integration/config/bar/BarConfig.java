package br.com.nuvemapp.integration.config.bar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "barEntityManagerFactory", 
        transactionManagerRef = "barTransactionManager",
        basePackages = { "br.com.nuvemapp.integration.repositories.bar" })
public class BarConfig {

	@Autowired
    JpaVendorAdapter jpaVendorAdapter;

    /**
     * Primary because if we have activated embedded databases, we do not want
     * the application to connect to an external database.
     */
    @Bean(name = "barDataSource")
    public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/bar_schema");
	    dataSource.setUsername("root");
	    dataSource.setPassword("root");
	    return dataSource;
    }

    @Bean(name = "barEntityManager")
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean(name = "barEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource());
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("br.com.nuvemapp.domain.bar");
        lef.setPersistenceUnitName("barPersistenceUnit");
        lef.afterPropertiesSet();
        return lef.getObject();
    }

    @Bean(name = "barTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

}