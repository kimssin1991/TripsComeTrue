package Main;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.MyPageDAO;
import DTO.MyPageDTO;

public class UpdatePop extends JFrame {

	JLabel laID, laCPW, laPW, laPC, laName, laJumin, laTel, laAddr, laDate;
	JTextField tfId;
	JTextField tfName;
	JTextField tfJumin;
	JTextField tfAddr;
	JTextField tfTel1;
	JTextField tfTel2;
	JTextField tfTel3;
	JTextField tfDate;
	JTextField tfP;
	JTextField tfJ;
	JTextField tfPC;
	JTextField tfcp;

	JButton bU, bC;

	MyPageDAO dao;

	MyPageDTO dto = new MyPageDTO();

	String User;
	JLabel laImage;
	ImageIcon image1;
//	Connection con;
//	ResultSet rs;

	public UpdatePop(String UserID) {
		User = UserID;
		 
		try {
			dto = new MyPageDTO();
			dao = new MyPageDAO();
			System.out.println("DB 연결 성공!!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DB 연결 실패: " + e.getMessage());
		}
        

		/*
		 * String id = dto.getId(); System.out.println(); String pw = dto.getPw();
		 * String name = dto.getName(); String jumin = dto.getJumin(); String tel =
		 * dto.getTel(); String addr = dto.getAddr(); String registerdate =
		 * dto.getRegisterdate();
		 * 
		 * tfId.setText(id); System.out.println("2"); // tfP.setText(dto.getPw()); //
		 * tfPC.setText(dto.getPw()); tfName.setText(dto.getName());
		 * tfJumin.setText(dto.getJumin().substring(0, 6));
		 * tfJ.setText(dto.getJumin().substring(7, 13));
		 * tfTel1.setText(dto.getTel().substring(0, 3));
		 * tfTel2.setText(dto.getTel().substring(4, 8));
		 * tfTel3.setText(dto.getTel().substring(9, 13)); tfAddr.setText(dto.getAddr());
		 * tfDate.setText(dto.getRegisterdate().toString());
		 * 
		 */
		image1 = new ImageIcon("tr_my2.jpg");
		laImage = new JLabel(image1);
		JLabel title = new JLabel("내 정 보 수 정", JLabel.CENTER);
		title.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		bU = new JButton("수     정");
		bC = new JButton("취     소");

		laID = new JLabel("ID", JLabel.RIGHT);
		laCPW = new JLabel("현재 비밀번호", JLabel.RIGHT);
		laPW = new JLabel("변경할 비밀번호", JLabel.RIGHT);
		laPC = new JLabel("변경할 비밀번호 확인", JLabel.RIGHT);
		laName = new JLabel("이름", JLabel.RIGHT);
		laJumin = new JLabel("주민번호", JLabel.RIGHT);
		laAddr = new JLabel("주소", JLabel.RIGHT);
		laTel = new JLabel("전화번호", JLabel.RIGHT);
		laDate = new JLabel("가입날짜", JLabel.RIGHT);

		tfId = new JTextField();
		tfName = new JTextField();
		tfJumin = new JTextField();
		tfAddr = new JTextField();
		tfTel1 = new JTextField();
		tfTel2 = new JTextField();
		tfTel3 = new JTextField();
		tfDate = new JTextField();

		tfcp = new JPasswordField();
		tfP = new JPasswordField();
		tfPC = new JPasswordField();
		tfJ = new JPasswordField();

		title.setBounds(15, 25, 400, 70);
		add(title);
		setTitle("내 정 보 수 정");

		JLabel dashJ1 = new JLabel("-");
		JLabel dashT1 = new JLabel("-");
		JLabel dashT2 = new JLabel("-");
		setLayout(null);

		laID.setBounds(50, 100, 100, 30);
		tfId.setBounds(170, 100, 200, 30);
		add(laID);
		add(tfId);// id
		
		laCPW.setBounds(50, 135, 100, 30);
		tfcp.setBounds(170, 135, 200, 30);
		add(laCPW);
		add(tfcp);
		
		laPW.setBounds(50, 170, 100, 30);
		tfP.setBounds(170, 170, 200, 30);
		add(laPW);
		add(tfP);// pw

		laPC.setBounds(0, 205, 150, 30);
		tfPC.setBounds(170, 205, 200, 30);
		add(laPC);
		add(tfPC);// pw확인

		laName.setBounds(50, 240, 100, 30);
		tfName.setBounds(170, 240, 200, 30);
		add(laName);
		add(tfName);// name

		laJumin.setBounds(50, 275, 100, 30);
		tfJumin.setBounds(170, 275, 90, 30);
		dashJ1.setBounds(267, 275, 20, 30);
		tfJ.setBounds(280, 275, 90, 30);
		add(laJumin);
		add(tfJumin);
		add(dashJ1);
		add(tfJ);
		// jumin

		laTel.setBounds(50, 310, 100, 30);
		tfTel1.setBounds(170, 310, 50, 30);
		dashT1.setBounds(225, 310, 20, 30);
		tfTel2.setBounds(235, 310, 60, 30);
		dashT2.setBounds(299, 310, 20, 30);
		tfTel3.setBounds(310, 310, 60, 30);
		add(laTel);
		add(tfTel1);
		add(dashT1);
		add(tfTel2);
		add(dashT2);
		add(tfTel3);// 전화번호

		laAddr.setBounds(50, 345, 100, 30);
		tfAddr.setBounds(170, 345, 200, 30);
		add(laAddr);
		add(tfAddr);// 주소

		laDate.setBounds(50, 380, 100, 30);
		tfDate.setBounds(170, 380, 200, 30);
		add(laDate);
		add(tfDate);

		bU.setBounds(120, 430, 100, 30);
		bC.setBounds(240, 430, 100, 30);
		add(bU);
		add(bC);

		tfId.setEditable(false);
		tfName.setEditable(false);
		tfJumin.setEditable(false);
		tfJ.setEditable(false);
		tfDate.setEditable(false);
		laImage.setBounds(0, 470, 450, 200);
		add(laImage);
		setSize(450, 800);

		setVisible(true);

		// dao.showMyInfo(User);
		dto = dao.showMyInfo(UserID);

		tfId.setText(dto.getId());

		/*
		 * String id = dto.getId(); String pw = dto.getPw(); String name =
		 * dto.getName(); String jumin = dto.getJumin(); String tel = dto.getTel();
		 * String addr = dto.getAddr(); String registerdate = dto.getRegisterdate();
		 * 
		 */
		// tfP.setText(dto.getPw());
		// tfPC.setText(dto.getPw());
		tfName.setText(dto.getName());
		tfJumin.setText(dto.getJumin().substring(0, 6));
		tfJ.setText(dto.getJumin().substring(7, 13));
		tfTel1.setText(dto.getTel().substring(0, 3));
		tfTel2.setText(dto.getTel().substring(4, 8));
		tfTel3.setText(dto.getTel().substring(9, 13));
		tfAddr.setText(dto.getAddr());
		tfDate.setText(dto.getRegisterdate().toString());

		bU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object obj = ae.getSource();
				if (obj == bU) {
					try {
						MyPageDAO dao = new MyPageDAO();
						//String pass = dao.pwCheck();
						if(!(tfcp.getText().equals("")||tfP.getText().equals("")||tfPC.getText().equals("")||
								tfTel1.getText().equals("")||tfTel2.getText().equals("")||tfTel3.getText().equals("")||
								tfAddr.getText().equals(""))) {
							if(tfcp.getText().equals(dto.getPw())) {
								if (tfP.getText().trim().equals(tfPC.getText().trim())) {
									dto.setId(tfId.getText());
									dto.setPw(tfP.getText());
									//dto.setPw(tfPC.getText());
									dto.setName(tfName.getText());
									dto.setTel(tfTel1.getText() + "-" + tfTel2.getText() + "-" + tfTel3.getText());
									dto.setAddr(tfAddr.getText());
									dto.setRegisterdate(tfDate.getText());
									int result = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "Confirm",
							                 JOptionPane.YES_NO_OPTION);
							           if (result == JOptionPane.YES_OPTION) {
									dao.updateProcess(dto);
							        }
									JOptionPane.showMessageDialog(null, "수정 성공");
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "비밀번호 확인이 일치하지 않습니다.");
								}
							}else {
								JOptionPane.showMessageDialog(null, "현재 비밀번호를 확인해주세요!");
							}
						}else {
							JOptionPane.showMessageDialog(null, "빈칸이 있는지 확인해주세요!");
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "수정 실패" + e.getMessage());
					}

				}
			}
		});
		bC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object obj = ae.getSource();
				if (obj == bC) {
					try {
						JOptionPane.showMessageDialog(null, "취소");
						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "취소 실패 : " + e.getMessage());
					}
				}
			}

		});

		tfTel1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField tfTel1 = (JTextField) ke.getSource();
				if (tfTel1.getText().length() >= 3)
					ke.consume();
			}

		});

		tfTel2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField tfTel2 = (JTextField) ke.getSource();
				if (tfTel2.getText().length() >= 4)
					ke.consume();

			}
		});
		tfTel3.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField tfTel3 = (JTextField) ke.getSource();
				if (tfTel3.getText().length() >= 4)
					ke.consume();

			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		dispose();

	}
}
