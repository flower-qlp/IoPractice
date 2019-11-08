package demo.java.project.demo.config.mysql;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AllMysqlConfiguration  {

    @Bean(name = "ds1")
    @Primary
    @Qualifier("ds1")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }
}
