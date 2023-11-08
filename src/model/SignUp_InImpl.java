package model;

import com.sun.tools.javac.Main;
import control.SignUp_InVO;
import view.BoardList;
import view.Main_SignIn_Screen;
import view.SignUp_Screen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp_InImpl {
    PreparedStatement pstmt;
    Connection conn;
    ResultSet rs;
    SignUp_Screen signUp_screen;
    Main_SignIn_Screen main_signIn_screen;

    public SignUp_InImpl(SignUp_Screen signUp_screen){
        this.signUp_screen = signUp_screen;
    }
    public  SignUp_InImpl(Main_SignIn_Screen main_signIn_screen){
        this.main_signIn_screen = main_signIn_screen;
    }
    public boolean joinService(SignUp_InVO vo){

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
                signUp_screen.dispose();
                new Main_SignIn_Screen();

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

    public boolean loginService(SignUp_InVO vo) {
        try {
            conn = DBConnector.getConnection();
            String sql = "SELECT * FROM users WHERE account = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getAccount());
            pstmt.setString(2, vo.getPassword());

            rs = pstmt.executeQuery();

            if (rs.next()) {

                main_signIn_screen.dispose();
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
