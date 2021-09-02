package Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.ClientDAO;
import DTO.ClientDTO;

public class ClientMain extends JPanel {

	String[] name1 = { "패키지명", "가  격", "days", "시작날짜", "종료날짜", "spot", "info" };
//	String[] name2 = { "ID", "회원이름", "패키지명", "신청날짜" };

	Object ob1[][] = new Object[0][7];
	Object ob2[][] = new Object[0][4];

	DefaultTableModel dt1 = new DefaultTableModel(ob1, name1);
//	DefaultTableModel dt2 = new DefaultTableModel(ob2, name2);
	JTable jt1 = new JTable(dt1);
//	JTable jt2 = new JTable(dt2);
	JScrollPane jsp1 = new JScrollPane(jt1);
//	JScrollPane jsp2 = new JScrollPane(jt2);
//	JPanel p2 = new JPanel();
	JTabbedPane t = new JTabbedPane();

	String[] searchTrip = { "낮은 가격순", "높은 가격순", "출 발 날 짜" };
	JComboBox comTripSearch = new JComboBox(searchTrip);

	JButton btn1 = new JButton("검색");
	JButton btn3 = new JButton("예약취소");
	JButton btn4 = new JButton("신청");
	JButton btn2 = new JButton("전체보기");

	JLabel title = new JLabel("패키지 신청 내역");

	JTextField jtextfield = new JTextField(20);
	
	ImageIcon[] images = { new ImageIcon("1.jpg"), new ImageIcon("2.jpg"),new ImageIcon("3.jpg"),
	         new ImageIcon("4.jpg"), new ImageIcon("5.jpg"),new ImageIcon("6.jpg"),
	         new ImageIcon("7.jpg"), new ImageIcon("9.jpg"),
	         new ImageIcon("10.jpg"), new ImageIcon("11.jpg"),new ImageIcon("12.jpg"),
	         new ImageIcon("13.jpg"), new ImageIcon("14.jpg"),new ImageIcon("15.jpg"),
	         new ImageIcon("16.jpg"), new ImageIcon("17.jpg"),new ImageIcon("18.jpg"),
	         new ImageIcon("19.jpg"), new ImageIcon("20.jpg"),new ImageIcon("21.jpg"),
	         new ImageIcon("22.jpg"), new ImageIcon("23.jpg"),new ImageIcon("24.jpg"),
	         new ImageIcon("25.jpg"), new ImageIcon("26.jpg"),new ImageIcon("27.jpg"),
	         new ImageIcon("28.jpg"), new ImageIcon("29.jpg"),new ImageIcon("30.jpg")
	         };
	JLabel jl = new JLabel(images[0]);
	JScrollPane jsp2 = new JScrollPane(jl);

	static String UserID;
	String User;

	public ClientMain(String UserID) throws Exception {

		User = UserID;
		ClientDAO dao = new ClientDAO(User);

		title.setFont(new Font("함초롬돋움", Font.BOLD, 18));

		jt1.getColumn("패키지명").setPreferredWidth(400);
		jt1.getColumn("가  격").setPreferredWidth(70);
		jt1.getColumn("spot").setPreferredWidth(40);
		jt1.getColumn("info").setPreferredWidth(150);
		jsp2.getVerticalScrollBar().setUnitIncrement(16);

		JPanel jp = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp1 = new JPanel();

		jp1.add(comTripSearch);
		jp1.add(jtextfield);
		jp1.add(btn1);
		jp1.add(btn2);

		jp.setBorder(new TitledBorder("여행지 고르기"));
		jp.setLayout(new BorderLayout());
		jp.add("Center", jsp1);
		jp.add("North", jp1);
		jp.add("South", btn4);
		
		jp.setBorder(new TitledBorder("여행지 사진"));
		jp2.setLayout(new BorderLayout());
		jp2.add(jsp2);
		

		setLayout(new GridLayout(1, 2));
		add(jp);
		add(jp2);

		setVisible(true);
		dao.select(dt1);

		dao.tableCellCenter(jt1);
		dao.tableCellRight(jt1);

		comTripSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				if (cb == comTripSearch) {
					switch (cb.getSelectedIndex()) {
					case 0:
						dao.radioAsc(dt1);
						break;
					case 1:
						dao.radioDesc(dt1);
						break;
					case 2:
						dao.startDate(dt1);
						break;
					}
				}
			}
		});

		jt1.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				String msg = getToolTipText(e);
				jt1.setToolTipText(msg);
			}

			public String getToolTipText(MouseEvent e) {
				JTable t = (JTable) e.getSource();
				Point p = e.getPoint();
				int row = t.rowAtPoint(p);
				int column = t.columnAtPoint(p);
				return t.getModel().getValueAt(row, column).toString();
			}
		});

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == btn1) {
					if (jtextfield.getText().trim().length() > 0) {
						dao.getUserSearch(dt1, jtextfield.getText());
					} else
						JOptionPane.showMessageDialog(null, "검색어를 입력해주세요");
				}
			}
		});

		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == btn2)
					dao.select(dt1);
			}
		});

		btn4.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            Object obj = e.getSource();
	            if (obj == btn4) {
	               int row = jt1.getSelectedRow();
	               String data = (String) jt1.getModel().getValueAt(row, 0);
	               if (dao.nameCheck(data)) {
	                  JOptionPane.showMessageDialog(null, "중복신청");
	               } else {
	                  int result = JOptionPane.showConfirmDialog(null, "신청하시겠습니까?", "Confirm",
	                        JOptionPane.YES_NO_OPTION);
	                  if (result == JOptionPane.YES_OPTION) {
	                     JOptionPane.showMessageDialog(null, "신청완료");
	                     dao.book(data);
	                     try {
							dao.updatePoint(data, User);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                  }
	               }
	            }
	         }
	      });
		
		jt1.addMouseListener(new MouseListener() {

	         @Override
	         public void mouseReleased(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mousePressed(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mouseClicked(MouseEvent e) {
	            jt1 = (JTable) e.getSource();
	            int row = jt1.getSelectedRow();
	    		String coffeeno = jt1.getValueAt(row, 0).toString();

	            if (coffeeno.equals("리무진 타고 떠나는 강원도 여행!")) {
		               jl.setIcon(images[0]);
		            } else if (coffeeno.equals("울릉도 여행 패키지 강릉출발")) {
		            	jl.setIcon(images[1]);
		            } else if (coffeeno.equals("'KTX 홍도 & 흑산도 & 비금도 & 도초도 기차여행")) {
		            	jl.setIcon(images[2]);
		            } else if (coffeeno.equals("제주도 원데이투어 프리미엄 감성여행")) {
		            	jl.setIcon(images[3]);
		            } else if (coffeeno.equals("매일 떠나는 강릉 구석구석 시티투어+솔향수목원+정동진바다부채길+안목커피거리+오죽헌")) {
		            	jl.setIcon(images[4]);
		            } else if (coffeeno.equals("VAN 타고 떠나는 통영,거제 1박2일 (가족여행, 소규모 모임여행 추천)")) {
		            	jl.setIcon(images[5]);
		            } else if (coffeeno.equals("[경주 야경] 낭만 가득한 신라의 야경 맛집 탐방 (경주야경투어)")) {
		            	jl.setIcon(images[6]);
		            } else if (coffeeno.equals("[평창군지원] [평창 택시투어 1탄] 천년고찰 월정사 전나무숲길~대관령삼양목장(당일)")) {
		            	jl.setIcon(images[7]);
		            } else if (coffeeno.equals("[인천시민할인] 천혜의 비경 백령도(매일출발 1박2일)")) {
		            	jl.setIcon(images[8]);
		            } else if (coffeeno.equals("★07월22일 출발확정★정읍 쌍화차거리+라벤더 허브원")) {
		            	jl.setIcon(images[9]);
		            } else if (coffeeno.equals("[문화+하이킹] 넓고 깊은 지리산의 모든 것 4일")) {
		            	jl.setIcon(images[10]);
		            } else if (coffeeno.equals("섬티아고 순례+신안일주 5일")) {
		            	jl.setIcon(images[11]);
		            } else if (coffeeno.equals("여수+제주도 싹쓰리 패키지 3일 [특급 라마다 호텔 3박]")) {
		            	jl.setIcon(images[12]);
		            } else if (coffeeno.equals("[당일] 만원의 행복 4탄 \"인천\"여행")) {
		            	jl.setIcon(images[13]);
		            } else if (coffeeno.equals("초특가 뱀사골 봄꽃트래킹/ 광한루 봄꽃 / 전주 특급 현대호텔 (1박2일) - 호텔조식포함 3식제공")) {
		            	jl.setIcon(images[14]);
		            } else if (coffeeno.equals("경남 함양 스카이뷰CC")) {
		            	jl.setIcon(images[15]);
		            } else if (coffeeno.equals("★5월 19일 수 출발확정!★강화 전등사 + 광성보 + 조양방직 카페 + 풍물시장 + 농촌체험 (당일)")) {
		            	jl.setIcon(images[16]);
		            } else if (coffeeno.equals("[포항출발] 울릉도 로맨틱 허니문 자유여행 2박3일 (라페루즈리조트 오션뷰 숙박/렌터카48시간)")) {
		            	jl.setIcon(images[17]);
		            } else if (coffeeno.equals("[경남-1박2일] 구석구석 경상도 탐방기 근대화거리 밀양아리랑천문대 표충사")) {
		            	jl.setIcon(images[18]);
		            } else if (coffeeno.equals("★09월23일 출발확정★화천 비수구미 트레킹+평화의댐 (25인안전여행)")) {
		            	jl.setIcon(images[19]);
		            } else if (coffeeno.equals("[지원특가] 푸른바다 고창청보리밭과 군산 당일여행")) {
		            	jl.setIcon(images[20]);
		            } else if (coffeeno.equals("횡성시티투어1코스 횡성호수길+안흥찐빵마을+햄버거만들기체험+풍수원성당")) {
		            	jl.setIcon(images[21]);
		            } else if (coffeeno.equals("[LUXURY~ 강원도 별미1박] 마리나 요트투어 + 하늘목장 트랙터마차 + 씨크루즈 호텔숙박 (호텔조식, 오징어순대, 오삼불고기) (1박2일)")) {
		            	jl.setIcon(images[22]);
		            } else if (coffeeno.equals("★5월 29일 토 출발확정!★안동 하회마을 + 월영교 + 물길공원 + 부용대 + 안동시장 먹방 (당일)")) {
		            	jl.setIcon(images[23]);
		            } else if (coffeeno.equals("★5월 19일 수 출발확정!★양양 미천골휴양림 + 서피비치 + 휴휴암 + 남애항 전망대 (당일)")) {
		            	jl.setIcon(images[24]);
		            } else if (coffeeno.equals("[경주당일여행-오픈할인EVENT] 경주의 찐핫플만 여행하는 1일버스여행")) {
		            	jl.setIcon(images[25]);
		            } else if (coffeeno.equals("[지원특가] 김대건 신부 탄생 200주년 한국의 산티아고 당진 여행")) {
		            	jl.setIcon(images[26]);
		            } else if (coffeeno.equals("★07월23일 출발확정★고창 유채꽃밭여행 학원농장 고창읍성 (24인안전여행)")) {
		            	jl.setIcon(images[27]);
		            } else if (coffeeno.equals("5/19 출발확정 : 지원특가] 낭만BIG3 대관령양떼목장 , 정동진, 안목항커피거리여행")) {
		            	jl.setIcon(images[28]);
		            }
	         }
	      });
		
	}

}