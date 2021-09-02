package Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import DAO.AdminClientDAO;
import DTO.AdminClientDTO;

public class AdminClientMain extends JPanel implements ActionListener {
   
      
      JTextField tfId, tfPw, tfName,tfJumin, tfTel, tfAddr, tfRegisterdate, tfPoint, tfRank;

      JButton bUpdate, bDelete, bSearch, bClear;
      JComboBox comClientSearch;
      JTextField tfClientSearch;
      JTable tableClient;
      AdminClientDAO dao;

      ClientTableModel tmClient;
      String searchClient[];
      JLabel forimg;
      ImageIcon img1;
      JPanel imgp; 

      public AdminClientMain() {
         newObject();
         addLayout();
         setStyle();
         eventProc();
         try {
            dao = new AdminClientDAO();
            System.out.println("DB 연결 성공");
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DB 연결 실패: " + e.getMessage());
         }
      }

      void newObject() {
               
         tfId = new JTextField(15);
         tfPw = new JTextField(15);
         tfName = new JTextField(15);
         tfJumin = new JTextField(15);
         tfTel = new JTextField(15);
         tfAddr = new JTextField(15);
         tfRegisterdate = new JTextField(15);
         tfPoint = new JTextField(15);
         tfRank = new JTextField(15);
         
         
         bSearch = new JButton("검    색");
         bUpdate = new JButton("수    정");
         bDelete = new JButton("삭   제");
         bClear = new JButton("화면 비우기");

         searchClient = new String[] { "고객 이름", "전화번호", "주민등록번호" };
         comClientSearch = new JComboBox(searchClient);
         tfClientSearch = new JTextField(15);

         tmClient = new ClientTableModel();
         tableClient = new JTable(tmClient); 
         
         img1 = new ImageIcon("customanage2.png");
         forimg = new JLabel(img1);
      }

      void addLayout() {
         // west 영역 : 비디오 입력 및 수정
         try {
    	 JPanel pWest = new JPanel();
         // 비디오 정보입력하는 부분
         JPanel pWestNorth = new JPanel();
         JPanel pWestNorthUp = new JPanel();
         pWestNorthUp.setLayout(new GridLayout(10, 2));

         pWestNorthUp.add(new JLabel("                       I         D "));
         pWestNorthUp.add(tfId);
         pWestNorthUp.add(new JLabel("                       P         W "));
         pWestNorthUp.add(tfPw); 
         pWestNorthUp.add(new JLabel("                       이                    름 "));
         pWestNorthUp.add(tfName);
         pWestNorthUp.add(new JLabel("                       주  민  등  록  번  호 "));
         pWestNorthUp.add(tfJumin);
         pWestNorthUp.add(new JLabel("                       연           락          처 "));
         pWestNorthUp.add(tfTel);
         pWestNorthUp.add(new JLabel("                       거      주      지      역 "));
         pWestNorthUp.add(tfAddr);
         pWestNorthUp.add(new JLabel("                       가            입          일 "));
         pWestNorthUp.add(tfRegisterdate);
         pWestNorthUp.add(new JLabel("                       고      객       등     급 "));
         pWestNorthUp.add(tfRank);
         pWestNorthUp.add(new JLabel("                       포            인          트 "));
         pWestNorthUp.add(tfPoint);

         pWestNorth.setBorder(new TitledBorder("고객 정보 입력 "));

         pWestNorth.setLayout(new BorderLayout());
         pWestNorth.add("North", pWestNorthUp);
         pWestNorth.add("South", forimg);
         
         JPanel pWestSouth = new JPanel();

         JPanel pWestSouthDown = new JPanel();
         pWestSouthDown.setLayout(new GridLayout(1, 2));
         pWestSouthDown.add(bUpdate);
         pWestSouthDown.add(bDelete);

         pWestSouth.setLayout(new GridLayout(1, 1));
         pWestSouth.add(pWestSouthDown);

         pWest.setLayout(new BorderLayout());
         pWest.add("Center", pWestNorth);
         pWest.add("South", pWestSouth);

         // east 영역 : 신청내역 검색부분
         JPanel pEast = new JPanel();
         JPanel pEastNorth = new JPanel();
         pEastNorth.add(comClientSearch);
         pEastNorth.add(tfClientSearch);
         pEastNorth.add(bSearch);
         pEastNorth.setBorder(new TitledBorder("고객 정보 조회"));
         

         JPanel pEastCenter = new JPanel();
         pEastCenter.setLayout(new BorderLayout());
         pEastCenter.add("Center", new JScrollPane(tableClient));

         JPanel pEastSouth = new JPanel();
         //pEastSouth.setLayout(new BorderLayout());
         //pEastSouth.add("South", bTotal);
         //pEastSouth.add("South", bClear); // 이상하면 그리드로 바꿔보기

         pEastSouth.setLayout(new GridLayout(1, 1));
         pEastSouth.add(bClear);
         
         pEast.setLayout(new BorderLayout());
         pEast.add("North", pEastNorth);
         pEast.add("Center", pEastCenter);
         pEast.add("South", pEastSouth);

         // 전체 붙이기
         setLayout(new GridLayout(1, 2));
         add(pWest);
         add(pEast);
         }catch(Exception e) {
        	 e.printStackTrace();
         }
      }

      void setStyle() {
         
         tfId.setEditable(false);
      
      }

      void eventProc() {
         bUpdate.addActionListener(this);
         bDelete.addActionListener(this);
         tfClientSearch.addActionListener(this);
         //bTotal.addActionListener(this); 
         
      bSearch.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            search();
            }
         });

         bClear.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               clearScreen();
            }
         });

         tableClient.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	select();
            }
         });
      }
      public void actionPerformed(ActionEvent ev) {
         Object o = ev.getSource();

         if (o == bUpdate) {
            update();
         } else if (o == bDelete) {
            delete();
            
         } else if (o == tfClientSearch) {
            search();
         }
         };
      void total() {
         int sel = comClientSearch.getSelectedIndex();
         String text = tfClientSearch.getText();

         try {
            ArrayList list = dao.selectClient(sel, text);
            tmClient.data = list;
            tableClient.setModel(tmClient);
            tmClient.fireTableDataChanged(); //제이테이블 데이터 변경시 리스너에게 전달

         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());
         }
            
         }

         void search() {
            int sel = comClientSearch.getSelectedIndex();
            String text = tfClientSearch.getText();

            try {
               ArrayList list = dao.selectClient(sel, text);
               tmClient.data = list;
               tableClient.setModel(tmClient);
               tmClient.fireTableDataChanged(); //제이테이블 데이터 변경시 리스너에게 전달

            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "검색 실패 : " + e.getMessage());
            }
            
         }
      

       void update() {
            String id = tfId.getText();
            String pw = tfPw.getText();
            String name = tfName.getText();
            String jumin = tfJumin.getText();
            String tel = tfTel.getText();
            String addr = tfAddr.getText();   
            String registerdate = tfRegisterdate.getText();
            String point = tfPoint.getText();
            String rank = tfRank.getText();

            
            AdminClientDTO dto = new AdminClientDTO();
            
            dto.setId(id);
            dto.setPw(pw);
            dto.setName(name);
            dto.setJumin(jumin);
            dto.setTel(tel);
            dto.setAddr(addr);
            dto.setRegisterdate(registerdate);
            dto.setPoint(point);
            dto.setRank(rank);

            try {
               dao.updateClient(dto);
               JOptionPane.showMessageDialog(null, "수정 성공");
            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "수정 실패" + e.getMessage());
            }
            clearScreen();
            
         }

          void delete() {
            String name = tfName.getText();

            AdminClientDTO dto = new AdminClientDTO();
            dto.setName(name); 

            try {
               dao.deleteClient(dto);; 
               JOptionPane.showMessageDialog(null, "삭제 성공");
            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "삭제 실패" + e.getMessage());
            }
            clearScreen();
            
         }
          
          public void select() {
        	  int row = tableClient.getSelectedRow();
              int col = 0;
              String vId = (String) tableClient.getValueAt(row, col);// row행 1번 컬럼의 값을 저장(1번 컬럼이 PK니까)
              AdminClientDTO dto = new AdminClientDTO();
              try {
                 dto = dao.selectByPk(vId);
              } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "고객정보 검색 실패 : " + ex.getMessage());
              }
              
              // VO의 각각의 값을 왼쪽 화면에 출력
              
              tfId.setText(dto.getId());
              tfPw.setText(dto.getPw());
              tfName.setText(dto.getName());
              tfJumin.setText(dto.getJumin());
              tfTel.setText(dto.getTel());
              tfAddr.setText(dto.getAddr());
              tfRegisterdate.setText(dto.getRegisterdate());
              tfPoint.setText(dto.getPoint());
              tfRank.setText(dto.getRank());
          }
          
          void clearScreen() {
          tfId.setText("");
            tfPw.setText("");
            tfName.setText("");
            tfJumin.setText("");
            tfTel.setText("");
            tfAddr.setText("");
            tfRegisterdate.setText("");
            tfPoint.setText("");
            tfRank.setText("");
      }

   
}
class ClientTableModel extends AbstractTableModel {

   ArrayList data = new ArrayList();
   String[] columnNames = { "ID", "PW", "고객 이름", "주민 등록 번호", "전화 번호", "거주 지역","가입일"};

   public int getColumnCount() {
      return columnNames.length;
   }

   public int getRowCount() {
      return data.size();
   }

   public Object getValueAt(int row, int col) {
      ArrayList temp = (ArrayList) data.get(row);
      return temp.get(col);
   }


   public String getColumnName(int col) {
      return columnNames[col];
   }
}