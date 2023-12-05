package control;

import java.util.Date;

public class UserManager {
    private static UserManager user;
    String id;          // ID
    String pw;          // PW
    String name;        // 이름
    String phnum;       // 전화번호
    Date regDate;       // 계정생성날짜
    int type;           // 사용자의사구분

    public static UserManager getInfo() {
        if (user == null) {
            user = new UserManager();
        }
        return user;
    }

    private UserManager() {

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhnum() {
        return phnum;
    }
    public void setPhnum(String phnum) {
        this.phnum = phnum;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
