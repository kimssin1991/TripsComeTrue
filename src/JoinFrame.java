

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class JoinFrame extends JFrame{

	JLabel la1, la2, la3, la4, la6, la7, la9;
	JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
	JPasswordField pf1, pf2, pf3;
	JButton b1, b3, b4;
	JTextField tf9;
	BufferedImage img;
	JoinDTO dto = new JoinDTO();
	
	

	public JoinFrame() {
		
		JLayeredPane pan = new JLayeredPane();
		pan.setLayout(null);
		try {
			img = ImageIO.read(new File("C:\\Users\\Kosmo_18\\Desktop\\join2.jpg"));
		}catch(Exception e) {
			
		}
		
		myPanel panel = new myPanel();
	      panel.setSize(470, 490);
		JLabel title = new JLabel("회원가입", JLabel.CENTER);
		b3 = new JButton("가입하기");
		b4 = new JButton("취소");
		
		la1 = new JLabel("ID", JLabel.RIGHT);
		la2 = new JLabel("비밀번호", JLabel.RIGHT);
		la3 = new JLabel("재입력", JLabel.RIGHT);
		la4 = new JLabel("이름", JLabel.RIGHT);
		la6 = new JLabel("주민번호", JLabel.RIGHT);
		la7 = new JLabel("주소", JLabel.RIGHT);
		la9 = new JLabel("전화번호", JLabel.RIGHT);

		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		tf5 = new JTextField();
		tf6 = new JTextField();
		tf7 = new JTextField();
		tf8 = new JTextField();
		tf9 = new JTextField();
		
		JLabel dash = new JLabel("-");
		JLabel dash1 = new JLabel("-");
		JLabel dash2 = new JLabel("-");

		pf1 = new JPasswordField();
		pf2 = new JPasswordField();
		pf3 = new JPasswordField();

		b1 = new JButton("중복체크");

		title.setFont(new Font("함초롬돋움", Font.BOLD, 40));

		setLayout(null);
		title.setBounds(10, 15, 405, 55);
		add(title);

		la1.setBounds(10, 100, 80, 30);
		tf1.setBounds(110, 100, 200, 30);
		b1.setBounds(320, 100, 100, 30);
		add(la1);
		add(tf1);
		add(b1);

		la2.setBounds(10, 135, 80, 30);
		pf1.setBounds(110, 135, 200, 30);
		add(la2);
		add(pf1);

		la3.setBounds(10, 170, 80, 30);
		pf2.setBounds(110, 170, 200, 30);
		add(la3);
		add(pf2);

		la4.setBounds(10, 205, 80, 30);
		tf2.setBounds(110, 205, 200, 30);
		add(la4);
		add(tf2);
		
		la6.setBounds(10, 240, 80, 30);
		tf3.setBounds(110, 240, 90, 30);
		dash.setBounds(207, 240, 20, 30);
		pf3.setBounds(220, 240, 90, 30);
		add(la6);
		add(tf3);
		add(dash);
		add(pf3);

		la7.setBounds(10, 275, 80, 30);
		tf5.setBounds(110, 275, 200, 30);
		add(la7);
		add(tf5);

		la9.setBounds(10, 310, 80, 30);
		tf9.setBounds(110, 310, 50, 30);
		dash1.setBounds(165, 310, 20, 30);
		tf7.setBounds(175, 310, 60, 30);
		dash2.setBounds(239, 310, 20, 30);
		tf8.setBounds(250, 310, 60, 30);
		add(la9);
		add(tf9);
		add(dash1);
		add(tf7);
		add(dash2);
		add(tf8);
		add(panel);
		
		b3.setBounds(100, 360, 100, 30);
		b4.setBounds(240, 360, 80, 30);
		add(b3);
		add(b4);

		setVisible(true);
		setSize(470, 490);
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == b1) {
					try {
						JoinDAO dao = new JoinDAO();
						if (tf1.getText().trim().equals("")||tf2.getText().trim().equals("")||tf3.getText().trim().equals("")||tf4.getText().trim().equals("")
								||tf5.getText().trim().equals("")||tf6.getText().trim().equals("")||tf7.getText().trim().equals("")||tf8.getText().trim().equals("")
								||tf9.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "ID를 입력해주세요");
							tf1.requestFocus();
						} else {
							if (dao.IdCheck(tf1.getText())) {
								JOptionPane.showMessageDialog(null, tf1.getText() + "은(는) 사용 가능한 ID입니다!");
							} else {
								JOptionPane.showMessageDialog(null, tf1.getText() + "은(는) 중복된 ID입니다!");
								tf1.setText("");
								tf1.requestFocus();
							}
						}
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		});
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == b3) {
					try {
						JoinDAO dao = new JoinDAO();
						if(!(tf1.getText().trim().equals("")||tf2.getText().trim().equals("")||tf3.getText().trim().equals("")||tf4.getText().trim().equals("")
								||tf5.getText().trim().equals("")||tf6.getText().trim().equals("")||tf7.getText().trim().equals("")||tf8.getText().trim().equals("")
								||tf9.getText().trim().equals(""))) {
						if (pf1.getText().trim().equals(pf2.getText().trim())) {
							dto.setId(tf1.getText());
							dto.setPw(pf1.getText());
							dto.setName(tf2.getText());
							dto.setJumin(tf3.getText() + "-" + tf6.getText());
							dto.setTel(tf9.getText() + "-" + tf8.getText() + "-" + tf7.getText());
							dto.setAddr(tf5.getText());
							LoginMain main = new LoginMain();
							JOptionPane.showMessageDialog(null, "<html>환영합니다!<br>3000포인트를 드립니다!<html>");
							dispose();
							dao.insertProcess(dto);
						}
						}else {
							JOptionPane.showMessageDialog(null, "빈칸이 있는지 확인해주세요");
						}

					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		});

		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		tf3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
            JTextField tf3 = (JTextField) ke.getSource();
            if(tf3.getText().length()>=6) ke.consume();
            }
         
         });
      
      
      pf3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
            JTextField pf3 = (JTextField) ke.getSource();
            if(pf3.getText().length()>=7) ke.consume();
            }
         
         });
         
         tf9.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
            JTextField tf9 = (JTextField) ke.getSource();
            if(tf9.getText().length()>=3) ke.consume();
            
            }
         });
         tf8.addKeyListener(new KeyAdapter() {
               public void keyTyped(KeyEvent ke) {
               JTextField tf8 = (JTextField) ke.getSource();
               if(tf8.getText().length()>=4) ke.consume();
               
               }
            });
         tf7.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
            JTextField tf7 = (JTextField) ke.getSource();
            if(tf7.getText().length()>=4) ke.consume();
            
            }
         });
	}
	   class myPanel extends JPanel {
		      public void paint(Graphics g) {
		    	  super.paintComponent(g);
		         g.drawImage(img, 0, 0, null);
		         
		      }
		   }
}
