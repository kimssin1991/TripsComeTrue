package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DTO.ClientDTO;
import DTO.TripDTO;

public class ConfirmDAO {
	Connection con;
	String User;

	public ConfirmDAO(String UserID) throws Exception {
		connectDB();
		User = UserID;
	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	PreparedStatement ps = null;
	ResultSet rs = null;
	Statement st = null;
	DecimalFormat format = new DecimalFormat("###,###");
	ClientDTO dto = new ClientDTO();
	TripDTO mdto = new TripDTO();

	public void tableCellCenter(JTable t) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = t.getColumnModel();
		tcm.getColumn(2).setCellRenderer(dtcr);
	}
	
	public void tableCellRight(JTable t) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm = t.getColumnModel();
		tcm.getColumn(1).setCellRenderer(dtcr);
	}
	
	public void tableCellCenter2(JTable t) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = t.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
	}


	public void apply(DefaultTableModel model) {
		try {
			String sql = "select t.tripname, t.tripstartdate, t.trippay from trip t, manage m where t.tripname = m.tripname and m.id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, User);
			rs = ps.executeQuery();
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				mdto.setTripName(rs.getString("tripname"));
				mdto.setTripStartDate(rs.getString("tripstartdate"));
				mdto.setTripPay(format.format(Integer.parseInt(rs.getString("TRIPPAY"))) + "원");
				Object data[] = { mdto.getTripName(), mdto.getTripStartDate(),mdto.getTripPay()};
				model.addRow(data);
			}
		} catch (Exception e) {
			System.out.println("select() 실행 오류 : " + e);
		} finally {
			dbClose();
		}
	}

	public void delete(String data) {
		try {
			String sql = "delete from manage where id = ? and tripname like '%" + data + "%'";

			ps = con.prepareStatement(sql);
			ps.setString(1, User);
			System.out.println(data);
			System.out.println(User);
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("DB 추가 실패" + e);
		} finally {
			dbClose();
		}
	}
	
	public TripDTO selectByPk(String vNum) throws Exception {
		
		TripDTO vo = new TripDTO();
		String sql = "SELECT tripinfo, tripspot FROM trip WHERE tripname = '" + vNum + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			vo.setTripInfo(rs.getString("tripinfo"));
			vo.setTripSpot(rs.getString("tripspot"));
		}
		rs.close();
		stmt.close();

		return vo;
	}

	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}
}