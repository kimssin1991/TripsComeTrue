package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DTO.MyPageDTO;
import Main.UpdatePop;

public class MyPageDAO {

	Connection con;
	ResultSet rs;
	String User;

	public MyPageDAO() throws Exception {
		connectDB();

	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	public MyPageDTO showMyInfo(String User) {
		MyPageDTO dto = new MyPageDTO();
		String sql = "SELECT * FROM client WHERE ID = ?";
		

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, User);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
		 		dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegisterdate(rs.getString("registerdate"));
				dto.setPoint(rs.getString("point"));
				dto.setRank(rs.getString("rank"));
				
			
			}
		rs.close();
		ps.close();
		} catch (Exception e) {
			System.out.println("회원 정보를 불러올 수 없습니다.");
			e.printStackTrace();
		}
		return dto;
	}

	public void deleteProcess(MyPageDTO dto) {
		try {
			String sql = "Delete from Client where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "탈퇴가 완료되었습니다.");
			System.exit(0);
			ps.close();
		 }catch(Exception e) {
			 e.printStackTrace();
			 }
	}

	public void updateProcess(MyPageDTO dto) {
		try {
			String sql = "UPDATE client SET pw= ?, name = ?, tel = ?, addr = ? WHERE ID = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getTel());
			ps.setString(4, dto.getAddr());
			ps.setString(5, dto.getId());
			
			//int count=
			ps.executeUpdate();
			System.out.println("정보수정 성공!");
			
			ps.close();
		}catch(Exception e) {
			 e.printStackTrace();
		
			JOptionPane.showMessageDialog(null, "수정을 완료하였습니다.");
		}
		
	}
	
	public String pwCheck() throws SQLException {
		MyPageDTO dto = new MyPageDTO();
		String pass = null;
		String sql = "select pw from client where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getId());
		rs = ps.executeQuery();
		
		while(rs.next()) {
			pass = rs.getString("pw");
		}
		return pass;
	}


	
}

