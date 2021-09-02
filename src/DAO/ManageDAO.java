package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.ManageDTO;

public class ManageDAO {
	
	Connection con;

	// 생성자
	public ManageDAO() throws Exception {
		connectDB();
	}
	// DB 연결
	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}
	//패키지신청내역 삭제하는 메소드
	public void deleteRV(ManageDTO vo) throws Exception {

		String sql = "delete from manage where tripnum = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, String.valueOf(vo.getTripNum()));
		//PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		// ps.setString(1, x);
	}
	
	//패키지번호를 수정하는 메소드
	public ManageDTO updateRV(ManageDTO vo, int row) throws Exception {
		String sql = "update manage set (tripnum, tripname) = (select tripnum, tripname from trip where tripnum = ?) where tripnum = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ManageDTO vm = new ManageDTO();
		
		ps.setInt(1, vo.getTripNum());
		ps.setInt(2, row);

		ps.executeUpdate();
		ps.close();
		
		return vm;
	}
	// PK값으로 비디오 검색하는 메소드
	public ManageDTO selectByPk(int vNum) throws Exception {

		ManageDTO vo = new ManageDTO();
		String sql = "SELECT * FROM manage WHERE tripnum = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, vNum);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			vo.setId(rs.getString("id"));
			vo.setCname(rs.getString("cname"));
			vo.setTripNum(rs.getInt("tripnum"));
			vo.setTripName(rs.getString("tripname"));
			vo.setTripRv(rs.getString("triprv"));
		}

		rs.close();
		stmt.close();

		return vo;
	}

//	//패키지 검색하는 메소드
	public ArrayList selectTripRV(int sel, String text) throws Exception{
				
		String[] selCol = {"id","tripname"};
		String sql = "select id, cname, tripnum, tripname, triprv from manage " 
					+ "where " + selCol[sel] + " like '%" + text + "%'";
		
		PreparedStatement ps = con.prepareStatement(sql);
		//ps.setString(1, selCol[sel]);
		//ps.setString(1, "%"+text+"%");
		
		ResultSet rs = ps.executeQuery();
		ArrayList list = new ArrayList();
				
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("id"));
			temp.add(rs.getString("cname"));
			temp.add(rs.getInt("Tripnum"));
			temp.add(rs.getString("tripname"));
			temp.add(rs.getString("triprv"));
			list.add(temp);
		}
		rs.close();
		ps.close();
		
		return list;


	}
}
