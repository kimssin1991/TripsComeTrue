

import java.util.Date;

public class JoinDTO {
	String id;
	String pw;
	String name;
	String jumin;
	String tel;
	String addr;
	Date registerdate;
	
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
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getReigsterdate() {
		return registerdate;
	}
	public void setReigsterdate(Date registerdate) {
		this.registerdate = registerdate;
	}
	boolean isFull() {
		if(id == "" || pw == "" || name == "" || jumin.length() != 14
				|| addr == "" || tel == "") return false;
		return true;
	}
}
