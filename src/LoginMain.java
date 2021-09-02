import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LoginMain extends JFrame implements ActionListener {
   TextField tfUser_ID, tfPassword;
   JButton loginBtn, joinBtn;
   BufferedImage img = null;
   //ImageIcon icon;
   String id;
   String pass;
   String sql;
   String sql2;
   JoinFrame jf;
   LoginMain login;
   JLabel words;
   Font font = new Font("한컴 쿨재즈 B", Font.BOLD, 37);
   String [] word = {"<html>인생은 짧고 세상은 넓다.<br>세상탐험은 지금 시작하는 것이 좋다.<html>",
   		 "<html>진정한 여행이란<br>새로운 풍경을 보는 것이 아니라,<br>새로운 눈을 가지는 것에 있다.<html>",
   		 "<html>청춘은 여행이다.<br>찢어진 주머니에 두 손을 내리꽂은 채<br>그저 길을 떠나도 좋은 것이다.<html>",
   		 "<html>여행하지 않은 사람에게<br>이 세상은<br>한 페이지만 읽은 책과 같다.<html>",
   		 "<html>여행과 변화를 사랑하는 사람은<br>생명을 가진 사람이다.<html>",
   		 "<html>소중한 것을 깨닫는 장소는<br>언제나 컴퓨터 앞이 아니라<br>파란 하늘 아래이다.<html>"};
   String [] foto = {"C:\\Users\\백인엽\\eclipse-workspace\\z\\foto1.png",
		   			"C:\\Users\\백인엽\\eclipse-workspace\\z\\foto2.png",
		   			"C:\\Users\\백인엽\\eclipse-workspace\\z\\fpto3.png",
		   			"C:\\Users\\백인엽\\eclipse-workspace\\z\\foto4.png",
		   			"C:\\Users\\백인엽\\eclipse-workspace\\z\\foto5.png",
   					"C:\\Users\\백인엽\\eclipse-workspace\\z\\foto6.png"};
   
   //관리자 창
   Trip trip;
   //고객 창
   Client tab;
   
   PreparedStatement ps;
   ResultSet rs;
   Connection con;
   

   public static void main(String[] args) {
	   LoginMain log = new LoginMain();

   }

   public LoginMain() {

      super("Trip Come True");
      try {
         con = ConnectionPool.getConnection();
         System.out.println("DB 연결 성공");
      } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "DB 연결 실패: " + e.getMessage());
      }
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            dispose();
         }
      });

      JLayeredPane pan = new JLayeredPane();
      pan.setLayout(null);
      try {
        img = ImageIO.read(new File(foto[((int)(Math.random()*foto.length))]));
        System.out.println(foto[((int)(Math.random()*foto.length))]);
      } catch (Exception e) {
         dispose();
      }
      myPanel panel = new myPanel();
      panel.setSize(1000, 800);

      setLayout(null);

      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setSize(1000, 800);
      setLayout(null);
      setFont(new Font("Serif", Font.BOLD, 15));
      
      words = new JLabel();
//      words.setBackground(new Color(255, 0, 0, 125));
      words.setText(word[((int)(Math.random()*word.length))]);
      words.setVerticalAlignment(SwingConstants.CENTER);
      //words.setHorizontalAlignment(JLabel.CENTER);
      words.setVerticalAlignment(SwingConstants.TOP);
      words.setOpaque(false);
//      words.setFont(new Font("Blackadder ITC", Font.BOLD, 35));
      words.setForeground(Color.WHITE);
      words.setBounds(320, 300, 500, 200);
      words.setFont(font);

      JLabel lblLogin = new JLabel("I D");
      lblLogin.setOpaque(false);
      lblLogin.setFont(new Font("Serif", Font.BOLD, 17));
      lblLogin.setForeground(Color.WHITE);
      lblLogin.setBounds(320, 500, 30, 25);

      JLabel lblPassword = new JLabel("P W");
      lblPassword.setOpaque(false);
      lblPassword.setFont(new Font("Serif", Font.BOLD, 17));
      lblPassword.setForeground(Color.WHITE);
      lblPassword.setBounds(500, 500, 33, 25);

      tfUser_ID = new TextField(15);
      tfUser_ID.setBounds(350, 500, 120, 22);

      tfPassword = new TextField(20);
      tfPassword.setBounds(545, 500, 120, 22);

      joinBtn = new JButton("가입하기");
      joinBtn.setBounds(520, 570, 100, 25);

      loginBtn = new JButton("로그인");
      loginBtn.setBounds(380, 570, 100, 25);

      tfPassword.setEchoChar('*');
      add(words);
      add(lblLogin);
      add(tfUser_ID);
      add(lblPassword);
      add(tfPassword);
      add(joinBtn);
      add(loginBtn);
      add(panel);

      setVisible(true);
      loginBtn.addActionListener(this);
      joinBtn.addActionListener(this); 
      tfPassword.addKeyListener(new KeyListener() {
          
          @Override
          public void keyTyped(KeyEvent e) {
             
          }
          
          @Override
          public void keyReleased(KeyEvent e) {
             
          }
          
          @Override
          public void keyPressed(KeyEvent e) {
             int key = e.getKeyCode();
             if(key == KeyEvent.VK_ENTER) {
                Toolkit.getDefaultToolkit().beep();
                loginBtn.doClick();
             }
          }
       });
   }

   class myPanel extends JPanel {
      public void paint(Graphics g) {
         g.drawImage(img, 0, 0, null);
      }
   }
   
   
   
   
   @Override
   public void actionPerformed(ActionEvent e) {
      Object o2 = e.getSource();
      if(o2 == joinBtn) {
         JoinFrame jf = new JoinFrame();
      }
      Object o = e.getSource();
      if (o == loginBtn) {
         id = tfUser_ID.getText().trim();
         pass = tfPassword.getText().trim();
         sql = "SELECT * FROM client WHERE id ='" + id + "'";
         try {
             con = ConnectionPool.getConnection();
             ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
             while (rs.next()) {
                if (id.equals("admin")  &&  pass.equals("admin")) {
                   JOptionPane.showMessageDialog(this, "관리자로 로그인!");
                   trip = new Trip();
                   return;
                } else if (pass.equals(rs.getString("pw"))) {
                   JOptionPane.showMessageDialog(this, tfUser_ID.getText()+"님, 반가워요!");
                   tab = new Client(tfUser_ID.getText());
                   return;
                } else if (id.equals("") ) {
                         JOptionPane.showMessageDialog(this, "ID를 입력해주세요.");
                         login.tfUser_ID.requestFocus();
                         return;
                }else if (pass.equals("") ) {
                            JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요.");
                            login.tfPassword.requestFocus();
                            return;
                } else if (!pass.equals(rs.getString("pw")) || !id.equals(rs.getString("id"))) {
                   JOptionPane.showMessageDialog(this, "ID 또는 Password를 확인해주세요.");
                   login.tfPassword.setText("");
                   login.tfPassword.requestFocus();
                   return;
                }
             }
             JOptionPane.showMessageDialog(this, "존재하지 않는 계정입니다.");
          } catch (Exception e1) {
             return;
             
          }
//         } finally {
//            try {
//               rs.close();
//               ps.close();
//               con.close();
//               return;
//            } catch (Exception e1) {
//               JOptionPane.showMessageDialog(null, "없는 계정입니다.");
//            }
         }
      }
   }
