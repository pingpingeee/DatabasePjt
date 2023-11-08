package model;

import control.Join_LoginDAO;
import control.Join_LoginVO;
import view.BoardList;
import view.LoginScreen;
import view.JoinScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Join_LoginDAOImpl implements Join_LoginDAO {
    PreparedStatement pstmt;
    Connection conn;
    ResultSet rs;
    JoinScreen join_screen;
    LoginScreen main_login_screen;

    //impl에서 화면수정을 위한 생성자 오버로드
    public Join_LoginDAOImpl(JoinScreen join_screen){
        this.join_screen = join_screen;
    }
    public Join_LoginDAOImpl(LoginScreen main_login_screen){
        this.main_login_screen = main_login_screen;
    }
    @Override
    public boolean joinService(Join_LoginVO vo){

        try{
            conn = DBConnector.getConnection();

            String checkDuplicateQuery = "SELECT COUNT(*) FROM users WHERE account = ? OR nickname = ?";
            pstmt = conn.prepareStatement(checkDuplicateQuery);
            pstmt.setString(1, vo.getAccount());
            pstmt.setString(2, vo.getNickname());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    // 아이디 또는 닉네임이 이미 존재하는 경우
                    JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 또는 닉네임입니다.",
                            "알림", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            String sql = "INSERT INTO users (nickname, account, password) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getNickname());
            pstmt.setString(2, vo.getAccount());
            pstmt.setString(3, vo.getPassword());

            int result = pstmt.executeUpdate();
            if (result == 1) {
                //회원가입 성공
                JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.",
                        "알림", JOptionPane.INFORMATION_MESSAGE);
                join_screen.dispose();
                new LoginScreen();

                return true;
            } else {
                System.out.println("회원가입실패");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Exception확인");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) DBConnector.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SignUp_InImpl-finally실패");
            }
        }
    }

    @Override
    public boolean loginService(Join_LoginVO vo) {
        try {
            conn = DBConnector.getConnection();
            String sql = "SELECT * FROM users WHERE account = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getAccount());
            pstmt.setString(2, vo.getPassword());

            rs = pstmt.executeQuery();

            if (rs.next()) {

                main_login_screen.dispose();
                new BoardList();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "잘못된 아이디 또는 비밀번호입니다.",
                        "알림", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Exception 확인");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) DBConnector.releaseConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
