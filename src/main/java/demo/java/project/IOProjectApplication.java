package demo.java.project;

import demo.java.project.utils.BaseUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author happy
 */
@SpringBootApplication
@ConfigurationProperties(value="classpath:application.yml")
@ComponentScan(basePackages = {"demo.java.project.demo"})
public class IOProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(IOProjectApplication.class, args);
    }
}
