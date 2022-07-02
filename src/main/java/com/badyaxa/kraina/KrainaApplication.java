package com.badyaxa.kraina;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class KrainaApplication {

//    @Autowired
//    private DataSource dataSource;

    public static final String VERSION_OF_THIS_APP = "0.4";

    public static void main(String[] args) {
        SpringApplication.run(KrainaApplication.class, args);

        log.info("-------------------------------environment>>>");
        Map<String, String> environment = System.getenv();
        environment.forEach((k, v) -> System.out.println("---" + k + ":" + v));
        log.info("<<<environment----------------------------beans>>>");
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        for (String name : context.getBeanDefinitionNames()) {
            log.info("---BeanName = " + name);
        }
        log.info("<<<beans-------------------------------");
    }

//    @PostConstruct
//    public void myRealMainMethod() throws SQLException {
//        log.info("-------------------------------@PostConstruct.myRealMainMethod()>>");
//        Statement stmt = dataSource.getConnection().createStatement();
//        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
//        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
//        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//        while (rs.next()) {
//            System.out.println("Read from DB: " + rs.getTimestamp("tick"));
//        }
//    }
}
