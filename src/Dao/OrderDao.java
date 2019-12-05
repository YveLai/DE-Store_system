package Dao;

import Controller.StringUtil;
import Model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao {
    public static int addorder(Connection conn, Order order) throws SQLException {
        String sql = "insert into Ordertb values(null, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, order.getOrder_name());
        pstmt.setString(2, order.getOrder_num());
        pstmt.setString(3, order.getOrder_time());
        pstmt.setString(4,order.getAddress());
        pstmt.setString(5,order.getPhone());
        return pstmt.executeUpdate();
    }

    public static ResultSet listproduct(Connection conn, Order order) throws Exception{
        StringBuffer sb = new StringBuffer("select * from Ordertb");
        if(StringUtil.isNotEmpty(order.getOrder_name())){
            sb.append(" and order_name like '%"+order.getOrder_name()+"%' ");
        }
        PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }
}
