package control;

import java.util.Date;

public class AnswerVO {
	int ansNum;
	int writeNum;
	String docterId;
	String doctorPw;


	String title;
	String content;
	Date regDate;
	public String getDoctorPw() {
		return doctorPw;
	}

	public void setDoctorPw(String doctorPw) {
		this.doctorPw = doctorPw;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getAnsNum() {
		return ansNum;
	}
	public void setAnsNum(int ansNum) {
		this.ansNum = ansNum;
	}
	public int getWriteNum() {
		return writeNum;
	}
	public void setWriteNum(int writeNum) {
		this.writeNum = writeNum;
	}
	public String getDocterId() {
		return docterId;
	}
	public void setDocterId(String docterId) {
		this.docterId = docterId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
