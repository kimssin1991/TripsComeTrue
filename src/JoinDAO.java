

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class JoinDAO {

	Connection con;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Statement st = null;
	String id;

	JoinDTO dto = new JoinDTO();

	public JoinDAO() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	public boolean IdCheck(String id) {
        boolean result = true;
 
        try {
        	String sql = "select * from Client where id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id.trim());
            rs = ps.executeQuery();
            if (rs.next())
                result = false; 
 
        } catch (SQLException e) {
            System.out.println(e + "=>  getIdByCheck fail");
        } finally {
            dbClose();
        }
 
        return result;
 
    }

	public void insertProcess(JoinDTO dto) {
		try {
			String sql = "insert into  Client (id, pw, name, jumin, tel, addr, registerdate, point, rank)"
					+ " values (?, ?, ?, ?, ?, ?, sysdate, 3000, 'Junior')";
			System.out.println("sql" + sql);
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getJumin());
			ps.setString(5, dto.getTel());
			ps.setString(6, dto.getAddr());
			ps.executeUpdate();
			System.out.println("DB추가 성공");
			System.exit(0);
			JOptionPane.showMessageDialog(null, "가입성공");
			
		} catch (Exception e) {
			System.out.println("DB 추가 실패" + e);
			JOptionPane.showMessageDialog(null, "비밀번호가 동일하지 않음");
		} finally {
			dbClose();
		}
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
