package model;

import control.Join_LoginDAO;
import control.Join_LoginVO;
import control.UserManager;
import view.BoardList;
import view.JoinScreenDoctor;
import view.LoginScreen;
import view.JoinScreen;

import javax.swing.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class Join_LoginDAOImpl implements Join_LoginDAO {
    PreparedStatement pstmt;
    Connection conn;
    ResultSet rs;
    JoinScreen join_screen;
    LoginScreen main_login_screen;
    JoinScreenDoctor joinScreenDoctor;
    CallableStatement csmt;
    Statement stmt;
    UserManager userVO = null;

    public Join_LoginDAOImpl(JoinScreen join_screen) {
        this.join_screen = join_screen;
    }
    public Join_LoginDAOImpl(LoginScreen main_login_screen){
        this.main_login_screen = main_login_screen;
    }
    public Join_LoginDAOImpl(JoinScreenDoctor joinScreenDoctor) {
        this.joinScreenDoctor = joinScreenDoctor;
    }
    // 아이디 중복 체크
    // 중복체크 프로시저 호출
    // 회원가입 버튼 클릭 시가 아닌 중복체크 버튼에서 동작
    @Override
    public boolean duplicateCheck(Join_LoginVO vo){
        try{
            conn = DBConnector.getConnection();
            String sql = "{call check_duplicate_id(?, ?)}";
            csmt = conn.prepareCall(sql);
            csmt.setString(1, vo.getId());
            csmt.registerOutParameter(2, Types.NUMERIC);
            csmt.execute();
            int result = csmt.getInt(2);

            switch (result) {
                case 0:
                    JOptionPane.showMessageDialog(null, "아이디 사용가능.",
                            "알림", JOptionPane.INFORMATION_MESSAGE);
                    join_screen.getJoinButton().setEnabled(true);
                    joinScreenDoctor.getJoinButton().setEnabled(true);
                    DBConnector.releaseConnection(conn);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "아이디 중복.",
                            "알림", JOptionPane.WARNING_MESSAGE);
                    joinScreenDoctor.getJoinButton().setEnabled(false);
                    DBConnector.releaseConnection(conn);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "오류.",
                            "알림", JOptionPane.ERROR_MESSAGE);
            }
            DBConnector.releaseConnection(conn);
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                csmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    // 회원가입
    // 일반 회원가입과 의사 회원가입 기능이 따로 있어야 함
    // 일반 회원가입
    public boolean joinService(Join_LoginVO vo){
       try {
    	   conn = DBConnector.getConnection();
    	   java.util.Date utilDate = new java.util.Date();
    	   java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

           String sql = "INSERT INTO 회원 (ID, PW, 이름, 전화번호, 계정생성날짜, 사용자의사구분) VALUES (?, ?, ?, ?, ?, ?)";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, vo.getId());
           pstmt.setString(2, vo.getPw());
           pstmt.setString(3, vo.getName());
           pstmt.setString(4, vo.getPhnum());
           pstmt.setDate(5, sqlDate);
           pstmt.setInt(6, vo.getType());

           int result = pstmt.executeUpdate();
           if (result == 1) {
               JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.",
                       "알림", JOptionPane.INFORMATION_MESSAGE);
               join_screen.dispose();
               new LoginScreen();
           }
       } catch (SQLException e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "아이디를 중복확인해주세요.",
                   "알림", JOptionPane.WARNING_MESSAGE);
           join_screen.getJoinButton().setEnabled(false);
       } finally {
           try {
               DBConnector.releaseConnection(conn);
               pstmt.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return true;
   }
 // 의사 회원가입
    public boolean joinServiceDoctor(Join_LoginVO vo){
       try {
    	   conn = DBConnector.getConnection();
    	   java.util.Date utilDate = new java.util.Date();
    	   java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

           String sql = "INSERT INTO 의사 (ID, PW, 이름, 전화번호, 계정생성날짜, 사용자의사구분, 진료과) VALUES (?, ?, ?, ?, ?, ?, ?)";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, vo.getId());
           pstmt.setString(2, vo.getPw());
           pstmt.setString(3, vo.getName());
           pstmt.setString(4, vo.getPhnum());
           pstmt.setDate(5, sqlDate);
           pstmt.setInt(6, vo.getType());
           pstmt.setString(7, vo.getSpeciality());

           int result = pstmt.executeUpdate();
           if (result == 1) {
               JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.",
                       "알림", JOptionPane.INFORMATION_MESSAGE);
               joinScreenDoctor.dispose();
               new LoginScreen();
           }
       } catch (SQLException e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.",
                   "알림", JOptionPane.WARNING_MESSAGE);
           joinScreenDoctor.getJoinButton().setEnabled(false);
       } finally {
           try {
               DBConnector.releaseConnection(conn);
               pstmt.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return true;
   }
    // 로그인
    public boolean loginService(Join_LoginVO vo) {
        try {
            conn = DBConnector.getConnection();
            // 회원 테이블에서 로그인 시도
            String usersql = "SELECT * FROM 회원 WHERE ID = ? AND PW = ?";
            try (PreparedStatement usersStmt = conn.prepareStatement(usersql)) {
                usersStmt.setString(1, vo.getId());
                usersStmt.setString(2, vo.getPw());
                ResultSet usersRs = usersStmt.executeQuery();

                if (usersRs.next()) {
                	try {
                        UserManager.getInfo().setId(usersRs.getString("ID"));
                        UserManager.getInfo().setPw(usersRs.getString("PW"));
                        UserManager.getInfo().setName(usersRs.getString("이름"));
                        UserManager.getInfo().setPhnum(usersRs.getString("전화번호"));
                        JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다.",
                                "알림", JOptionPane.INFORMATION_MESSAGE);
                        main_login_screen.dispose();
                        DBConnector.releaseConnection(conn);
                        new BoardList();
                        return true;
                    } finally {
                        conn.close(); // 성공 시에도 리소스를 닫아주도록 변경
                    }
                }
            }
            // 의사 테이블에서 로그인 시도
            String doctorsql = "SELECT * FROM 의사 WHERE ID = ? AND PW = ?";
            try (PreparedStatement doctorUsersStmt = conn.prepareStatement(doctorsql)) {
                doctorUsersStmt.setString(1, vo.getId());
                doctorUsersStmt.setString(2, vo.getPw());
                ResultSet doctorUsersRs = doctorUsersStmt.executeQuery();

                if (doctorUsersRs.next()) {
                	try {
                        JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다.",
                                "알림", JOptionPane.INFORMATION_MESSAGE);
                        main_login_screen.dispose();
                        new BoardList();
                        return true;
                    } finally {
                        conn.close(); // 성공 시에도 리소스를 닫아주도록 변경
                    }
                }
            }
            // 로그인 실패
            JOptionPane.showMessageDialog(null, "잘못된 아이디 또는 비밀번호입니다.", "알림", JOptionPane.WARNING_MESSAGE);
            DBConnector.releaseConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnector.releaseConnection(conn);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
