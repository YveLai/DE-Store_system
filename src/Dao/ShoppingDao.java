package Dao;


import Controller.database_conn;
import Model.Shoppingrecords;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingDao {
    private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;

    public List<Shoppingrecords> queryAllUser(){
        String sql="select * from Shoppingrecords";
        List<Shoppingrecords> list=new ArrayList<Shoppingrecords>();
        try {
            conn = database_conn.getCon();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            System.out.println(ps.toString());
            while(rs.next()){
                Shoppingrecords shoppingrecords=new Shoppingrecords();
                shoppingrecords.setId(rs.getInt(1));
                shoppingrecords.setPayer(rs.getString(2));
                shoppingrecords.setGender(rs.getString(3));
                shoppingrecords.setDOB(rs.getString(4));
                shoppingrecords.setProduct(rs.getString(5));
                shoppingrecords.setPrice(rs.getString(6));
                shoppingrecords.setEnabling(rs.getString(7));


                list.add(shoppingrecords);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}


