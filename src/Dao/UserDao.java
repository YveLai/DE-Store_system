package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;

public class UserDao {

    public User login(Connection conn, User user)throws Exception{
        User resultUser = null;
        String sql = "select * from Login where username=? and password=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            resultUser = new User();
            resultUser.setUser_id(rs.getInt("user_id"));
            resultUser.setUsername(rs.getString("username"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }

    public static int Sign_up(Connection conn, User user) throws SQLException {
        String sql = "insert into Login values(null, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        return pstmt.executeUpdate();
    }

}
