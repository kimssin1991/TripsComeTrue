package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.AdminClientDTO;

public class AdminClientDAO {
   
      Connection con;
      // 생성자
      public AdminClientDAO() throws Exception {
         connectDB();
      }
      // DB 연결
      void connectDB() throws Exception {
         con = ConnectionPool.getConnection();
      }


      public void deleteClient(AdminClientDTO dto)  {
         
         String sql = "delete from Client where name = ?";

         try {
         PreparedStatement ps = con.prepareStatement(sql);
         
         ps.setString(1, dto.getName());
         // System.out.println(dto.getId());
         
         ps.executeUpdate();
         ps.close();
         }catch(Exception e) {
            System.out.println(e);
         }
      
      }
         
   
      //비디오 수정하는 메소드
      public void updateClient(AdminClientDTO dto) {
         //String sql = "update Client set pw = ?, name = ?, jumin = ?, tel = ?, addr = ?, registerdate = ? "
                  //   + " where id =" + dto.getId();
         String sql = "update Client set pw = ?, name = ?, jumin = ?, tel = ?, addr = ?, registerdate = ?, point = ?, rank = ? "
               + " where id = ?";
         try {   
//            System.out.println(dto.getRegisterdate());
//            String ab = dto.getRegisterdate().toString();
//            ab = ab.replace("-", "/");
//            System.out.println(ab);
//            ab = ab.substring(0, 10);
//            System.out.println(ab);
         
         PreparedStatement ps = con.prepareStatement(sql);
         //ps.setString(1, dto.getId());
         ps.setString(1, dto.getPw());
         ps.setString(2, dto.getName());
         ps.setString(3, dto.getJumin());
         ps.setString(4, dto.getTel());
         ps.setString(5, dto.getAddr());
         ps.setString(6, dto.getRegisterdate());
         ps.setString(7, dto.getPoint());
         ps.setString(8, dto.getRank());
         ps.setString(9, dto.getId());
         
         ps.executeUpdate();
         ps.close();
            
         }catch(Exception e) {
            System.out.println(e);
         }
      
      }

      public AdminClientDTO selectByPk(String vId) throws Exception {
         AdminClientDTO dto = new AdminClientDTO();

         String sql = "SELECT * FROM client WHERE id = ?";
         

         PreparedStatement stmt = con.prepareStatement(sql);
         stmt.setString(1, vId);
         ResultSet rs = stmt.executeQuery();
         
         if (rs.next()) {
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
         stmt.close();

         return dto;
      }

//      //비디오 검색하는 메소드
      public ArrayList selectClient(int sel, String text) throws Exception {
         // 3. sql 문장 만들기
         String[] selCol = { "name", "tel", "jumin" };
         String sql = "select id, pw, name, jumin, tel, addr, registerdate, point, rank from client " + "where "
               + selCol[sel] + " like '%" + text + "%'";

         // 4. 전송객체 얻어오기
         PreparedStatement ps = con.prepareStatement(sql);

         // 5. 전송하기
         ResultSet rs = ps.executeQuery();
         ArrayList list = new ArrayList();

         while (rs.next()) {
            ArrayList temp = new ArrayList();
            temp.add(rs.getString("id"));
            temp.add(rs.getString("pw"));
            temp.add(rs.getString("name"));
            temp.add(rs.getString("jumin"));
            temp.add(rs.getString("tel"));
            temp.add(rs.getString("addr"));
            temp.add(rs.getString("registerdate"));
            temp.add(rs.getString("point"));
            temp.add(rs.getString("rank"));
            list.add(temp);
         }
         rs.close();
         ps.close();

         return list;
      }
   }