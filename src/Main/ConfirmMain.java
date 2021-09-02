package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.ClientDAO;
import DAO.ConfirmDAO;
import DTO.TripDTO;


public class ConfirmMain extends JPanel{

   String[] name2 = {"패키지명", "출발날짜", "가격" };

   Object ob2[][] = new Object[0][4];

   DefaultTableModel dt2 = new DefaultTableModel(ob2, name2);
   JTable jt2 = new JTable(dt2);
   JScrollPane jsp2 = new JScrollPane(jt2);
//   JPanel p2 = new JPanel();
   
   TextArea textA;
   JButton btn3 = new JButton("예약취소");
   JButton btn4 = new JButton("전체보기");
   
   ConfirmDAO dao;
   JTextArea taSpot;
   JTextArea tainfo;

   JTextField jtextfield = new JTextField(20);
   String User;
   
   public ConfirmMain(String UserID) throws Exception {
      try {
      User = UserID;
      dao = new ConfirmDAO(User);
     // jsp2.setPreferredSize(new Dimension(950, 250));
    //  add(jsp2);
     // JPanel img = new JPanel();
      
      
      
//        JPanel a = new JPanel();
//         JPanel spot = new JPanel();
//         spot.setLayout(new BorderLayout());
//         JTextArea taSpot = new JTextArea(15, 3);
//         spot.add("Center", taSpot);
//         JScrollPane sps = new JScrollPane(taSpot);
//         spot.add(sps);
//         spot.setBorder(new TitledBorder("여 행 지"));
         
      
      
//      JScrollPane spta1 = new JScrollPane();
//      JPanel textPan1 = new JPanel();
//       textPan1.setLayout(new BorderLayout());
//       textPan1.add("Center",spta1);
//       textPan1.add(spta1);
//       textPan1.setBorder(new TitledBorder("여 행 지"));
//      JPanel textPan2 = new JPanel();
      ImageIcon img1 = new ImageIcon("confirm1.png");
      ImageIcon img2 = new ImageIcon("confirm2.jpg");
      JLabel forimg1 = new JLabel(img1); 
      JLabel forimg2 = new JLabel(img2);
      
        JPanel a = new JPanel();
         JPanel spot = new JPanel();
         spot.setLayout(new BorderLayout());
         
         taSpot = new JTextArea(15, 3);
         spot.add("Center", taSpot);
         JScrollPane sps = new JScrollPane(taSpot);
         taSpot.setEditable(false);
         spot.add(sps);
         spot.setBorder(new TitledBorder("여 행 지"));
         
         JPanel b = new JPanel();
         
         JPanel info = new JPanel();
         info.setLayout(new BorderLayout());
         
         tainfo = new JTextArea(15, 3);
         info.add("Center", tainfo);
         tainfo.setEditable(false);
         
         JScrollPane jinfo = new JScrollPane(tainfo);
         info.add(jinfo);
         info.setBorder(new TitledBorder("설   명"));
         
       
      
      
         setLayout(null);
    //     pWestNorthDown.setBorder(new TitledBorder("설        명"));
         
      jsp2.setBounds(330, 0, 338, 250);
      spot.setBounds(330,255,338,200);
      info.setBounds(330,460,338,200);
     forimg1.setBounds(0, 0, 330, 660);
     forimg2.setBounds(670, 0, 330, 660);
     
     btn3.setBounds(380, 670, 100, 28);
     btn4.setBounds(500, 670, 100, 28);
     add(spot);
     add(info);
      add(jsp2);
      add(forimg1);
      add(forimg2);
      add(btn3);
      add(btn4);
      
//      add(textPan1);
//      add(textPan2);
      
      
//   add(forimg1, BorderLayout.WEST);
    //  시작
   //   add(forimg2, BorderLayout.EAST);
   //     JPanel forbt = new JPanel();
   //   add(forbt, BorderLayout.SOUTH);
      //forbt.add(btn3);
   //   forbt.add(btn4);
      //끝
      
   //   btn4.setBounds(x, y, width, height);
      //add(btn3);
     // add(jsp2, "Center");
    //  add(btn3);
   //   add(btn4);
  
     
      
      dao.apply(dt2);
      setVisible(true);
      setSize(900, 255);
      
      btn4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
         Object obj = e.getSource();
         if(obj == btn4) {
            dao.apply(dt2);
         }
      }
   });
      
      jt2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				select();
			}
		});
      
      
      btn3.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             Object obj = e.getSource();
             if (obj == btn3) {
                int row = jt2.getSelectedRow();
                String data = (String) jt2.getModel().getValueAt(row, 2);
                int result = JOptionPane.showConfirmDialog(null, "취소하시겠습니까?", "Confirm",
                   JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                   dao.delete(data);
                   dao.apply(dt2);
                   JOptionPane.showMessageDialog(null, "취소완료");
                }
             }
          }
       });
      }catch (Exception e) {
         e.printStackTrace();
      }
   }
	void select() {
		int row = jt2.getSelectedRow();
		int col = 0;
		String vNum = (String) jt2.getValueAt(row, col);// row행 1번 컬럼의 값을 저장(1번 컬럼이 PK니까)
		TripDTO vo = new TripDTO();
		try {
			vo = dao.selectByPk(vNum);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "패키지 검색 실패 : " + ex.getMessage());
		}
		tainfo.setText(vo.getTripInfo());
		taSpot.setText(vo.getTripSpot());
	}
      

}