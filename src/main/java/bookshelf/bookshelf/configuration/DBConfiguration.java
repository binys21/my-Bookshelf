package bookshelf.bookshelf.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {
    @Autowired
    private ApplicationContext applicationContext;
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

    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(
                applicationContext.getResources("classpath:/mapper/**/sql-*.xml")
        );
        return sessionFactoryBean.getObject();

    }
    @Bean
    SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
