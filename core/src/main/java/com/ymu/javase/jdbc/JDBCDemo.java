package com.ymu.javase.jdbc;

import com.ymu.javase.jdbc.ofice.JDBCTutorialUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo {

    private final static String url = "jdbc:mysql://192.168.0.106:3910/coffees_house";
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private final static String userName = "root";
    private final static String pwd = "123456";

    /**
     * 加载驱动
     */
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection con = getConnection();
        //JDBCTutorialUtilities.createDatabase(con, "coffees_house", "mysql"); //创建库
        //JDBCTutorialUtilities.initializeTables(con, "coffees_house", "mysql"); //创建表

        JDBCTutorialUtilities.rowIdLifetime(con);
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    /**
     * 建立数据库连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, userName, pwd);
            return conn;
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }

        return null;
    }
}
