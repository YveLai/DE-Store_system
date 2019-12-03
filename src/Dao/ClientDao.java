package Dao;

import Controller.StringUtil;
import Model.Client;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao {
    public static int AddClient(Connection conn, Client client) throws SQLException {
        String sql = "insert into Client values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, client.getClient_name());
        pstmt.setString(2, client.getDOB());
        pstmt.setString(3, client.getGender());
        pstmt.setString(4,client.getPhone_num());
        pstmt.setString(5,client.getLine1());
        pstmt.setString(6,client.getLine2());
        pstmt.setString(7,client.getTown());
        pstmt.setString(8,client.getPostcode());
        pstmt.setString(9,client.getLoyalty_card());
        return pstmt.executeUpdate();
    }

    public static int UpdataMember(Connection conn, Client client) throws SQLException{
        String sql = "update Client set client_name=?, DOB=?, gender=?, phone_num=?, " +
                "line1=?, line2=?, town, postcode, loyaoty_card where product_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, client.getClient_name());
        pstmt.setString(2, client.getDOB());
        pstmt.setString(3, client.getGender());
        pstmt.setString(4,client.getPhone_num());
        pstmt.setString(5, client.getLine1());
        pstmt.setString(6, client.getLine2());
        pstmt.setString(7, client.getTown());
        pstmt.setString(8, client.getPostcode());
        pstmt.setString(9, client.getLoyalty_card());
        pstmt.setInt(10, client.getClient_id());
        return pstmt.executeUpdate();
    }

    public static int DeleteMember(Connection conn, Client client) throws SQLException{
        String sql = "delete from Client where client_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, client.getClient_id());
        return  pstmt.executeUpdate();
    }

    public static ResultSet listproduct(Connection conn, Client client) throws Exception{
        StringBuffer sb = new StringBuffer("select * from Client");
        if(StringUtil.isNotEmpty(client.getClient_name())){
            sb.append(" and client_name like '%"+client.getClient_name()+"%' ");
        }
        PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }
}
