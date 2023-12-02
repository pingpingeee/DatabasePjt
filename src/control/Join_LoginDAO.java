package control;

public interface Join_LoginDAO {
    public boolean joinService(Join_LoginVO vo);
    public boolean loginService(Join_LoginVO vo);
	boolean duplicateCheck(Join_LoginVO vo);
}
