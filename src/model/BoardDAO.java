package control;
import java.util.List;


public interface BoardDAO {
    public int insert(BoardVO vo);
    public int delete(BoardVO vo);

    BoardVO search(BoardVO vo);

    public List<BoardVO> select();
    public void search(String search, String searchString);
}
