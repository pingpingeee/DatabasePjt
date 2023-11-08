package model;

import control.SignUp_InVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp_InImpl {
    BoardDAOImpl con;
    Connection conn;
    PreparedStatement pstmt;

    ResultSet rs;
    public SignUp_InImpl(){
        con = new BoardDAOImpl();
    }

    public boolean joinService(SignUp_InVO vo){
        Connection conn;
        try{
            Class.forName(con.DRIVER_NAME);
            conn = con.getConn();
            String sql = "INSERT INTO users (nickname, account, password) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getNickname());
            pstmt.setString(2, vo.getAccount());
            pstmt.setString(3, vo.getPassword());

            int result = pstmt.executeUpdate();
            if (result == 1) {
                //회원가입 성공
                System.out.println("회원가입완료");
                return true;
            }else{
                return false;
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
