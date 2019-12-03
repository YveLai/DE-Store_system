package Dao;

import Controller.StringUtil;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {
    /**
     * delete the product
     * @param conn
     * @param product
     * @return
     * @throws SQLException
     */
    public static int deleteproduct(Connection conn, Product product) throws SQLException{
        String sql = "delete from Product where product_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, product.getProduct_id());
        return  pstmt.executeUpdate();
    }
    /**
     * update the product
     * @param conn
     * @param product
     * @return
     * @throws SQLException
     */
    public static int updateproduct(Connection conn, Product product) throws SQLException{
        String sql = "update Product set product_name=?, product_price=?, product_num=?, notes=? where product_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, product.getProduct_name());
        pstmt.setString(2, product.getProduct_price());
        pstmt.setString(3, product.getProduct_num());
        pstmt.setString(4,product.getNotes());
        pstmt.setInt(5, product.getProduct_id());
        return pstmt.executeUpdate();
    }
    /**
     * Query the product
     * @param conn
     * @param product
     * @return
     * @throws Exception
     */
    public static ResultSet listproduct(Connection conn, Product product) throws Exception{
        StringBuffer sb = new StringBuffer("select * from Product");
        if(StringUtil.isNotEmpty(product.getProduct_name())){
            sb.append(" and product_name like '%"+product.getProduct_name()+"%' ");
        }
        PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    /**
     * add the product
     * @param conn
     * @param product
     * @return
     * @throws SQLException
     */
    public static int addproduct(Connection conn, Product product) throws SQLException{
        String sql = "insert into Product values(null, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, product.getProduct_name());
        pstmt.setString(2, product.getProduct_price());
        pstmt.setString(3, product.getProduct_num());
        pstmt.setString(4,product.getNotes());
        return pstmt.executeUpdate();
    }
}
