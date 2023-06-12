package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDAO {
    public  Connection connection() throws Exception {


        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "";
        String password = "";
        System.out.println("Connecting database...");

        Connection c = DriverManager.getConnection(url, username, password);
        return c;

    }
}
