package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.BoardDAO;
import control.BoardVO;
import control.User;
import view.BoardList2;

import javax.swing.*;

public class BoardDAOImpl implements BoardDAO {
    Connection conn;
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    CallableStatement csmt;

    // 게시글 등록 V
    @Override
    public int insert(BoardVO vo) {
        try {
            conn = DBConnector.getConnection();
            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);
            String sql = "insert into 게시글(게시글번호, 제목, 내용, 작성자ID, 비밀번호, 게시글작성날짜) values (SEQ_BOARD_NUM.NEXTVAL, ?, ?, ?, ?, ? )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3, User.getInfo().getId());
            pstmt.setString(4, User.getInfo().getPw());
            pstmt.setDate(5, sqlDate);

            int result = pstmt.executeUpdate();

            if (result > 0) {
            	JOptionPane.showMessageDialog(null, "게시글이 등록되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            } else {
            	JOptionPane.showMessageDialog(null, "게시글 등록에 실패했습니다.", "경고", JOptionPane.WARNING_MESSAGE);
            }
            DBConnector.releaseConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }         
        }
        return 0;
    }
    // 게시글 삭제
    // 비밀번호가 일치하면 게시글을 삭제 단 게시글에 답변이 달려있지 않아야 한다.(저장프로시저)
    @Override
    public int delete(BoardVO vo) {
        try {
            conn = DBConnector.getConnection();
            String sql = "SELECT 비밀번호 FROM 게시글 WHERE 게시글번호=" + vo.getNum();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String dbPassword = rs.getString("비밀번호");
                if (dbPassword.equals(vo.getPw())) {
                	String callSql = "{call DELETE_POST_WITH_REPLY(?)}"; 
                    csmt = conn.prepareCall(callSql);
                    csmt.setInt(1, vo.getNum());
                    csmt.execute();

                    JOptionPane.showMessageDialog(null, "글이 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                }
            }/* else {
            	JOptionPane.showMessageDialog(null, "해당하는 글이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }*/
            DBConnector.releaseConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public BoardVO search(BoardVO vo) {
        return null;
    }

    // 게시글 조회 V
    @Override
    public List<BoardVO> select() {
        List<BoardVO> list = new ArrayList<BoardVO>();

        try {
            conn = DBConnector.getConnection();
            String sql = "SELECT 게시글번호, 제목, 내용, 작성자ID, 비밀번호, 게시글작성날짜 FROM 게시글 ORDER BY 게시글번호 DESC";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setNum(rs.getInt("게시글번호"));
                vo.setTitle(rs.getString("제목"));
                vo.setContent(rs.getString("내용"));
                vo.setWriterId(rs.getString("작성자ID"));
                vo.setPw(rs.getString("비밀번호"));
                vo.setRegdate(rs.getDate("게시글작성날짜"));

                list.add(vo);
            }
            DBConnector.releaseConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void search(String search, String searchString) {
        List<BoardVO> list = new ArrayList<BoardVO>();

        try {
            conn = DBConnector.getConnection();
            String sql = "select 게시글번호, 제목, 내용, 작성자ID, 게시글작성날짜 from 게시글 where " + search + " like '%"
                    + searchString + "%' order by 게시글번호 desc";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setNum(rs.getInt("게시글번호"));
                vo.setTitle(rs.getString("제목"));
                vo.setContent(rs.getString("내용"));
                vo.setWriterId(rs.getString("작성자ID"));
                vo.setRegdate(rs.getDate("게시글작성날짜"));
                //vo.setPass(rs.getString("pass"));

                list.add(vo);

            }
            DBConnector.releaseConnection(conn);
            new BoardList2(list);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
