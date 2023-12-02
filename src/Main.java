
import java.util.List;

import control.AnswerVO;
import control.BoardVO;
import model.Answer;
import model.BoardDAOImpl;
import view.LoginScreen;

public class Main {
    public static void main(String[] args) {
/*        testInsertAnswer();
        testDeleteAnswer();
        testSelect();*/
        new LoginScreen();
    }
    private static void testInsertAnswer() {
        Answer answer = new Answer();
        AnswerVO answerVO = new AnswerVO();

        // 답변 정보 설정
        answerVO.setWriteNum(7); // 적절한 게시글 번호 설정
        answerVO.setDocterId("tsu23"); // 적절한 의사 ID 설정
        answerVO.setTitle("테스트 답변 제목");
        answerVO.setContent("테스트 답변 내용");

        // 답변 추가 테스트
        answer.insert(answerVO);
    }

    private static void testDeleteAnswer() {
        Answer answer = new Answer();
        AnswerVO answerVO = new AnswerVO();

        // 답변 정보 설정
        answerVO.setAnsNum(2); // 삭제할 답변 번호 설정

        // 답변 삭제 테스트
        answer.delete(answerVO);
    }
    public static void testSelect() {
        Answer answer = new Answer();
        List<AnswerVO> answerList = answer.selectAnswers(); // 적절한 게시글 번호 설정

        System.out.println("답변 목록:");
        for (AnswerVO vo : answerList) {
            System.out.println("답변 번호: " + vo.getAnsNum());
            System.out.println("게시글 번호: " + vo.getWriteNum());
            System.out.println("의사 ID: " + vo.getDocterId());
            System.out.println("제목: " + vo.getTitle());
            System.out.println("내용: " + vo.getContent());
            System.out.println("등록일자: " + vo.getRegDate());
            System.out.println("---------------------");
        }
    }
}
