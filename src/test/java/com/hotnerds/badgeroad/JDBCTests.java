package com.hotnerds.badgeroad;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JDBCTests {
    @Before
    public void Setup() {

    }

    @Value("${spring.datasource.url}")
    String url_;

    @Value("${spring.datasource.user}")
    String user_;

    @Value("${spring.datasource.password}")
    String password_;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {

        String url = "jdbc:mysql://localhost:3306/badgeroad?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "jeong1998";
        try(Connection con =
                    DriverManager.getConnection(
                            url,
                            user,
                            password)){
            System.out.println(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}
