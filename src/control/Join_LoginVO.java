package control;

import java.sql.Date;

public class Join_LoginVO {
    String id;
    String pw;
    String name;
    String phnum;
    int type;
    String speciality;

    public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
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
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}
