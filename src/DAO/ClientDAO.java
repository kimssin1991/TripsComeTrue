package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DTO.ClientDTO;
import DTO.ManageDTO;

public class ClientDAO {
	Connection con;
	String User;
	String rank;

	public ClientDAO(String UserID) throws Exception {
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
	ManageDTO mdto = new ManageDTO();

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

	public void select(DefaultTableModel model) {
		try {
			String sql = "select TRIPNAME, TRIPPAY, TRIPDAYS, TRIPSTARTDATE, TRIPFINISHDATE, TRIPSPOT, TRIPINFO from trip";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
//				dto.setNum((rs.getInt("TRIPNUM")));
				dto.setName(rs.getString("TRIPNAME"));
				dto.setPay(format.format(Integer.parseInt(rs.getString("TRIPPAY"))) + " 원 ");
				dto.setDays(rs.getString("TRIPDAYS"));
				dto.setStartdate(rs.getString("TRIPSTARTDATE"));
				dto.setFinishdate(rs.getString("TRIPFINISHDATE"));
				dto.setSpot(rs.getString("TRIPSPOT"));
				dto.setInfo(rs.getString("TRIPINFO"));
				Object data[] = { dto.getName(), dto.getPay(), dto.getDays(), dto.getStartdate(), dto.getFinishdate(),
						dto.getSpot(), dto.getInfo() };
				model.addRow(data);
			}
		} catch (Exception e) {
			System.out.println("select() 실행 오류 : " + e);
		} finally {
			dbClose();
		}
	}

	public void radioDesc(DefaultTableModel model) {
		try {
			String sql = "select TRIPNAME, TRIPPAY, TRIPDAYS, TRIPSTARTDATE, TRIPFINISHDATE, TRIPSPOT, TRIPINFO from trip order by trippay desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
//				dto.setNum(rs.getInt("TRIPNUM"));
				dto.setName(rs.getString("TRIPNAME"));
				dto.setPay(format.format(Integer.parseInt(rs.getString("TRIPPAY"))) + "원");
				dto.setDays(rs.getString("TRIPDAYS"));
				dto.setStartdate(rs.getString("TRIPSTARTDATE"));
				dto.setFinishdate(rs.getString("TRIPFINISHDATE"));
				dto.setSpot(rs.getString("TRIPSPOT"));
				dto.setInfo(rs.getString("TRIPINFO"));
				Object data[] = { dto.getName(), dto.getPay(), dto.getDays(), dto.getStartdate(), dto.getFinishdate(),
						dto.getSpot(), dto.getInfo() };
				model.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> 실패");
		} finally {
			dbClose();
		}
	}
	
	public boolean nameCheck(String data) {
        boolean result = false;
        try {
           String sql = "select * from manage where tripname = ? and id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.setString(2, User);
            rs = ps.executeQuery();
            if (rs.next())
                result = true; 
        } catch (SQLException e) {
            System.out.println(e + "=>  getIdByCheck fail");
        } finally {
            dbClose();
        }
        return result;
    }

	public void radioAsc(DefaultTableModel model) {
		try {
			String sql = "select TRIPNAME, TRIPPAY, TRIPDAYS, TRIPSTARTDATE, TRIPFINISHDATE, TRIPSPOT, TRIPINFO from trip order by trippay asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
//				dto.setNum(rs.getInt("TRIPNUM"));
				dto.setName(rs.getString("TRIPNAME"));
				dto.setPay(format.format(Integer.parseInt(rs.getString("TRIPPAY"))) + "원");
				dto.setDays(rs.getString("TRIPDAYS"));
				dto.setStartdate(rs.getString("TRIPSTARTDATE"));
				dto.setFinishdate(rs.getString("TRIPFINISHDATE"));
				dto.setSpot(rs.getString("TRIPSPOT"));
				dto.setInfo(rs.getString("TRIPINFO"));
				Object data[] = { dto.getName(), dto.getPay(), dto.getDays(), dto.getStartdate(), dto.getFinishdate(),
						dto.getSpot(), dto.getInfo() };
				model.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> 실패");
		} finally {
			dbClose();
		}
	}
	
	public void startDate(DefaultTableModel model) {
		try {
			String sql = "select TRIPNAME, TRIPPAY, TRIPDAYS, TRIPSTARTDATE, TRIPFINISHDATE, TRIPSPOT, TRIPINFO from trip order by TRIPSTARTDATE asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
//				dto.setNum(rs.getInt("TRIPNUM"));
				dto.setName(rs.getString("TRIPNAME"));
				dto.setPay(format.format(Integer.parseInt(rs.getString("TRIPPAY"))) + "원");
				dto.setDays(rs.getString("TRIPDAYS"));
				dto.setStartdate(rs.getString("TRIPSTARTDATE"));
				dto.setFinishdate(rs.getString("TRIPFINISHDATE"));
				dto.setSpot(rs.getString("TRIPSPOT"));
				dto.setInfo(rs.getString("TRIPINFO"));
				Object data[] = { dto.getName(), dto.getPay(), dto.getDays(), dto.getStartdate(), dto.getFinishdate(),
						dto.getSpot(), dto.getInfo() };
				model.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> 실패");
		} finally {
			dbClose();
		}
	}

	public void getUserSearch(DefaultTableModel model, String tripname) {
		try {
			String sql = "SELECT TRIPNAME, TRIPPAY, TRIPDAYS, TRIPSTARTDATE, TRIPFINISHDATE, TRIPSPOT, TRIPINFO FROM trip WHERE TRIPNAME LIKE upper ('%"
					+ tripname.trim() + "%')";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
//				dto.setNum(rs.getInt("TRIPNUM"));
				dto.setName(rs.getString("TRIPNAME"));
				dto.setPay(format.format(Integer.parseInt(rs.getString("TRIPPAY"))) + "원");
				dto.setDays(rs.getString("TRIPDAYS"));
				dto.setStartdate(rs.getString("TRIPSTARTDATE"));
				dto.setFinishdate(rs.getString("TRIPFINISHDATE"));
				dto.setSpot(rs.getString("TRIPSPOT"));
				dto.setInfo(rs.getString("TRIPINFO"));
				Object data[] = { dto.getName(), dto.getPay(), dto.getDays(), dto.getStartdate(), dto.getFinishdate(),
						dto.getSpot(), dto.getInfo() };
				model.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "=> 실패");
		} finally {
			dbClose();
		}
	}

	public void book(String data) {
		try {
			String sql = "insert into manage (id, cname, tripnum, tripname)"
					+ " select c.id, c.name, t.tripnum, t.tripname from client c, trip t where c.id = ?"
					+ " and t.tripname like '%" + data + "%'";

			ps = con.prepareStatement(sql);

			ps.setString(1, User);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePoint(String data, String UserID) throws SQLException {
		String a = "Junior";
		String b = "Senior";
		String c = "VIP";
		try {
		String sql1 = "select rank from client where id = '" + UserID + "'";
		ps = con.prepareStatement(sql1);
		rs = ps.executeQuery();
		while(rs.next()) {
			rank = rs.getString("rank");
		}	
		System.out.println(rank);
		if(rank == "") {
			
		}else {
			System.out.println(0);
		if(rank.equals(a)) {
			System.out.println(1);
			String sql2 = "update client c set c.point = "
					+ "c.point + (trunc((select trippay from trip where tripname = ?) * 0.01))"
					+ "where rank = 'Junior' and id = ?";
			
			ps = con.prepareStatement(sql2);
			
			ps.setString(1, data);
			ps.setString(2, UserID);
//			ps.setString(3, UserID);
			ps.executeUpdate();
		}else if(rank.equals(b)) {
			System.out.println(2);
			String sql2 = "update client c set c.point = "
					+ "c.point + (trunc((select trippay from trip where tripname = ?) * 0.03))"
					+ "where rank = (select rank from client where id = ?) and id = ?";
			
			ps = con.prepareStatement(sql2);
			
			ps.setString(1, data);
			ps.setString(2, UserID);
			ps.setString(3, UserID);
			ps.executeUpdate();
		}else if(rank.equals(c)) {
			System.out.println(3);
			String sql2 = "update client c set c.point = "
					+ "c.point + (trunc((select trippay from trip where tripname = ?) * 0.05))"
					+ "where rank = (select rank from client where id = ?) and id = ?";
			
			ps = con.prepareStatement(sql2);
			
			ps.setString(1, data);
			ps.setString(2, UserID);
			ps.setString(3, UserID);
			ps.executeUpdate();
		}
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}
	
	public void updateRank(String UserID) {
		int r = 0;
		try {
			String sql = "select id from manage where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, UserID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				r = rs.getRow();
			}
			if(r > 9) {
				String sql1 = "update client set rank = 'VIP' where id = ?";
				ps = con.prepareStatement(sql1);
				
				ps.setString(1, UserID);
				ps.executeUpdate();
			}else if(r > 4 && r < 10) {
				String sql1 = "update client set rank = 'Senior' where id = ?";
				ps = con.prepareStatement(sql1);
				
				ps.setString(1, UserID);
				ps.executeUpdate();
			}else if(r < 5) {
				String sql1 = "update client set rank = 'Junior' where id = ?";
				ps = con.prepareStatement(sql1);
				
				ps.setString(1, UserID);
				ps.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}

	public void apply(DefaultTableModel model) {
		try {
			String sql = "select manage.id, cname, tripname, tripRv from manage inner join client on manage.id = client.id where manage.id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, User);
			rs = ps.executeQuery();
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				mdto.setId(rs.getString("ID"));
				mdto.setCname(rs.getString("CNAME"));
				mdto.setTripName(rs.getString("TRIPNAME"));
				mdto.setTripRv(rs.getString("TRIPRV"));
				Object data[] = { mdto.getId(), mdto.getCname(), mdto.getTripName(), mdto.getTripRv() };
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
	
	public int selectfoto(String vName) throws SQLException {
		String sql = "SELECT tripnum FROM trip WHERE tripname = '" + vName + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int tripnum = 0;
		
		if (rs.next()) {
			tripnum = rs.getInt("tripNUM");
		}
		return tripnum;
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