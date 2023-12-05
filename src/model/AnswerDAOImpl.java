package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.swing.JOptionPane;

import control.AnswerDAO;
import control.AnswerVO;
import control.UserManager;
import view.BoardList;

public class AnswerDAOImpl implements AnswerDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	CallableStatement csmt;
	ResultSet rs;

	//// 답변 추가
	// 답변 추가할때 의사만 답변을 달 수 있다.(트리거)
	public int insert(AnswerVO vo) {
		String sql = "INSERT INTO 답변 (답변번호, 게시글번호, 의사이름, 제목, 내용, 답변일자) VALUES (SEQ_ANSWER_NUM.NEXTVAL, ?, ?, ?, ?, ?)";

		try {
			conn = DBConnector.getConnection();
			pstmt = conn.prepareStatement(sql);
			LocalDate currentDate = LocalDate.now();
			Date sqlDate = Date.valueOf(currentDate);

			pstmt.setInt(1, BoardList.num);
			pstmt.setString(2, UserManager.getInfo().getId());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setDate(5, sqlDate);

			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "답변이 성공적으로 추가되었습니다.",
					"알림", JOptionPane.INFORMATION_MESSAGE);
			DBConnector.releaseConnection(conn);
		} catch (SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "의사만 답변을 작성할 수 있습니다.",
					"경고", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	// 답변 삭제
	public int delete(AnswerVO vo) {
		try {
			conn = DBConnector.getConnection();
			String sql = "DELETE FROM 답변 WHERE 답변번호 = " + vo.getAnsNum();
			stmt = conn.createStatement();

			int rs = stmt.executeUpdate(sql);

			if (rs > 0) {
				JOptionPane.showMessageDialog(null, "답변이 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "해당하는 답변이 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				csmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	//답변 조회
	public List<AnswerVO> selectAnswers() {
		List<AnswerVO> answerList = new ArrayList<AnswerVO>();
		try {
			conn = DBConnector.getConnection();
			String sql = "SELECT 답변번호, 게시글번호, 의사이름, 제목, 내용, 답변일자 FROM 답변 WHERE 게시글번호 = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardList.num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AnswerVO answerVO = new AnswerVO();
				answerVO.setAnsNum(rs.getInt("답변번호"));
				answerVO.setWriteNum(rs.getInt("게시글번호"));
				answerVO.setDocterId(rs.getString("의사이름"));
				answerVO.setTitle(rs.getString("제목"));
				answerVO.setContent(rs.getString("내용"));
				answerVO.setRegDate(rs.getDate("답변일자"));

				answerList.add(answerVO);
			}
			DBConnector.releaseConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return answerList;
	}
}
