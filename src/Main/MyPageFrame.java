package Main;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import DAO.MyPageDAO;
import DTO.MyPageDTO;

public class MyPageFrame extends JPanel implements ActionListener {

     
      JLabel laID, laPW,laPC, laName, laJumin, laTel, laAddr, laDate, laPoint, laRank;
      static JTextField tfId;
	JTextField tfName;
	JTextField tfJumin;
	JTextField tfAddr;
	JTextField tfTel1;
	JTextField tfTel2;
	JTextField tfTel3;
	JTextField tfDate;
	JTextField tfP;
	JTextField tfJ;
	JTextField tfPoint, tfRank;
	JLabel laImage;
	ImageIcon image1;

      JButton bU, bO, bD, bP;

      MyPageDAO dao;

      MyPageDTO dto = new MyPageDTO();
      JTabbedPane t = new JTabbedPane();
      String User;

      public MyPageFrame(String UserID) {
         User = UserID;
         newObject();
         addLayout();
         setStyle();

         try {
            dao = new MyPageDAO();
            System.out.println("DB 연결 성공");
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DB 연결 실패: " + e.getMessage());
         }
         dto = dao.showMyInfo(UserID);
         tfId.setText(dto.getId());
         tfP.setText(dto.getPw());
         tfName.setText(dto.getName());
         tfJumin.setText(dto.getJumin().substring(0, 6));
         tfJ.setText(dto.getJumin().substring(7, 13));
         tfTel1.setText(dto.getTel().substring(0, 3));
         tfTel2.setText(dto.getTel().substring(4, 8));
         tfTel3.setText(dto.getTel().substring(9, 13));
         tfAddr.setText(dto.getAddr());
         tfDate.setText(dto.getRegisterdate().toString());
         tfPoint.setText(dto.getPoint());
         tfRank.setText(dto.getRank());

         
      }


      void newObject() {

    	  image1 = new ImageIcon("tr_my1.jpg");
    	 laImage = new JLabel(image1);
         String id = dto.getId();
         String pw = dto.getPw();
         String name = dto.getName();
         String jumin = dto.getJumin();
         String tel = dto.getTel();
         String addr = dto.getAddr();
         String registerdate = dto.getRegisterdate();
         String point = dto.getPoint();
         String rank = dto.getRank();

         JLabel title = new JLabel("마 이 페 이 지", JLabel.CENTER);
        title.setFont(new Font("함초롬돋움", Font.BOLD, 30));
         bU = new JButton("수정");
         bO = new JButton("로그아웃");
         bD = new JButton("탈퇴");
         bP = new JButton("내 포인트 확인");

         laID = new JLabel("ID", JLabel.RIGHT);
         laPW = new JLabel("비밀번호", JLabel.RIGHT);
         laName = new JLabel("이름", JLabel.RIGHT);
         laJumin = new JLabel("주민번호", JLabel.RIGHT);
         laAddr = new JLabel("주소", JLabel.RIGHT);
         laTel = new JLabel("전화번호", JLabel.RIGHT);
         laDate = new JLabel("가입날짜", JLabel.RIGHT);
         laPoint = new JLabel("포인트", JLabel.RIGHT);
         laRank = new JLabel("고객등급", JLabel.RIGHT);

         tfId = new JTextField();
         tfName = new JTextField();
         tfJumin = new JTextField();
         tfAddr = new JTextField();
         tfTel1 = new JTextField(3);
         tfTel2 = new JTextField(4);
         tfTel3 = new JTextField(4);
         tfDate = new JTextField();
         tfPoint = new JTextField();
         tfRank = new JTextField();

         tfP = new JPasswordField();
         tfJ = new JPasswordField();
        
         title.setBounds(310, 15, 405, 55);
         add(title);

      }

      void addLayout() {
         JLabel dashJ1 = new JLabel("-");
         JLabel dashT1 = new JLabel("-");
         JLabel dashT2 = new JLabel("-");
         setLayout(null);
        
         laID.setBounds(270, 135, 120, 30);
         tfId.setBounds(410, 135, 200, 30);
         add(laID);
         add(tfId);// id

         laName.setBounds(270, 170, 120, 30);
         tfName.setBounds(410, 170, 200, 30);
         add(laName);
         add(tfName);// name

         laJumin.setBounds(270, 205, 120, 30);
         tfJumin.setBounds(410, 205, 80, 30);
         dashJ1.setBounds(500, 205, 20, 30);
         tfJ.setBounds(520, 205, 90, 30);
         add(laJumin);
         add(tfJumin);
         add(dashJ1);
         add(tfJ);
         // jumin


         laTel.setBounds(270, 240, 120, 30);
         tfTel1.setBounds(410, 240, 50, 30);
         dashT1.setBounds(465, 240, 20, 30);
         tfTel2.setBounds(475, 240, 60, 30);
         dashT2.setBounds(539, 240, 20, 30);
         tfTel3.setBounds(550, 240, 60, 30);
         add(laTel);
         add(tfTel1);
         add(dashT1);
         add(tfTel2);
         add(dashT2);
         add(tfTel3);// 전화번호

         laAddr.setBounds(270, 275, 120, 30);
         tfAddr.setBounds(410, 275, 200, 30);
         add(laAddr);
         add(tfAddr);// 주소

         laDate.setBounds(270, 310, 120, 30);
         tfDate.setBounds(410, 310, 200, 30);
         add(laDate);
         add(tfDate);
         
         laRank.setBounds(270, 345, 120, 30);
         tfRank.setBounds(410, 345, 200, 30);
         add(laRank);
         add(tfRank);
         
         laPoint.setBounds(270, 380, 120, 30);
         tfPoint.setBounds(410, 380, 200, 30);
         bP.setBounds(630, 380, 120, 30);
         add(laPoint);
         add(tfPoint);
         add(bP);
         
         bU.setBounds(300, 435, 120, 30);
         bO.setBounds(430, 435, 120, 30);
         bD.setBounds(560, 435, 120, 30);
         add(bU);
         add(bO);
         add(bD);
       
         laImage.setBounds(0,500,2000,500);
         add(laImage);
      bU.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
        	  new UpdatePop(User);
        	 
         }
      });
      
      bD.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
             Object obj = ae.getSource();
             if (obj == bD) {
                try {
                   String id = tfId.getText();
                   int result = JOptionPane.showConfirmDialog(null, "탈퇴하시겠습니까?", "Confirm",
                         JOptionPane.YES_NO_OPTION);
                   if (result == JOptionPane.YES_OPTION) {
                      dao.deleteProcess(dto);
                      System.out.println("삭제 성공");
                      System.exit(0);
                   }

                } catch (Exception e) {
                   JOptionPane.showMessageDialog(null, "삭제 실패 : " + e.getMessage());
                }
             }
          }

       });
      
      bO.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
             Object obj = ae.getSource();
             if (obj == bO) {
                try {
                   int result = JOptionPane.showConfirmDialog(null, "로그아웃하시겠습니까?", "Confirm",
                         JOptionPane.YES_NO_OPTION);
                   if (result == JOptionPane.YES_OPTION) {
                      JOptionPane.showMessageDialog(null, "로그아웃이 완료되었습니다.");
                      System.exit(0);
                   }
                } catch (Exception e) {
                   JOptionPane.showMessageDialog(null, "삭제 실패 : " + e.getMessage());
                }
             }
          }

       });
      
      bP.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
             Object obj = ae.getSource();
             String UserID = User; 
             if (obj == bP) {
                try {
//                   dao.showMyInfo(User);
//                   tfPoint.setText(dto.getPoint());
                	dto = dao.showMyInfo(UserID);
                    tfPoint.setText(dto.getPoint());
//                	new MyPageFrame(UserID);
                } catch (Exception e) {
                   JOptionPane.showMessageDialog(null, "삭제 실패 : " + e.getMessage());
                }
             }
          }

       });
      
      tfTel1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
            JTextField tfTel1 = (JTextField) ke.getSource();
            if(tfTel1.getText().length()>=3) ke.consume();
            }
         
         });
         
         tfTel2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
            JTextField tfTel2 = (JTextField) ke.getSource();
            if(tfTel2.getText().length()>=4) ke.consume();
            
            }
         });
         tfTel3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
            JTextField tfTel3 = (JTextField) ke.getSource();
            if(tfTel3.getText().length()>=4) ke.consume();
            
            }
         });
      
   }

   void setStyle() {
      tfId.setEditable(false);
      tfP.setEditable(false);
      tfName.setEditable(false);
      tfJumin.setEditable(false);
      tfJ.setEditable(false);
      tfTel1.setEditable(false);
      tfTel2.setEditable(false);
      tfTel3.setEditable(false);
      tfDate.setEditable(false);
      tfAddr.setEditable(false);
      tfPoint.setEditable(false);
      tfRank.setEditable(false);
   }




   @Override
   public void actionPerformed(ActionEvent e) {

   }



}
