package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database_conn {
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";;
    private static String DB_URL = "jdbc:mysql://localhost:3306/DE_Store";
    private static String USER = "root";
    private static String PASS = "Lyf19980311";

    public static Connection getCon() throws Exception{
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }


    public static void close(Connection conn) throws SQLException{
        if(conn!=null){
            conn.close();
        }
    }

    public static void main(String[] args) {
        try {
            getCon();
            System.out.println("Database connect success!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Database connect fail!");
        }
    }

    public static void close(Connection conn, ResultSet rs) {
    }
}
