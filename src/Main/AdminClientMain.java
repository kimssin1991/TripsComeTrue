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
            System.out.println("DB ���� ����");
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DB ���� ����: " + e.getMessage());
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
         
         
         bSearch = new JButton("��    ��");
         bUpdate = new JButton("��    ��");
         bDelete = new JButton("��   ��");
         bClear = new JButton("ȭ�� ����");

         searchClient = new String[] { "�� �̸�", "��ȭ��ȣ", "�ֹε�Ϲ�ȣ" };
         comClientSearch = new JComboBox(searchClient);
         tfClientSearch = new JTextField(15);

         tmClient = new ClientTableModel();
         tableClient = new JTable(tmClient); 
         
         img1 = new ImageIcon("customanage2.png");
         forimg = new JLabel(img1);
      }

      void addLayout() {
         // west ���� : ���� �Է� �� ����
         try {
    	 JPanel pWest = new JPanel();
         // ���� �����Է��ϴ� �κ�
         JPanel pWestNorth = new JPanel();
         JPanel pWestNorthUp = new JPanel();
         pWestNorthUp.setLayout(new GridLayout(10, 2));

         pWestNorthUp.add(new JLabel("                       I         D "));
         pWestNorthUp.add(tfId);
         pWestNorthUp.add(new JLabel("                       P         W "));
         pWestNorthUp.add(tfPw); 
         pWestNorthUp.add(new JLabel("                       ��                    �� "));
         pWestNorthUp.add(tfName);
         pWestNorthUp.add(new JLabel("                       ��  ��  ��  ��  ��  ȣ "));
         pWestNorthUp.add(tfJumin);
         pWestNorthUp.add(new JLabel("                       ��           ��          ó "));
         pWestNorthUp.add(tfTel);
         pWestNorthUp.add(new JLabel("                       ��      ��      ��      �� "));
         pWestNorthUp.add(tfAddr);
         pWestNorthUp.add(new JLabel("                       ��            ��          �� "));
         pWestNorthUp.add(tfRegisterdate);
         pWestNorthUp.add(new JLabel("                       ��      ��       ��     �� "));
         pWestNorthUp.add(tfRank);
         pWestNorthUp.add(new JLabel("                       ��            ��          Ʈ "));
         pWestNorthUp.add(tfPoint);

         pWestNorth.setBorder(new TitledBorder("�� ���� �Է� "));

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

         // east ���� : ��û���� �˻��κ�
         JPanel pEast = new JPanel();
         JPanel pEastNorth = new JPanel();
         pEastNorth.add(comClientSearch);
         pEastNorth.add(tfClientSearch);
         pEastNorth.add(bSearch);
         pEastNorth.setBorder(new TitledBorder("�� ���� ��ȸ"));
         

         JPanel pEastCenter = new JPanel();
         pEastCenter.setLayout(new BorderLayout());
         pEastCenter.add("Center", new JScrollPane(tableClient));

         JPanel pEastSouth = new JPanel();
         //pEastSouth.setLayout(new BorderLayout());
         //pEastSouth.add("South", bTotal);
         //pEastSouth.add("South", bClear); // �̻��ϸ� �׸���� �ٲ㺸��

         pEastSouth.setLayout(new GridLayout(1, 1));
         pEastSouth.add(bClear);
         
         pEast.setLayout(new BorderLayout());
         pEast.add("North", pEastNorth);
         pEast.add("Center", pEastCenter);
         pEast.add("South", pEastSouth);

         // ��ü ���̱�
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
            tmClient.fireTableDataChanged(); //�������̺� ������ ����� �����ʿ��� ����

         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "�˻� ���� : " + e.getMessage());
         }
            
         }

         void search() {
            int sel = comClientSearch.getSelectedIndex();
            String text = tfClientSearch.getText();

            try {
               ArrayList list = dao.selectClient(sel, text);
               tmClient.data = list;
               tableClient.setModel(tmClient);
               tmClient.fireTableDataChanged(); //�������̺� ������ ����� �����ʿ��� ����

            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "�˻� ���� : " + e.getMessage());
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
               JOptionPane.showMessageDialog(null, "���� ����");
            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "���� ����" + e.getMessage());
            }
            clearScreen();
            
         }

          void delete() {
            String name = tfName.getText();

            AdminClientDTO dto = new AdminClientDTO();
            dto.setName(name); 

            try {
               dao.deleteClient(dto);; 
               JOptionPane.showMessageDialog(null, "���� ����");
            } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "���� ����" + e.getMessage());
            }
            clearScreen();
            
         }
          
          public void select() {
        	  int row = tableClient.getSelectedRow();
              int col = 0;
              String vId = (String) tableClient.getValueAt(row, col);// row�� 1�� �÷��� ���� ����(1�� �÷��� PK�ϱ�)
              AdminClientDTO dto = new AdminClientDTO();
              try {
                 dto = dao.selectByPk(vId);
              } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "������ �˻� ���� : " + ex.getMessage());
              }
              
              // VO�� ������ ���� ���� ȭ�鿡 ���
              
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
   String[] columnNames = { "ID", "PW", "�� �̸�", "�ֹ� ��� ��ȣ", "��ȭ ��ȣ", "���� ����","������"};

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