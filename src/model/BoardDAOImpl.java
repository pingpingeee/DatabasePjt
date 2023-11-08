package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.BoardDAO;
import control.BoardVO;
import view.BoardList2;

import javax.swing.*;

public class BoardDAOImpl implements BoardDAO {

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    @Override
    public int insert(BoardVO vo) {
        try {
            conn = DBConnector.getConnection();
            String sql = "insert into board(num, title, content, writer, pw, regdate) values (SEQ_BOARD_NUM.NEXTVAL, ?, ?, ?, ?, SYSDATE )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3, vo.getName());
            pstmt.setString(4, vo.getPass());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int update(BoardVO vo) {
        try {
            conn = DBConnector.getConnection();
            String sql = "update board set title=?, content=?, writer=?, regdate=SYSDATE where num=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3, vo.getName());
            pstmt.setInt(4, vo.getNum());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int delete(BoardVO vo) {
        try {
            conn = DBConnector.getConnection();
            String getPasswordSQL = "select pw from board where num=?";
            pstmt = conn.prepareStatement(getPasswordSQL);
            pstmt.setInt(1, vo.getNum());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("pw");
                if (dbPassword.equals(vo.getPass())) {
                    String deleteSQL = "delete from board where num=?";
                    pstmt = conn.prepareStatement(deleteSQL);
                    pstmt.setInt(1, vo.getNum());
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "글이 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //System.out.println("비밀번호가 일치하지 않습니다.");
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                System.out.println("해당하는 글이 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                conn.close();
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

    @Override
    public List<BoardVO> select() {

        List<BoardVO> list = new ArrayList<BoardVO>();

        try {
            conn = DBConnector.getConnection();
            String sql = "select num, title, content, writer, regdate from board order by num desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setNum(rs.getInt("num"));
                vo.setTitle(rs.getString("title"));
                vo.setContent(rs.getString("content"));
                vo.setName(rs.getString("writer"));
                vo.setRegDate(rs.getDate("regdate"));

                list.add(vo);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                conn.close();
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
            String sql = "select num, title, content, writer, regdate from board where " + search + " like '%"
                    + searchString + "%' order by num desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setNum(rs.getInt("num"));
                vo.setTitle(rs.getString("title"));
                vo.setContent(rs.getString("content"));
                vo.setName(rs.getString("writer"));
                vo.setRegDate(rs.getDate("regdate"));
                //vo.setPass(rs.getString("pass"));

                list.add(vo);

            }

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
                pstmt.close();
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

    public Connection getConn() {
        return conn;
    }
}
