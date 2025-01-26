package bookshelf.bookshelf.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    @Bean
    DataSource dataSource() {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println(dataSource);
        return dataSource;
    }


}
