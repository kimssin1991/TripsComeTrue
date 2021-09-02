package DTO;

import java.util.Date;

public class MyPageDTO {
	String id;
	String pw;
	String pw2;
	String name;
	String jumin;
	String tel;
	String addr;
	String registerdate;
	String point;
	String rank;

	
	
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
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
	public String getPw2() {
		return pw2;
	}
	public void setPw2(String pw2) {
		this.pw2 = pw2;
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
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	@Override
	public String toString() {
		return "MyPageDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", jumin=" + jumin + ", tel=" + tel + ", addr="
				+ addr + ", registerdate=" + registerdate + "]";
	
	
	
	
	//boolean isFull() {
	//	if(id == "" || pw == "" || name == "" || jumin.length() != 14
	//			|| addr == "" || tel == "") return false;
		//return true;
}}

