package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import DTO.TripDTO;

public class TripDAO {
	Connection con;
	DecimalFormat format = new DecimalFormat("###,###");
	// 생성자
	public TripDAO() throws Exception {
		connectDB();
	}
	// DB 연결
	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}
	
	// 비디오 테이블에 입력하는 메소드
	public void insertTrip(TripDTO vo) throws Exception {
		String sql = "INSERT INTO Trip (tripnum, tripname, trippay, tripdays, tripstartdate, tripfinishdate, tripinfo, tripspot) "
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, String.valueOf(vo.getTripNum()));
		ps.setString(2, vo.getTripName());
		ps.setString(3, vo.getTripPay());
		ps.setString(4, vo.getTripDays());
		ps.setString(5, vo.getTripStartDate());
		ps.setString(6, vo.getTripFinishDate());
		ps.setString(7, vo.getTripInfo());
		ps.setString(8, vo.getTripSpot());

		ps.executeUpdate();
		ps.close();
	}

	public void deleteTrip(TripDTO vo) throws Exception {
		
		String sql = "delete from trip where tripnum = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, String.valueOf(vo.getTripNum()));
		
		ps.executeUpdate();
		ps.close();
	}
	//비디오 수정하는 메소드
	public void updateTrip(TripDTO vo) throws Exception{
		String sql = "update Trip set tripnum = ?, tripname = ?, trippay = ?, tripdays = ?, tripstartdate = ?, "
				+ "tripfinishdate = ?, tripinfo = ?, tripspot = ?"
				+ " where tripnum = " + vo.getTripNum();

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, String.valueOf(vo.getTripNum()));
		ps.setString(2, vo.getTripName());
		ps.setString(3, vo.getTripPay());
		ps.setString(4, vo.getTripDays());
		ps.setString(5, vo.getTripStartDate());
		ps.setString(6, vo.getTripFinishDate());
		ps.setString(7, vo.getTripInfo());
		ps.setString(8, vo.getTripSpot());

		ps.executeUpdate();
		ps.close();
	}

	public TripDTO selectByPk(int vNum) throws Exception {
		
		TripDTO vo = new TripDTO();
		String sql = "SELECT * FROM trip WHERE tripnum = " + vNum;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			vo.setTripNum(rs.getInt("tripNUM"));
			vo.setTripName(rs.getString("tripname"));
			vo.setTripPay(format.format(Integer.parseInt(rs.getString("TRIPPAY"))) + "원");
			vo.setTripDays(rs.getString("tripdays"));
			vo.setTripStartDate(rs.getString("tripstartdate"));
			vo.setTripFinishDate(rs.getString("tripfinishdate"));
			vo.setTripInfo(rs.getString("tripinfo"));
			vo.setTripSpot(rs.getString("tripspot"));
		}
		rs.close();
		stmt.close();

		return vo;
	}

//	//비디오 검색하는 메소드
	public ArrayList selectTrip(int sel, String text) throws Exception {
		// 3. sql 문장 만들기
		String[] selCol = { "tripname", "tripnum", "tripstartdate" };
		String sql = "select tripnum, tripname, trippay, tripdays, tripstartdate, tripfinishdate from trip " + "where "
				+ selCol[sel] + " like '%" + text + "%'";

		// 4. 전송객체 얻어오기
		PreparedStatement ps = con.prepareStatement(sql);

		// 5. 전송하기
		ResultSet rs = ps.executeQuery();
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("Tripnum"));
			temp.add(rs.getString("tripname"));
			temp.add(format.format(Integer.parseInt(rs.getString("TRIPPAY"))) + "원");
			temp.add(rs.getString("tripdays"));
			temp.add(rs.getString("tripstartdate"));
			temp.add(rs.getString("tripfinishdate"));
			list.add(temp);
		}
		rs.close();
		ps.close();

		return list;
	}
}
