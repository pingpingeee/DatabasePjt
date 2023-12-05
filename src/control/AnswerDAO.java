package control;

import java.util.List;

public interface AnswerDAO {
	public int insert(AnswerVO vo);
	public int delete(AnswerVO vo);
	public List<AnswerVO> selectAnswers();
	public void profile(String doctorId);
}
