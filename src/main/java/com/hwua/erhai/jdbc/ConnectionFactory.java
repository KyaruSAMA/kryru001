package com.hwua.erhai.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    //在类被加载时执行,只会执行一次
    static {
        //从配置文件读取键值对
        //存储键值对--集合
        Properties prop = new Properties();
        try {
            //把properties文件中的数据加载到prop对象中
            //要求:ConnectionFactory类和jdbc.properties在同一个包下
            prop.load(ConnectionFactory.class.getClassLoader()
                    .getResourceAsStream("jdbc.properties"));
            //value=getProperty(key):通过键获取值
            DRIVER = prop.getProperty("driver");
            URL = prop.getProperty("url");
            USERNAME = prop.getProperty("username");
            PASSWORD = prop.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("failed to load jdbc configuration", e);
        }
    }

    public static Connection getConnection() {
        Connection conn;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("failed to getConnection", e);
        }
        return conn;
    }

    public static void main(String[] args) {
        System.out.println(ConnectionFactory.getConnection());
    }
}
